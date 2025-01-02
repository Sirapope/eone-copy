package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import th.or.set.setportal.eonereport.bean.ChairmanMessageBean;
import th.or.set.setportal.eonereport.bean.IntroductionData;
import th.or.set.setportal.eonereport.bean.RemarkData;
import th.or.set.setportal.eonereport.bean.export.*;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.bean.response.ImageInfoResponse;
import th.or.set.setportal.eonereport.bean.response.OverallBusinessCorporatePolicyResponse;
import th.or.set.setportal.eonereport.config.Constants;
import th.or.set.setportal.eonereport.constants.AdditionalImgInfoType;
import th.or.set.setportal.eonereport.constants.IntroductionSection;
import th.or.set.setportal.eonereport.constants.RemarkSection;
import th.or.set.setportal.eonereport.model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static th.or.set.setportal.eonereport.config.Constants.*;
import static th.or.set.setportal.eonereport.service.UtilService.checkFieldIsNull;
import static th.or.set.setportal.eonereport.service.UtilService.checkNullSetDefaultDash;
import static th.or.set.setportal.eonereport.service.XlsUtilService.*;

@Service
@Slf4j
public class ExportBusinessAndCorporatePolicyService {
    @Autowired
    private BusinessStrategiesOverviewService businessStrategiesOverviewService;
    @Autowired
    private ReportDetailService reportDetailService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UtilService utilService;
    @Autowired
    private XlsUtilService xlsUtilService;
    @Autowired
    private AdditionalImageInfoService additionalImageInfoService;

    private final List<String> TITLE = Arrays.asList(
            "main_node1.node1.section1.topic1.question2",
            "main_node1.node1.section1.topic1.question3",
            "main_node1.node1.section1.topic1.question4",
            "main_node1.node1.section1.topic1.question5"
    );
    @Autowired
    private MaterialChangesAndDevelopmentsService materialChangesAndDevelopmentsService;
    @Autowired
    private SpentRaisedFundService spentRaisedFundService;
    @Autowired
    private SecuritiesOfferingObligationService securitiesOfferingObligationService;
    @Autowired
    private OverallBusinessCorporatePolicyService overallBusinessCorporatePolicyService;

    private static String JASPER_FOLDER_NAME = "1-1_BusinessAndCorporatePolicy";
    private static String JASPER_FILE_NAME = "BusinessAndCorporatePolicy";

    public JasperPrint exportReport(ReportDetail reportDetail, String language, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq) {
        try {
            return generateBusinessAndCorporatePolicy(reportDetail.getReport().getAsOfYear()
                    , Locale.forLanguageTag(language)
                    , changed
                    , currentStartPage
                    , isExcel
                    , isWord
                    , phase
                    , remarkSeq
                    , reportDetail);
        } catch (Exception e) {
            log.error("Error export BusinessAndCorporatePolicy", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    private JasperPrint generateBusinessAndCorporatePolicy(Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq, ReportDetail reportDetail) throws IOException, JRException {
        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase,
                (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        Map<String, Object> parameters = new HashMap<String, Object>();
        UtilService.setResourceBundle(parameters, locale);

        UtilService.addYearStructure(parameters, asOfYear, locale);
        parameters.put("Language", locale.getLanguage());
        parameters.put("FirstPage", currentStartPage);
        parameters.put("IsExcel", isExcel);
        parameters.put("Phase", phase);
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        parameters.put("SUBREPORT_DIR", UtilService.getTemplatePath(phase));
        xlsUtilService.generatePageTitle(reportDetail, locale, parameters, remarkSeq, isExcel, isWord);

        // Prepare data for report
        BusinessAndCorporatePolicyResponse businessAndCorporatePolicyResponse = new BusinessAndCorporatePolicyResponse();
        businessAndCorporatePolicyResponse.setBusinessStrategiesOverview(businessStrategiesOverviewService.getBusinessStrategiesOverview(reportDetail));
        businessAndCorporatePolicyResponse.setOtherBusinessStrategiesOverviewResponse(businessStrategiesOverviewService.getOtherBusinessStrategiesOverview(reportDetail));
        businessAndCorporatePolicyResponse.setMaterialChangesAndDevelopments(materialChangesAndDevelopmentsService.getMaterialChangesAndDevelopmentsByReportDetail(reportDetail));
        businessAndCorporatePolicyResponse.setSpentRaisedFund(spentRaisedFundService.getSpentRaisedFund(reportDetail));
        businessAndCorporatePolicyResponse.setSecuritiesOfferingObligation(securitiesOfferingObligationService.getSecuritiesOffering(reportDetail));
        businessAndCorporatePolicyResponse.setOverallBusinessCorporatePolicy(overallBusinessCorporatePolicyService.getOverallBusinessCorporatePolicyByrReportDetail(reportDetail));
        businessAndCorporatePolicyResponse.setChairmanMessageBean(businessStrategiesOverviewService.getChairmanMessage(reportDetail));
        businessAndCorporatePolicyResponse.setChairmanMessageImgInfos(businessStrategiesOverviewService.getChairmanMessageImage(reportDetail));
        businessAndCorporatePolicyResponse.setAdditionalImgInfoList(additionalImageInfoService.getAdditionalImgInfo(reportDetail, AdditionalImgInfoType.CompanyLogo,null));

        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap = xlsUtilService.getRemarkSectionRemarkDataMap(reportDetail, phase, REMARK_SECTION_BUSINESS_AND_CORPORATE_POLICY);
        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap = xlsUtilService.getIntroductionSectionIntroductionDataMap(reportDetail, phase, INTRODUCTION_SECTION_BUSINESS_AND_CORPORATE_POLICY);

        // 1.1.1
        prepareChairmanMessage(parameters, locale, businessAndCorporatePolicyResponse, reportDetail, isExcel, isWord);
        prepareBusinessStrategiesOverview(businessAndCorporatePolicyResponse.getBusinessStrategiesOverview(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareOtherBusinessStrategiesOverviews(businessAndCorporatePolicyResponse.getOtherBusinessStrategiesOverviewResponse(), parameters, locale, changed, phase, isExcel, isWord);

        // 1.1.2
        ReportDetailNoRelation bac02ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_02);
        prepareMaterialChangesAndDevelopments(businessAndCorporatePolicyResponse.getMaterialChangesAndDevelopments(),
                parameters, locale, changed, isExcel,isWord, bac02ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.3
        ReportDetailNoRelation bac03ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_03);
        prepareSpentRaisedFund(businessAndCorporatePolicyResponse.getSpentRaisedFund(), parameters, locale, changed,
                isExcel, isWord, bac03ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.4
        ReportDetailNoRelation bac04ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_04);
        prepareSecuritiesOffering(businessAndCorporatePolicyResponse.getSecuritiesOfferingObligation(), parameters,
                locale, changed, isExcel, isWord, bac04ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.5
        ReportDetailNoRelation bac05ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_05);
        prepareOverallBusinessCorporatePolicy(businessAndCorporatePolicyResponse.getOverallBusinessCorporatePolicy(),
                parameters, locale, changed, isExcel, isWord,bac05ReportDetail, remarkSeq, remarkSectionRemarkDataMap);
        prepareExportOverallBusinessCorporateShareList(businessAndCorporatePolicyResponse.getOverallBusinessCorporatePolicy(), parameters, locale, changed);
        String reportPath = UtilService.getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());
//        prepareAdditionalImgInfo(businessAndCorporatePolicyResponse.getAdditionalImgInfoList(),
//                parameters, locale, remarkSeq, remarkSectionRemarkDataMap, isExcel, reportPath);

        parameters.put("ShowBusinessStrategiesOverview1", parameters.get("ShowBac01") != null && (Boolean) parameters.get("ShowBac01"));
        xlsUtilService.prepareRemarkSequencePdf(locale, parameters, REAMRK_BUSINESS_AND_CORPORATE_POLICY_SECTION,
                changed, remarkSeq, isExcel, isWord);
        xlsUtilService.prepareIntroductionPdf(locale, parameters, introductionSectionIntroductionDataMap, INTRODUCTION_BUSINESS_AND_CORPORATE_POLICY_SECTION, changed, isExcel, isWord);

        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }

    private JasperPrint generateBusinessAndCorporatePolicy(DataStructureResponse dataStructureResponse, Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq, ReportDetail reportDetail) throws IOException, JRException {

        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase,
                (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        Map<String, Object> parameters = new HashMap<String, Object>();
        UtilService.setResourceBundle(parameters, locale);

        UtilService.addYearStructure(parameters, asOfYear, locale);
        parameters.put("Language", locale.getLanguage());
        parameters.put("FirstPage", currentStartPage);
        parameters.put("IsExcel", isExcel);
        parameters.put("Phase", phase);
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        parameters.put("SUBREPORT_DIR", UtilService.getTemplatePath(phase));
        xlsUtilService.generatePageTitle(reportDetail, locale, parameters, remarkSeq, isExcel, isWord);

        // Prepare data for report
        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap = xlsUtilService.getRemarkSectionRemarkDataMap(reportDetail, phase, REMARK_SECTION_BUSINESS_AND_CORPORATE_POLICY);
        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap = xlsUtilService.getIntroductionSectionIntroductionDataMap(reportDetail, phase, INTRODUCTION_SECTION_BUSINESS_AND_CORPORATE_POLICY);

        BusinessAndCorporatePolicyResponse businessAndCorporatePolicyResponse = dataStructureResponse.getBusinessAndCorporatePolicyResponse();
        // 1.1.1
        prepareChairmanMessage(parameters, locale, businessAndCorporatePolicyResponse, reportDetail, isExcel, isWord);
        prepareBusinessStrategiesOverview(businessAndCorporatePolicyResponse.getBusinessStrategiesOverview(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareOtherBusinessStrategiesOverviews(businessAndCorporatePolicyResponse.getOtherBusinessStrategiesOverviewResponse(), parameters, locale, changed, phase, isExcel, isWord);

        // 1.1.2
        ReportDetailNoRelation bac02ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_02);
        prepareMaterialChangesAndDevelopments(businessAndCorporatePolicyResponse.getMaterialChangesAndDevelopments(),
                parameters, locale, changed, isExcel,isWord, bac02ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.3
        ReportDetailNoRelation bac03ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_03);
        prepareSpentRaisedFund(businessAndCorporatePolicyResponse.getSpentRaisedFund(), parameters, locale, changed,
                isExcel, isWord, bac03ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.4
        ReportDetailNoRelation bac04ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_04);
        prepareSecuritiesOffering(businessAndCorporatePolicyResponse.getSecuritiesOfferingObligation(), parameters,
                locale, changed, isExcel, isWord, bac04ReportDetail, remarkSeq, remarkSectionRemarkDataMap);

        // 1.1.5
        ReportDetailNoRelation bac05ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_05);
        prepareOverallBusinessCorporatePolicy(businessAndCorporatePolicyResponse.getOverallBusinessCorporatePolicy(),
                parameters, locale, changed, isExcel, isWord,bac05ReportDetail, remarkSeq, remarkSectionRemarkDataMap);
        prepareExportOverallBusinessCorporateShareList(businessAndCorporatePolicyResponse.getOverallBusinessCorporatePolicy(), parameters, locale, changed);
        String reportPath = UtilService.getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());
//        prepareAdditionalImgInfo(businessAndCorporatePolicyResponse.getAdditionalImgInfoList(),
//                parameters, locale, remarkSeq, remarkSectionRemarkDataMap, isExcel, reportPath);

        parameters.put("ShowBusinessStrategiesOverview1", parameters.get("ShowBac01") != null && (Boolean) parameters.get("ShowBac01"));
        xlsUtilService.prepareRemarkSequencePdf(locale, parameters, REAMRK_BUSINESS_AND_CORPORATE_POLICY_SECTION,
                changed, remarkSeq, isExcel, isWord);
        xlsUtilService.prepareIntroductionPdf(locale, parameters, introductionSectionIntroductionDataMap, INTRODUCTION_BUSINESS_AND_CORPORATE_POLICY_SECTION, changed, isExcel, isWord);

        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }


    private void prepareBusinessStrategiesOverview(BusinessStrategiesOverview businessStrategiesOverview, Map<String, Object> parameters, Locale locale,
                                                   Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                                   boolean isExcel, boolean isWord,ReportDetail reportDetail) {
        String sustainabilityPolicyAndGoalQuestion1 = XlsUtilService.getNameByLanguage(reportDetail.getNodeConfig().getDescriptionTh(), reportDetail.getNodeConfig().getDescriptionEn(), locale);

        parameters.put("BusinessStrategiesOverview1", xlsUtilService.addRemarkSeq(sustainabilityPolicyAndGoalQuestion1,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.BUSINESS_AND_CORPORATE_POLICY),
                locale,
                isExcel
        ));
        ReportDetailNoRelation structureAndOperationReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), STRUCTURE_AND_OPERATION);
        String structureAndOperation = XlsUtilService.getNameByLanguage(structureAndOperationReportDetail.getNodeConfig().getDescriptionTh(), structureAndOperationReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("StructureAndOperation", structureAndOperation);
        ReportDetailNoRelation bac01ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), BAC_01);
        String bac01 = XlsUtilService.getNameByLanguage(bac01ReportDetail.getNodeConfig().getDescriptionTh(), bac01ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Bac01", xlsUtilService.addRemarkSeq(bac01,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.BAC01),
                locale,
                isExcel
        ));
        mapBusinessStrategiesOverview(parameters, locale,
                Arrays.asList(
                        XlsUtilService.getHtmlByLanguage(businessStrategiesOverview.getVisionTh(),
                                businessStrategiesOverview.getVisionEn(), locale, isWord),
                        XlsUtilService.getHtmlByLanguage(businessStrategiesOverview.getObjectivesTh(), businessStrategiesOverview.getObjectivesEn(), locale, isWord),
                        XlsUtilService.getHtmlByLanguage(businessStrategiesOverview.getGoalsTh(), businessStrategiesOverview.getGoalsEn(), locale, isWord),
                        XlsUtilService.getHtmlByLanguage(businessStrategiesOverview.getBusinessStrategiesTh(), businessStrategiesOverview.getBusinessStrategiesEn(), locale, isWord)
                ));

    }

    private void mapBusinessStrategiesOverview(Map<String, Object> parameters, Locale locale,
                                               List<String> value) {
        List<Pair<String, String>> pairArrayList = new ArrayList<>();
        for (int i = 0; i < TITLE.size(); i++) {
            pairArrayList.add(new Pair<>(messageSource.getMessage(TITLE.get(i), null, locale), value.get(i)));
        }
        JRBeanCollectionDataSource businessStrategiesOverviewList = new JRBeanCollectionDataSource(
                pairArrayList.stream()
                        .map(ExportBusinessAndCorporatePolicyService::getExportDescriptive)
                        .collect(Collectors.toList()));
        parameters.put("BusinessStrategiesOverviewList", businessStrategiesOverviewList);
    }

    private void prepareChairmanMessage(Map<String, Object> parameters, Locale locale,
                                        BusinessAndCorporatePolicyResponse businessAndCorporatePolicyResponse,
                                        ReportDetail reportDetail, Boolean isExcel, Boolean isWord) {
        ChairmanMessageBean chairmanMessage = businessAndCorporatePolicyResponse.getChairmanMessageBean();
        String chairmanDesc = XlsUtilService.getHtmlByLanguage(chairmanMessage.getDescriptionTh(),
                chairmanMessage.getDescriptionEn(), locale, isWord);
        if (!checkFieldIsNull(chairmanDesc)) {
            parameters.put("ChairmanMessage", new JRBeanCollectionDataSource(
                    List.of(new ExportDescriptive("<b>" + messageSource.getMessage("main_node1.node1.section1.topic5.question18", null, locale) + "</b>",
                            chairmanDesc))
            ));
        }
//        if (businessAndCorporatePolicyResponse.getChairmanMessageImgInfos() != null
//                && !businessAndCorporatePolicyResponse.getChairmanMessageImgInfos().isEmpty()
//                && businessAndCorporatePolicyResponse.getChairmanMessageImgInfos().stream()
//                .anyMatch(o -> XlsUtilService.getNameByLanguage(o.getGenerateFileNameTh(), o.getGenerateFileNameEn(), locale) != null)) {
//            String reportPath = UtilService.getReportPath(reportDetail);
//            parameters.put("ChairmanMessageImage", new JRBeanCollectionDataSource(
//                    businessAndCorporatePolicyResponse.getChairmanMessageImgInfos().stream()
//                            .map(o -> XlsUtilService.getExportImage(locale, o, reportPath))
//                            .collect(Collectors.toList())
//            ));
//        }
    }

    private static ExportDescriptive getExportDescriptive(Pair<String, String> o) {
        return new ExportDescriptive("<b>" + o.getFirst() + "</b>", o.getSecond());
    }

    private void prepareOtherBusinessStrategiesOverviews(List<OtherBusinessStrategiesOverview> otherBusinessStrategiesOverviews,
                                                         Map<String, Object> parameters, Locale locale, Boolean changed,
                                                         double phase, boolean isExcel, boolean isWord) {
        parameters.put("OtherBusinessStrategiesOverview", messageSource.getMessage("main_node1.node1.section1.topic1.question6", null, locale));

        List<ExportDescriptive> exportDescriptives = getOtherExportDescriptives(otherBusinessStrategiesOverviews,
                locale,isWord);
        JRBeanCollectionDataSource otherBusinessStrategiesOverviewList = new JRBeanCollectionDataSource(exportDescriptives);
        parameters.put("OtherBusinessStrategiesOverviewList", otherBusinessStrategiesOverviewList);
        parameters.put("ShowBac01", !otherBusinessStrategiesOverviewList.getData().isEmpty());

    }

    private static List<ExportDescriptive> getOtherExportDescriptives(List<OtherBusinessStrategiesOverview> otherBusinessStrategiesOverviews,
                                                                      Locale locale, boolean isWord) {
        return otherBusinessStrategiesOverviews.stream()
                .filter(o -> {
                    if (TH.equals(locale.getLanguage())) {
                        return !checkFieldIsNull(o.getNameTh()) || !checkFieldIsNull(o.getDescriptionTh());
                    }
                    return !checkFieldIsNull(o.getNameEn()) || !checkFieldIsNull(o.getDescriptionEn());
                })
                .map(o -> {
                    String name = XlsUtilService.checkNullSetDefaultDash(XlsUtilService.getNameByLanguage(o.getNameTh(), o.getNameEn(), locale));
                    if (TH.equalsIgnoreCase(locale.getLanguage())){
                        name = Thaicut.wordwrap(name, 80, isWord);
                    }
                    String description = XlsUtilService.getHtmlByLanguage(o.getDescriptionTh(), o.getDescriptionEn(),
                            locale, isWord);
                    return getExportDescriptive(new Pair<>(name, description));
                })
                .collect(Collectors.toList());
    }

    private void prepareMaterialChangesAndDevelopments(List<MaterialChangesAndDevelopment> materialChangesAndDevelopments, Map<String, Object> parameters, Locale locale,
                                                       Boolean changed, Boolean isExcel, Boolean isWord,
                                                       ReportDetailNoRelation bac02ReportDetail, RemarkSequence remarkSequence,
                                                       Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap) {
        String bac02 = XlsUtilService.getNameByLanguage(bac02ReportDetail.getNodeConfig().getDescriptionTh(), bac02ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Bac02", xlsUtilService.addRemarkSeq(bac02,
                remarkSequence,
                remarkSectionRemarkDataMap.get(RemarkSection.BAC02),
                locale,
                isExcel
        ));
        List<ExportYearStructureUnit> materialChangesAndDevelopmentsList =
                getExportYearStructureUnits(materialChangesAndDevelopments, locale, isWord);
        parameters.put("MaterialChangesAndDevelopmentsList", new JRBeanCollectionDataSource(materialChangesAndDevelopmentsList));
        parameters.put("ShowBac02", !materialChangesAndDevelopmentsList.isEmpty());
    }

    private static List<ExportYearStructureUnit> getExportYearStructureUnits(List<MaterialChangesAndDevelopment> materialChangesAndDevelopments, Locale locale, boolean isWord) {
        List<ExportYearStructureUnit> ex = materialChangesAndDevelopments.stream()
                .map(o -> new ExportYearStructureUnit(UtilService.convertToBuddhistYear(o.getYear()) + "",
                        XlsUtilService.getHtmlByLanguage(o.getDescriptionTh(), o.getDescriptionEn(), locale, 92, isWord)))
                .collect(Collectors.toList());
        XlsUtilService.setFirstAndLast(ex);
        return ex;
    }

    private void prepareSpentRaisedFund(SpentRaisedFund spentRaisedFund, Map<String, Object> parameters,
                                        Locale locale, Boolean changed, Boolean isExcel, Boolean isWord,
                                        ReportDetailNoRelation bac03ReportDetail, RemarkSequence remarkSeq,
                                        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap) {
        String bac03 = XlsUtilService.getNameByLanguage(bac03ReportDetail.getNodeConfig().getDescriptionTh(), bac03ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Bac03", xlsUtilService.addRemarkSeq(bac03,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.BAC03),
                locale,
                isExcel
        ));
        //SpentRaisedFundPolicy
        ExportYearStructureUnit exportYearStructure = new ExportYearStructureUnit();
        exportYearStructure.setQuestion(messageSource.getMessage("main_node1.node1.section1.topic3.question1", null, locale));
        exportYearStructure.setAsOfYear(xlsUtilService.checkPolicyNull(spentRaisedFund.getSecurityOffering(), locale));
        if (exportYearStructure.getAsOfYear() != null) {
            JRBeanCollectionDataSource spentRaisedFundPolicy = new JRBeanCollectionDataSource(List.of(exportYearStructure));
            parameters.put("SpentRaisedFundPolicy", spentRaisedFundPolicy);
        }
        //SpentRaisedFundList
        List<ExportSpentRaisedFundInfo> exportSpentRaisedFundInfoSecurityTypes =
                getExportSpentRaisedFundInfos(spentRaisedFund.getSpentRaisedFundInfo(), locale, isWord);
        parameters.put("SpentRaisedFundList", new JRBeanCollectionDataSource(exportSpentRaisedFundInfoSecurityTypes));
        parameters.put("ShowSpentRaisedFundList", spentRaisedFund.getSecurityOffering() != null && Y.equals(spentRaisedFund.getSecurityOffering()));
        //ApplicableLaw
        parameters.put("ApplicableLaw", XlsUtilService.getHtmlByLanguage(spentRaisedFund.getApplicableLawTh(), spentRaisedFund.getApplicableLawEn(), locale));
    }

    private List<ExportSpentRaisedFundInfo> getExportSpentRaisedFundInfos(List<SpentRaisedFundInfo> spentRaisedFundInfo, Locale locale, boolean isWord) {
        AtomicInteger seq = new AtomicInteger(1);
        return spentRaisedFundInfo.stream()
                .map(o -> {
                    ExportSpentRaisedFundInfo exportSpentRaisedFundInfo = new ExportSpentRaisedFundInfo();
                    exportSpentRaisedFundInfo.setSeq(messageSource.getMessage("main_node1.node1.section1.topic3.question4", null, locale).replace("{no}", seq.getAndIncrement() + ""));

                    if (o.getSecurityType().getName().equals("other")) {
                        exportSpentRaisedFundInfo.setSecurityType(XlsUtilService.getNameByLanguage(o.getOtherSecTypeTh(), o.getOtherSecTypeEn(), locale));
                    } else {
                        exportSpentRaisedFundInfo.setSecurityType(XlsUtilService.getNameByLanguage(o.getSecurityType().getNameTh(), o.getSecurityType().getNameEn(), locale));
                    }
                    if (TH.equalsIgnoreCase(locale.getLanguage())){
                        exportSpentRaisedFundInfo.setSecurityType(Thaicut.wordwrap(exportSpentRaisedFundInfo.getSecurityType(), 40, isWord));
                    }
                    exportSpentRaisedFundInfo.setFundraisingType(XlsUtilService.getNameByLanguage(o.getFundraisingType().getNameTh(), o.getFundraisingType().getNameEn(), locale));
                    exportSpentRaisedFundInfo.setFundraisingAmount(setSpentRaisedFundAmount(o.getFundraisingType().getName(), o.getFundraisingAmount(), o.getStartFundraisingAmount(), o.getEndFundraisingAmount())
                            + " " + messageSource.getMessage("main_node1.node1.section1.topic3.question18", null, locale));


                    List<ExportDescriptive> details = new ArrayList<>();
                    if (o.getAchievedSpentRaisedFund() != null) {
                        String answer = Y.equals(o.getAchievedSpentRaisedFund()) ?
                                messageSource.getMessage("main_node1.node1.section1.topic3.question11", null, locale)
                                : messageSource.getMessage("main_node1.node1.section1.topic3.question12", null, locale);
                        answer = Thaicut.wordwrap(answer, 93, isWord);
                        details.add(
                                getExportDescriptive(new Pair<>(messageSource.getMessage("main_node1.node1.section1.topic3.question10", null, locale), answer)));
                    }

                    //ความคืบหน้าของการใช้เงิน
                    details.add(getExportDescriptive(new Pair<>(messageSource.getMessage("main_node1.node1.section1.topic3.question13", null, locale),
                            XlsUtilService.checkNullSetDefaultDash(XlsUtilService.getHtmlByLanguage(o.getProgressSpentRaisedFundTh(), o.getProgressSpentRaisedFundEn(), locale, 107, isWord)))));

                    String url = XlsUtilService.getUrlByLanguage(o.getUrlTh(), o.getUrlEn(), locale);
                    details.add(getExportDescriptive(new Pair<>(messageSource.getMessage("main_node1.node1.section1.topic3.question14", null, locale),
                            XlsUtilService.checkNullSetDefaultDash(url))));

                    exportSpentRaisedFundInfo.setDetails(new JRBeanCollectionDataSource(details));
                    AtomicInteger seqObjective = new AtomicInteger(1);
                    List<ExportSpentRaisedFundObjective> exportSpentRaisedFundObjectives = o.getSpentRaisedFundObjective().stream()
                            .map(objective -> {
                                ExportSpentRaisedFundObjective exportSpentRaisedFundObjective = new ExportSpentRaisedFundObjective();
                                String objectiveValue = XlsUtilService.getNameByLanguage(objective.getObjectiveTh(),
                                        objective.getObjectiveEn(), locale);
                                String objectiveTxt =
                                        seqObjective.getAndIncrement() + ". " + (UtilService.checkFieldIsNull(objectiveValue) ? DASH : objectiveValue);
                                exportSpentRaisedFundObjective.setObjective(TH.equalsIgnoreCase(locale.getLanguage()) ? Thaicut.wordwrap(objectiveTxt, 43, isWord) : objectiveTxt);
                                exportSpentRaisedFundObjective.setPeriodType(XlsUtilService.getNameByLanguage(objective.getPeriodType().getNameTh(), objective.getPeriodType().getNameEn(), locale));
                                exportSpentRaisedFundObjective.setPeriod(formatDateByPeriod(objective.getPeriodType().getName(), objective.getStartPeriod(), objective.getEndPeriod(), objective.getPeriod(), locale));
                                exportSpentRaisedFundObjective.setSpentAmount(setSpentRaisedFundAmount(objective.getSpentAmountType().getName(), objective.getSpentAmount(), objective.getStartSpentAmount(), objective.getEndSpentAmount()));
                                exportSpentRaisedFundObjective.setExpectedAmount(setSpentRaisedFundAmount(objective.getExpectedAmountType().getName(), objective.getExpectedAmount(), objective.getStartExpectedAmount(), objective.getEndExpectedAmount()));
                                return exportSpentRaisedFundObjective;
                            })
                            .collect(Collectors.toList());
                    XlsUtilService.setFirstAndLast(exportSpentRaisedFundObjectives);
                    exportSpentRaisedFundInfo.setSpentRaisedFundObjective(new JRBeanCollectionDataSource(exportSpentRaisedFundObjectives));
                    return exportSpentRaisedFundInfo;
                })
                .collect(Collectors.toList());
    }

    private static String setSpentRaisedFundAmount(String name, BigDecimal number, BigDecimal start, BigDecimal end) {
        if (name.equals("integer")) {
            return checkNullSetDefaultDash(number, df_2);
        } else {
            return checkNullSetDefaultDash(start, df_2) + " - " + checkNullSetDefaultDash(end, df_2);
        }
    }

    private void prepareSecuritiesOffering(SecuritiesOfferingObligation securitiesOfferingObligation, Map<String,
            Object> parameters, Locale locale, Boolean changed, Boolean isExcel, Boolean isWord,
                                           ReportDetailNoRelation bac04ReportDetail, RemarkSequence remarkSeq, Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap) {
        String bac04 = XlsUtilService.getNameByLanguage(bac04ReportDetail.getNodeConfig().getDescriptionTh(),
                bac04ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Bac04", Thaicut.wordwrap(xlsUtilService.addRemarkSeq(bac04,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.BAC04),
                locale,
                isExcel
        ), 70, isWord));
        ExportYearStructureUnit exportYearStructure = new ExportYearStructureUnit();
        exportYearStructure.setQuestion(messageSource.getMessage("main_node1.node1.section1.topic4.question1", null, locale));
        exportYearStructure.setAsOfYear(xlsUtilService.checkPolicyNull(securitiesOfferingObligation.getSecuritiesOfferingObligation(), locale));
        if (exportYearStructure.getAsOfYear() != null) {
            JRBeanCollectionDataSource spentRaisedFundPolicy = new JRBeanCollectionDataSource(List.of(exportYearStructure));
            parameters.put("SecuritiesOfferingPolicy", spentRaisedFundPolicy);
        }
        parameters.put("SecuritiesOfferingDescription",
                XlsUtilService.getHtmlByLanguage(securitiesOfferingObligation.getDescriptionTh(),
                        securitiesOfferingObligation.getDescriptionEn(), locale, isWord));
    }

    private void prepareOverallBusinessCorporatePolicy(OverallBusinessCorporatePolicyResponse overallBusinessCorporatePolicyResponse, Map<String, Object> parameters, Locale locale, Boolean changed, Boolean isExcel, Boolean isWord, ReportDetailNoRelation bac05ReportDetail, RemarkSequence remarkSeq, Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap) {
        String bac05 = XlsUtilService.getNameByLanguage(bac05ReportDetail.getNodeConfig().getDescriptionTh(), bac05ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Bac05", xlsUtilService.addRemarkSeq(bac05,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.BAC05),
                locale,
                isExcel
        ));
        List<ExportYearStructureUnit> exportBusinessAndCorporatePolicyList = new ArrayList<>();
        exportBusinessAndCorporatePolicyList.add(
                new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question1", null, locale), locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.isNameThIsChanged() : overallBusinessCorporatePolicyResponse.isNameEnIsChanged(), changed, locale)
                        , locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.getNameTh() : overallBusinessCorporatePolicyResponse.getNameEn()));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question17", null, locale), overallBusinessCorporatePolicyResponse.isSymbolIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getSymbol()));
        exportBusinessAndCorporatePolicyList.add(
                new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question3", null, locale), locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.isAddressThIsChanged() : overallBusinessCorporatePolicyResponse.isAddressEnIsChanged(), changed, locale)
                        , locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.getAddressTh() : overallBusinessCorporatePolicyResponse.getAddressEn()));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question5", null, locale), overallBusinessCorporatePolicyResponse.isProvinceIsChanged(), changed, locale), checkProvinceNull(overallBusinessCorporatePolicyResponse.getProvince(), locale)));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question6", null, locale), overallBusinessCorporatePolicyResponse.isZipCodeIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getZipCode()));
        exportBusinessAndCorporatePolicyList.add(
                new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question7", null, locale), locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.isBusinessTypeThIsChanged() : overallBusinessCorporatePolicyResponse.isBusinessTypeEnIsChanged(), changed, locale)
                        , locale.equals(Locale.forLanguageTag(Constants.TH)) ? overallBusinessCorporatePolicyResponse.getBusinessTypeTh() : overallBusinessCorporatePolicyResponse.getBusinessTypeEn()));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question9", null, locale), overallBusinessCorporatePolicyResponse.isJuristicRegistrationNoIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getJuristicRegistrationNo()));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question10", null, locale), overallBusinessCorporatePolicyResponse.isTelephoneIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getTelephone()));

        if (overallBusinessCorporatePolicyResponse.getFax() != null && !overallBusinessCorporatePolicyResponse.getFax().isBlank()) {
            exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question11", null, locale), overallBusinessCorporatePolicyResponse.isFaxIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getFax()));
        }

        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question12", null, locale), overallBusinessCorporatePolicyResponse.isWebsiteIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getWebsite()));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question13", null, locale), overallBusinessCorporatePolicyResponse.isEmailIsChanged(), changed, locale), overallBusinessCorporatePolicyResponse.getEmail()));

        parameters.put("OverallBusinessCorporatePolicyList", new JRBeanCollectionDataSource(exportBusinessAndCorporatePolicyList.stream().filter(o -> !checkFieldIsNull(o.getAsOfYear())).collect(Collectors.toList())));
    }


    private void prepareExportOverallBusinessCorporateShareList(OverallBusinessCorporatePolicyResponse overallBusinessCorporatePolicyResponse, Map<String, Object> parameters, Locale locale, Boolean changed) {
        List<ExportYearStructureUnit> exportBusinessAndCorporatePolicyList = new ArrayList<>();
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question15", null, locale), overallBusinessCorporatePolicyResponse.isCommonPaidupShareIsChanged(), changed, locale),
                checkNullSetDefaultNull(overallBusinessCorporatePolicyResponse.getCommonPaidupShare(), df)));
        exportBusinessAndCorporatePolicyList.add(new ExportYearStructureUnit(utilService.checkChanged(messageSource.getMessage("main_node1.node1.section1.topic5.question16", null, locale), overallBusinessCorporatePolicyResponse.isPreferredPaidupShareIsChanged(), changed, locale),
                checkNullSetDefaultNull(overallBusinessCorporatePolicyResponse.getPreferredPaidupShare(), df)));

        parameters.put("OverallBusinessCorporateShareList", new JRBeanCollectionDataSource(exportBusinessAndCorporatePolicyList.stream().filter(o -> !checkFieldIsNull(o.getAsOfYear())).collect(Collectors.toList())));

    }


    private void prepareAdditionalImgInfo(List<ImageInfoResponse> additionalImgInfoList,
                                          Map<String, Object> parameters, Locale locale, RemarkSequence remarkSeq,
                                          Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap, Boolean isExcel,
                                          String reportPath) {
        if (!additionalImgInfoList.isEmpty() && additionalImgInfoList.stream().anyMatch(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)) {
            parameters.put("AdditionalImgInfoTitle", messageSource.getMessage("main_node1.node1.session1.topic6",
                    null, locale));
            List<ExportImage> exportImages = additionalImgInfoList.stream()
                    .filter(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)
                    .map(img -> XlsUtilService.getExportImage(locale, img, reportPath)).collect(Collectors.toList());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportImages);
            parameters.put("AdditionalImgInfoList", dataSource);
        } else {
            parameters.put("AdditionalImgInfoTitle", null);
            parameters.put("AdditionalImgInfoList" , null);
        }
    }

    private static String checkProvinceNull(Province province, Locale locale) {
        if (province != null) {
            if (locale.equals(Locale.forLanguageTag(Constants.TH))) {
                return province.getProvinceTh();
            } else {
                return province.getProvinceEn();
            }
        }
        return null;
    }
}

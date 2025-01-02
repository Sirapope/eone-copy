package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import th.or.set.setportal.eonereport.bean.*;
import th.or.set.setportal.eonereport.bean.export.*;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.bean.response.*;
import th.or.set.setportal.eonereport.config.Constants;
import th.or.set.setportal.eonereport.constants.IntroductionSection;
import th.or.set.setportal.eonereport.constants.RemarkSection;
import th.or.set.setportal.eonereport.constants.ShareholderStructureType;
import th.or.set.setportal.eonereport.model.ReportDetail;
import th.or.set.setportal.eonereport.model.ReportDetailNoRelation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static th.or.set.setportal.eonereport.config.Constants.*;
import static th.or.set.setportal.eonereport.service.UtilService.checkFieldIsNull;
import static th.or.set.setportal.eonereport.service.UtilService.getReportPath;
import static th.or.set.setportal.eonereport.service.XlsUtilService.*;

@Service
@Slf4j
public class ExportShareholdingStructureService {
    @Autowired
    private ShareholdingStructureService shareholdingStructureService;
    @Autowired
    private ReportDetailService reportDetailService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UtilService utilService;
    @Autowired
    private XlsUtilService xlsUtilService;

    private static String JASPER_FOLDER_NAME = "1-3_ShareholdingStructure";
    private static String JASPER_FILE_NAME = "ShareholdingStructure";

    public JasperPrint exportReport(ReportDetail reportDetail, String language, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq) {
        try {
            return generateShareholdingStructure(reportDetail.getReport().getAsOfYear()
                    , Locale.forLanguageTag(language)
                    , changed
                    , currentStartPage
                    , isExcel
                    , isWord
                    , phase
                    , remarkSeq
                    , reportDetail);
        } catch (Exception e) {
            log.error("Error export ShareholdingStructure", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    public JasperPrint exportReport(String language, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, DataStructureResponse dataStructureResponse, Long year, double phase, RemarkSequence remarkSeq, ReportDetail reportDetail) {
        try {

            return generateShareholdingStructure(dataStructureResponse
                    , year
                    , Locale.forLanguageTag(language)
                    , changed
                    , currentStartPage
                    , isExcel
                    , isWord
                    , phase
                    , remarkSeq
                    , reportDetail);
        } catch (Exception e) {
            log.error("Error export ShareholdingStructure", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    private JasperPrint generateShareholdingStructure(Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq, ReportDetail reportDetail) throws IOException, JRException {

        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase, (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        Map<String, Object> parameters = new HashMap<String, Object>();
        UtilService.setResourceBundle(parameters, locale);

        UtilService.addYearStructure(parameters, asOfYear, locale);
        parameters.put("Language", locale.getLanguage());
        parameters.put("FirstPage", currentStartPage);
        parameters.put("IsExcel", isExcel);
        parameters.put("Phase", phase);
        parameters.put("SUBREPORT_DIR", UtilService.getTemplatePath(phase));
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        xlsUtilService.generatePageTitle(reportDetail, locale, parameters, remarkSeq, isExcel, isWord);

        // Prepare data for report
        ExportShareholdingStructure shareholdingStructure = new ExportShareholdingStructure();
        shareholdingStructure.setOperationalOrganizationPolicy(shareholdingStructureService.getOperationalOrganizationPolicy(reportDetail));
        shareholdingStructure.setDiagramImgInfos(shareholdingStructureService.getShareholdingDiagramImage(reportDetail));
        shareholdingStructure.setShareholderStructureInfos(shareholdingStructureService.getAllShareholderStructureInfo(reportDetail));
        shareholdingStructure.setTenPercentInfos(shareholdingStructureService.getTenPercentShareholderInfo(reportDetail));
        shareholdingStructure.setPotentialConflictShareholder(shareholdingStructureService.getPotentialConflictShareholder(reportDetail));
        shareholdingStructure.setMajorShareholderRelationship(shareholdingStructureService.getMajorShareholderRelationship(reportDetail));
        shareholdingStructure.setMajorShareholderRelationshipImgInfos(shareholdingStructureService.getMajorShareholderRelationshipImage(reportDetail));
        shareholdingStructure.setMajorShareholderInfos(shareholdingStructureService.getMajorShareholderInfos(reportDetail));
        shareholdingStructure.setShareholderAgreement(shareholdingStructureService.getShareholderAgreement(reportDetail));

        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap = xlsUtilService.getRemarkSectionRemarkDataMap(reportDetail, phase, REMARK_SECTION_SHAREHOLDING_STRUCTURE);
        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap = xlsUtilService.getIntroductionSectionIntroductionDataMap(reportDetail, phase, INTRODUCTION_SECTION_SHAREHOLDING_STRUCTURE);

        // 1.3.1
        prepareOperationalOrganizationPolicy(shareholdingStructure.getOperationalOrganizationPolicy(), parameters,
                locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareDiagramImgInfos(shareholdingStructure.getDiagramImgInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareShareholderStructureInfos(shareholdingStructure.getShareholderStructureInfos(), parameters, locale,
                changed, remarkSeq, remarkSectionRemarkDataMap, isExcel,isWord, reportDetail);
        prepareTenPercentInfos(shareholdingStructure.getTenPercentInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, introductionSectionIntroductionDataMap, isExcel, reportDetail);
        // 1.3.2
        preparePotentialConflictShareholder(shareholdingStructure.getPotentialConflictShareholder(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        // 1.3.3
        prepareMajorShareholderRelationship(shareholdingStructure.getMajorShareholderRelationship(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareMajorShareholderRelationshipImgInfos(shareholdingStructure.getMajorShareholderRelationshipImgInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, reportDetail);
        // 1.3.4
        prepareMajorShareholderInfos(shareholdingStructure.getMajorShareholderInfos(), parameters, locale, changed,
                remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareShareholderAgreement(shareholdingStructure.getShareholderAgreement(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);

        xlsUtilService.prepareRemarkSequencePdf(locale, parameters, REMARK_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS, changed, remarkSeq, isExcel, isWord);
        xlsUtilService.prepareIntroductionPdf(locale, parameters, introductionSectionIntroductionDataMap, INTRODUCTION_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS, changed, isExcel, isWord);

        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }

    private JasperPrint generateShareholdingStructure(DataStructureResponse dataStructureResponse, Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSeq, ReportDetail reportDetail) throws IOException, JRException {

        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase, (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        Map<String, Object> parameters = new HashMap<String, Object>();
        UtilService.setResourceBundle(parameters, locale);

        UtilService.addYearStructure(parameters, asOfYear, locale);
        parameters.put("Language", locale.getLanguage());
        parameters.put("FirstPage", currentStartPage);
        parameters.put("IsExcel", isExcel);
        parameters.put("Phase", phase);
        parameters.put("SUBREPORT_DIR", UtilService.getTemplatePath(phase));
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        xlsUtilService.generatePageTitle(reportDetail, locale, parameters, remarkSeq, isExcel, isWord);

        // Prepare data for report
        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap = xlsUtilService.getRemarkSectionRemarkDataMap(reportDetail, phase, REMARK_SECTION_SHAREHOLDING_STRUCTURE);
        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap = xlsUtilService.getIntroductionSectionIntroductionDataMap(reportDetail, phase, INTRODUCTION_SECTION_SHAREHOLDING_STRUCTURE);

        ExportShareholdingStructure shareholdingStructure = dataStructureResponse.getExportShareholdingStructure();
        // 1.3.1
        prepareOperationalOrganizationPolicy(shareholdingStructure.getOperationalOrganizationPolicy(), parameters,
                locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareDiagramImgInfos(shareholdingStructure.getDiagramImgInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareShareholderStructureInfos(shareholdingStructure.getShareholderStructureInfos(), parameters, locale,
                changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareTenPercentInfos(shareholdingStructure.getTenPercentInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, introductionSectionIntroductionDataMap, isExcel, reportDetail);
        // 1.3.2
        preparePotentialConflictShareholder(shareholdingStructure.getPotentialConflictShareholder(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        // 1.3.3
        prepareMajorShareholderRelationship(shareholdingStructure.getMajorShareholderRelationship(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareMajorShareholderRelationshipImgInfos(shareholdingStructure.getMajorShareholderRelationshipImgInfos(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, reportDetail);
        // 1.3.4
        prepareMajorShareholderInfos(shareholdingStructure.getMajorShareholderInfos(), parameters, locale, changed,
                remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);
        prepareShareholderAgreement(shareholdingStructure.getShareholderAgreement(), parameters, locale, changed, remarkSeq, remarkSectionRemarkDataMap, isExcel, isWord, reportDetail);

        xlsUtilService.prepareRemarkSequencePdf(locale, parameters, REMARK_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS, changed, remarkSeq, isExcel, isWord);
        xlsUtilService.prepareIntroductionPdf(locale, parameters, introductionSectionIntroductionDataMap, INTRODUCTION_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS, changed, isExcel, isWord);
        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }


    private void prepareOperationalOrganizationPolicy(OpOrgPolicy opOrgPolicy, Map<String, Object> parameters, Locale locale,
                                                      Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                                      boolean isExcel,boolean isWord, ReportDetail reportDetail) {
        String shareholdingStructure = getNameByLanguage(reportDetail.getNodeConfig().getDescriptionTh(), reportDetail.getNodeConfig().getDescriptionEn(), locale);

        parameters.put("ShareholdingStructure", xlsUtilService.addRemarkSeq(shareholdingStructure,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SHAREHOLDING_STRUCTURE),
                locale,
                isExcel
        ));
        ReportDetailNoRelation ss01ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), SS_01);
        String ss01 = getNameByLanguage(ss01ReportDetail.getNodeConfig().getDescriptionTh(), ss01ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Ss01", xlsUtilService.addRemarkSeq(ss01,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SS01),
                locale,
                isExcel
        ));

        parameters.put("OperationalOrganizationPolicyTitle", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section3.topic1.question1", null, locale),
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.OPERATIONAL_ORGANIZATION_POLICY),
                locale,
                isExcel
        ));
        parameters.put("OperationalOrganizationPolicy",
                XlsUtilService.getHtmlByLanguage(opOrgPolicy.getDescriptionTh(), opOrgPolicy.getDescriptionEn(),
                        locale, isWord));

        parameters.put("ShareholdingDiagramTitle", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section3.topic1.question2", null, locale),
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SHAREHOLDING_DIAGRAM),
                locale,
                isExcel
        ));
    }
    private void prepareDiagramImgInfos(List<ShareholdingDiagramImgInfoResponse> diagramImgInfos,
                                        Map<String, Object> parameters, Locale locale, Boolean changed,
                                        RemarkSequence remarkSeq,
                                        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap, Boolean isExcel,
                                        Boolean isWord, ReportDetail reportDetail) {
        String label = messageSource.getMessage("main_node1.node1.section3.topic1.question3", null, locale);
        String reportPath = getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());

        List<ExportImage> exportImages = diagramImgInfos.stream()
                .filter(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)
                .map(diagramImgInfo -> {
                    return getExportImage(locale, diagramImgInfo, reportPath);
                }).collect(Collectors.toList());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportImages);
        parameters.put("DiagramImgInfos", dataSource);

    }


    private void prepareShareholderStructureInfos(List<ShareholderStructureInfoResponse> shareholderStructureInfos,
                                                  Map<String, Object> parameters, Locale locale, Boolean changed,
                                                  RemarkSequence remarkSeq,
                                                  Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                                  Boolean isExcel, Boolean isWord, ReportDetail reportDetail) {
        List<ExportShareholderStructureInfo> subsidiaries = new ArrayList<>();
        mapShareholderStructureInfo(shareholderStructureInfos, locale, subsidiaries,
                ShareholderStructureType.SUBSIDIARIES, isWord);
        setExportShareholderStructureInfoFirstAndLast(subsidiaries);
        parameters.put("Subsidiaries", new JRBeanCollectionDataSource(subsidiaries));

        List<ExportShareholderStructureInfo> associatedCompanies = new ArrayList<>();
        mapShareholderStructureInfo(shareholderStructureInfos, locale, associatedCompanies, ShareholderStructureType.ASSOCIATED_COMPANIES, isWord);
        setExportShareholderStructureInfoFirstAndLast(associatedCompanies);
        parameters.put("AssociatedCompanies", new JRBeanCollectionDataSource(associatedCompanies));

        List<ExportShareholderStructureInfo> jointVentureCompanies = new ArrayList<>();
        mapShareholderStructureInfo(shareholderStructureInfos, locale, jointVentureCompanies, ShareholderStructureType.JOINT_VENTURE_COMPANIES, isWord);
        setExportShareholderStructureInfoFirstAndLast(jointVentureCompanies);
        parameters.put("JointVentureCompanies", new JRBeanCollectionDataSource(jointVentureCompanies));
    }

    private void mapShareholderStructureInfo(List<ShareholderStructureInfoResponse> shareholderStructureInfos,
                                             Locale locale, List<ExportShareholderStructureInfo> subsidiaries,
                                             ShareholderStructureType shareholderStructureType, boolean isWord) {
        shareholderStructureInfos.stream()
                .filter(x -> x.getType() == shareholderStructureType)
                .sorted(Comparator.comparing(ShareholderStructureInfoResponse::getSequence))
                .forEach(shareholderStructureInfo -> {
                    ExportShareholderStructureInfo exportShareholderStructureInfo = new ExportShareholderStructureInfo();
                    String question = XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(shareholderStructureInfo.getCompanyNameTh(), shareholderStructureInfo.getCompanyNameEn(), locale));
                    if (TH.equalsIgnoreCase(locale.getLanguage())){
                        question = Thaicut.wordwrap(question, 29, isWord);
                    }
                    exportShareholderStructureInfo.setQuestion(XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(shareholderStructureInfo.getCompanyNameTh(), shareholderStructureInfo.getCompanyNameEn(), locale)));
                    List<ExportYearStructureUnit> juristicPersons = new ArrayList<>();
                    mapShareholderStructureInfo(locale, shareholderStructureInfo, juristicPersons, isWord);

                    if (shareholderStructureInfo.getJuristicPerson().isEmpty()) {
                        juristicPersons.add(new ExportYearStructureUnit());
                    }
                    setFirstAndLast(juristicPersons);

                    juristicPersons.forEach(juristicPerson -> {
                        juristicPerson.setQuestion(exportShareholderStructureInfo.getQuestion());
                    });

                    exportShareholderStructureInfo.setJuristicPersons(new JRBeanCollectionDataSource(juristicPersons));
                    subsidiaries.add(exportShareholderStructureInfo);
                });
    }

    private static void mapShareholderStructureInfo(Locale locale,
                                                    ShareholderStructureInfoResponse shareholderStructureInfo,
                                                    List<ExportYearStructureUnit> subsidiaries, boolean isWord) {
        shareholderStructureInfo.getJuristicPerson().forEach(juristicPerson -> {
            ExportYearStructureUnit exportYearStructureUnit = new ExportYearStructureUnit();
            String unit = getNameByLanguage(juristicPerson.getNameTh(),juristicPerson.getNameEn(), locale);
            if (TH.equalsIgnoreCase(locale.getLanguage())){
                unit = Thaicut.wordwrap(unit, 25, isWord);
            }
            exportYearStructureUnit.setUnit(checkNullSetDefaultBlank(unit));
            String asOfYesteryear = checkNullSetDefaultBlank(juristicPerson.getShareholdingProportion(), Constants.df_2);
            if (!N_A.equals(asOfYesteryear)) {
                asOfYesteryear = asOfYesteryear + "%";
            }
            exportYearStructureUnit.setAsOfYesteryear(asOfYesteryear);
            String asOfYear = checkNullSetDefaultBlank(juristicPerson.getVotingRights(), Constants.df_2);
            if (!N_A.equals(asOfYear)) {
                asOfYear = asOfYear + "%";
            }
            exportYearStructureUnit.setAsOfYear(asOfYear);
            subsidiaries.add(exportYearStructureUnit);
        });
    }

    private void prepareTenPercentInfos(List<TenPercentInfoResponse> tenPercentInfos,
                                        Map<String, Object> parameters, Locale locale,
                                        Boolean changed, RemarkSequence remarkSeq,
                                        Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap, Boolean isExcel, ReportDetail reportDetail) {
        List<ExportYearStructureUnit> yearStructureUnits = tenPercentInfos.stream()
                .map(tenPercentInfo -> {
                    ExportYearStructureUnit structureUnit = new ExportYearStructureUnit();
                    String address =
                            XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(tenPercentInfo.getCompanyNameTh(), tenPercentInfo.getCompanyNameEn(), locale));
                    if (XlsUtilService.getNameByLanguage(tenPercentInfo.getAddressTh(), tenPercentInfo.getAddressEn(), locale) != null){
                        address += "\n" + XlsUtilService.getNameByLanguage(tenPercentInfo.getAddressTh(), tenPercentInfo.getAddressEn(), locale);
                    }
                    if (tenPercentInfo.getProvince() != null){
                        address += " ";
                        if (TH.equalsIgnoreCase(locale.getLanguage())){
                            address += messageSource.getMessage("main_node1.node1.section1.topic5.question5", null,
                                    locale);
                        }
                        address += XlsUtilService.getNameByLanguage(tenPercentInfo.getProvince().getProvinceTh(),tenPercentInfo.getProvince().getProvinceEn(), locale);
                    }
                    if (tenPercentInfo.getZipCode() != null){
                        address += " " + tenPercentInfo.getZipCode();
                    }
                    String telephone = messageSource.getMessage("telephone", null, locale) + " : " + XlsUtilService.checkNullSetDefaultDash(tenPercentInfo.getTelephone());
                    String fax = messageSource.getMessage("fax", null, locale) + " : " + XlsUtilService.checkNullSetDefaultDash(tenPercentInfo.getFax());
                    address += "\n" + telephone + "\n" + fax;
                    if (TH.equalsIgnoreCase(locale.getLanguage())){
                        address = Thaicut.wordwrap(address, 30, isExcel);
                    }
                    structureUnit.setQuestion(address);
                    String unit = getNameByLanguage(tenPercentInfo.getBusinessTypeTh(), tenPercentInfo.getBusinessTypeEn(), locale);
                    if (TH.equalsIgnoreCase(locale.getLanguage())){
                        unit = Thaicut.wordwrap(unit, 30, isExcel);
                    }
                    structureUnit.setUnit(checkNullSetDefaultBlank(unit));
                    if (tenPercentInfo.getSecurityOfferingType() != null){
                        structureUnit.setAsOfYear(XlsUtilService.getNameByLanguage(tenPercentInfo.getSecurityOfferingType().getNameTh(), tenPercentInfo.getSecurityOfferingType().getNameEn(), locale));
                    }
                    structureUnit.setAsOfYesteryear(checkNullSetDefaultBlank(tenPercentInfo.getShare(), Constants.df));
                    structureUnit.setAsOfYearBeforeYesteryear(checkNullSetDefaultBlank(tenPercentInfo.getPaidUpShare(), Constants.df));
                    return structureUnit;
                }).collect(Collectors.toList());
        setFirstAndLast(yearStructureUnits);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(yearStructureUnits);
        parameters.put("TenPercentInfos", dataSource);
        parameters.put("TenPercentInfoTitle", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section3.topic1.question11", null, locale),
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.TEN_PERCENT),
                locale,
                isExcel
        ));
        parameters.put("ShowTenPercentInfos", !dataSource.getData().isEmpty()
                || remarkSectionRemarkDataMap.get(RemarkSection.TEN_PERCENT) != null
                || introductionSectionIntroductionDataMap.get(IntroductionSection.TEN_PERCENT) != null);
    }

    private void preparePotentialConflictShareholder(PotConfShareholder potentialConflictShareholder, Map<String,
            Object> parameters, Locale locale, Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection,
            RemarkData> remarkSectionRemarkDataMap, Boolean isExcel, Boolean isWord, ReportDetail reportDetail) {
        ReportDetailNoRelation ss02ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), SS_02);
        String ss02 = getNameByLanguage(ss02ReportDetail.getNodeConfig().getDescriptionTh(), ss02ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Ss02", xlsUtilService.addRemarkSeq(ss02,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SS02),
                locale,
                isExcel
        ));
        ExportYearStructureUnit exportYearStructure = new ExportYearStructureUnit();
        exportYearStructure.setQuestion(messageSource.getMessage("main_node1.node1.section3.topic1.question17", null, locale));
        exportYearStructure.setAsOfYear(xlsUtilService.checkPolicyNull(potentialConflictShareholder.getPotentialConflictShareholder(), locale));
        if (exportYearStructure.getAsOfYear() != null) {
            parameters.put("PotConfShareholder", new JRBeanCollectionDataSource(List.of(exportYearStructure)));
            parameters.put("PotConfShareholderDescription",
                    getHtmlByLanguage(potentialConflictShareholder.getDescriptionTh(),
                            potentialConflictShareholder.getDescriptionEn(), locale, isWord));
        }
    }

    private void prepareMajorShareholderRelationship(MajorShareholderRel majorShareholderRelationship, Map<String,
            Object> parameters, Locale locale, Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection,
            RemarkData> remarkSectionRemarkDataMap, Boolean isExcel, Boolean isWord, ReportDetail reportDetail) {
        ReportDetailNoRelation ss03ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), SS_03);
        String ss03 = getNameByLanguage(ss03ReportDetail.getNodeConfig().getDescriptionTh(), ss03ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Ss03", xlsUtilService.addRemarkSeq(ss03,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SS03),
                locale,
                isExcel
        ));
        ExportYearStructureUnit exportYearStructure = new ExportYearStructureUnit();
        exportYearStructure.setQuestion(messageSource.getMessage("main_node1.node1.section3.topic1.question18", null, locale));
        exportYearStructure.setAsOfYear(xlsUtilService.checkPolicyNull(majorShareholderRelationship.getMajorShareholderRelationship(), locale));
        if (exportYearStructure.getAsOfYear() != null) {
            parameters.put("MajorShareholderRel", new JRBeanCollectionDataSource(List.of(exportYearStructure)));
            parameters.put("MajorShareholderRelDescription",
                    getHtmlByLanguage(majorShareholderRelationship.getDescriptionTh(),
                            majorShareholderRelationship.getDescriptionEn(), locale, isWord));
        }
    }

    private void prepareMajorShareholderInfos(List<MajorShareholderInfoResponse> majorShareholderInfos, Map<String,
            Object> parameters, Locale locale, Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection,
            RemarkData> remarkSectionRemarkDataMap, Boolean isExcel,Boolean isWord, ReportDetail reportDetail) {
        ReportDetailNoRelation ss04ReportDetail = reportDetailService.getReportDetailByName(reportDetail.getReport().getId(), SS_04);
        String ss04 = getNameByLanguage(ss04ReportDetail.getNodeConfig().getDescriptionTh(), ss04ReportDetail.getNodeConfig().getDescriptionEn(), locale);
        parameters.put("Ss04", xlsUtilService.addRemarkSeq(ss04,
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SS04),
                locale,
                isExcel
        ));
        parameters.put("MajorShareholdersTitle", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section3.topic1.question21", null, locale),
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.MAJOR_SHAREHOLDERS),
                locale,
                isExcel
        ));
        parameters.put("ShareholderAgreementTitle", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section3.topic1.question25", null, locale),
                remarkSeq,
                remarkSectionRemarkDataMap.get(RemarkSection.SHAREHOLDER_AGREEMENT),
                locale,
                isExcel
        ));

        List<ExportYearStructureUnit> majorShareholder = new ArrayList<>();

        List<MajorShareholderInfoResponse> parents = majorShareholderInfos.stream()
                .filter(x -> x.getParentId() == null)
                .filter(x -> {
                    if (TH.equals(locale.getLanguage())) {
                        return !checkFieldIsNull(x.getNameTh()) || x.getPercentShares() != null || x.getShares() != null;
                    } else {
                        return !checkFieldIsNull(x.getNameEn()) || x.getPercentShares() != null || x.getShares() != null;
                    }
                })
                .sorted(Comparator.comparing(MajorShareholderInfoResponse::getSequence)).collect(Collectors.toList());
        Map<Long, List<MajorShareholderInfoResponse>> childes = majorShareholderInfos.stream()
                .filter(x -> x.getParentId() != null)
                .filter(x -> {
                    if (TH.equals(locale.getLanguage())) {
                        return !checkFieldIsNull(x.getNameTh()) || x.getPercentShares() != null || x.getShares() != null;
                    } else {
                        return !checkFieldIsNull(x.getNameEn()) || x.getPercentShares() != null || x.getShares() != null;
                    }
                })
                .collect(Collectors.groupingBy(MajorShareholderInfoResponse::getParentId));

        for (MajorShareholderInfoResponse parent : parents) {
            majorShareholder.add(getMajorShareholderInfo(locale, parent, parent.getSequence().toString(), isWord));

            // child
            List<MajorShareholderInfoResponse> child = childes.get(parent.getId());
            if (child != null) {
                child.stream().sorted(Comparator.comparing(MajorShareholderInfoResponse::getSequence))
                        .forEach(majorShareholderInfo -> {
                            majorShareholder.add(getMajorShareholderInfo(locale, majorShareholderInfo,
                                    parent.getSequence() + "." + majorShareholderInfo.getSequence(), isWord));
                        });
            }
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(majorShareholder);
        setFirstAndLast(majorShareholder);
        parameters.put("MajorShareholderInfos", dataSource);
    }

    private static ExportYearStructureUnit getMajorShareholderInfo(Locale locale, MajorShareholderInfoResponse parent
            , String seq, boolean isWord) {
        ExportYearStructureUnit structureUnit = new ExportYearStructureUnit();
        String question = seq + ". " + XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(parent.getNameTh(), parent.getNameEn(), locale));
        if (TH.equalsIgnoreCase(locale.getLanguage())){
            question = Thaicut.wordwrap(question, 75, isWord);
        }
        structureUnit.setQuestion(question);
        structureUnit.setUnit(XlsUtilService.checkNullSetDefaultBlank(parent.getShares(), Constants.df));
        structureUnit.setAsOfYear(XlsUtilService.checkNullSetDefaultBlank(parent.getPercentShares(), df_2));
        structureUnit.setIndent(parent.getIndent());
        return structureUnit;
    }

    private void prepareShareholderAgreement(ShareholderAgreement shareholderAgreement,
                                             Map<String, Object> parameters, Locale locale, Boolean changed,
                                             RemarkSequence remarkSeq,
                                             Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                             Boolean isExcel, Boolean isWord, ReportDetail reportDetail) {
        ExportYearStructureUnit exportYearStructure = new ExportYearStructureUnit();
        exportYearStructure.setQuestion(messageSource.getMessage("main_node1.node1.section3.topic1.question26", null, locale));
        exportYearStructure.setAsOfYear(xlsUtilService.checkPolicyNull(shareholderAgreement.getShareholderAgreement(), locale));
        if (exportYearStructure.getAsOfYear() != null) {
            parameters.put("ShareholderAgreement", new JRBeanCollectionDataSource(List.of(exportYearStructure)));
            parameters.put("ShareholderAgreementDescription",
                    getHtmlByLanguage(shareholderAgreement.getDescriptionTh(),
                            shareholderAgreement.getDescriptionEn(), locale, isWord));
        }
    }


    public static void setExportShareholderStructureInfoFirstAndLast(List<ExportShareholderStructureInfo> exportYearStructures) {
        if (exportYearStructures != null && !exportYearStructures.isEmpty()) {
            exportYearStructures.get(0).setFirst(true);
            exportYearStructures.get(exportYearStructures.size() - 1).setLast(true);
        }
    }


    private void prepareMajorShareholderRelationshipImgInfos(List<MajorShareholderRelationshipImgInfoResponse> majorShareholderRelationshipImgInfos, Map<String, Object> parameters, Locale locale, Boolean changed, RemarkSequence remarkSeq, Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap, Boolean isExcel, ReportDetail reportDetail) {
        String reportPath = getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());

        List<ExportImage> exportImages = majorShareholderRelationshipImgInfos.stream()
                .filter(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)
                .map(majorShareholderRelationshipImgInfo -> {
                    return getExportImage(locale, majorShareholderRelationshipImgInfo, reportPath);
                }).collect(Collectors.toList());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportImages);
        parameters.put("MajorShareholderRelationshipImgInfos", dataSource);
    }
}

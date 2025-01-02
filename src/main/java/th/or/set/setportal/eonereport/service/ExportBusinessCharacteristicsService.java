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
import th.or.set.setportal.eonereport.bean.export.phase3.IntroductionTable;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkTable;
import th.or.set.setportal.eonereport.bean.response.*;
import th.or.set.setportal.eonereport.config.Constants;
import th.or.set.setportal.eonereport.constants.AdditionalImgInfoType;
import th.or.set.setportal.eonereport.constants.InfoType;
import th.or.set.setportal.eonereport.constants.IntroductionSection;
import th.or.set.setportal.eonereport.constants.RemarkSection;
import th.or.set.setportal.eonereport.model.InfoTypeConfig;
import th.or.set.setportal.eonereport.model.ReportDetail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static th.or.set.setportal.eonereport.config.Constants.*;
import static th.or.set.setportal.eonereport.service.UtilService.*;
import static th.or.set.setportal.eonereport.service.XlsUtilService.*;

@Slf4j()
@Service
public class ExportBusinessCharacteristicsService {

    @Autowired
    private IncomeStructureService incomeStructureService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ReportDetailService reportDetailService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UtilService utilService;

    @Autowired
    private XlsUtilService xlsUtilService;


    @Autowired
    private OtherIncomeStructureService otherIncomeStructureService;

    private static final DecimalFormat df_2 = new DecimalFormat("#,##0.00");

    private static final DecimalFormat df = new DecimalFormat("#,##0");
    @Autowired
    private AdditionalImageInfoService additionalImageInfoService;

    private static String JASPER_FOLDER_NAME = "1-2_BusinessCharacteristics";
    private static String JASPER_FILE_NAME = "BusinessCharacteristics";
    @Autowired
    private IntroductionService introductionService;
    @Autowired
    private RemarkService remarkService;

    public JasperPrint exportReport(ReportDetail reportDetail, String language, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSequence) {
        try {
            return generateBusinessCharacteristics(
                    reportDetail
                    , Locale.forLanguageTag(language)
                    , changed
                    , currentStartPage
                    , isExcel
                    , isWord
                    , phase
                    , remarkSequence);
        } catch (Exception e) {
            log.error("Error export businessCharacteristics", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    public JasperPrint exportReport(String language, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, DataStructureResponse dataStructureResponse, Long year, double phase, RemarkSequence remarkSequence, ReportDetail reportDetail) {
        try {
            return generateBusinessCharacteristics(dataStructureResponse
                    , year
                    , Locale.forLanguageTag(language)
                    , changed
                    , currentStartPage
                    , isExcel
                    , isWord
                    , phase
                    , remarkSequence
                    , reportDetail);
        } catch (Exception e) {
            log.error("Error export businessCharacteristics", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    private JasperPrint generateBusinessCharacteristics(DataStructureResponse dataStructureResponse, Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSequence, ReportDetail reportDetail) throws IOException, JRException {

        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase, (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        Map<String, Object> parameters = getParameter(asOfYear, locale, changed, currentStartPage, isExcel, isWord, phase, remarkSequence, reportDetail, dataStructureResponse.getBusinessCharacteristicsResponse());
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }

    private Map<String, Object> getParameter(Long asOfYear, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSequence, ReportDetail reportDetail, BusinessCharacteristicsResponse businessCharacteristicsResponse) {
        //prepare data
        IncomeStructureResponse incomeStructureResponse = businessCharacteristicsResponse.getIncomeStructureResponse();
        ProductInfo productInfo = businessCharacteristicsResponse.getProductInfo();
        ShareProfitResponse shareProfitResponse = businessCharacteristicsResponse.getShareProfitResponse();
        OtherIncomeStructureResponse otherIncomeStructureResponse = businessCharacteristicsResponse.getOtherIncomeStructureResponse();

        Map<String, Object> parameters = new HashMap<>();

        Map<RemarkSection, RemarkData> remarkSectionRemarkMap = xlsUtilService.getRemarkSectionRemarkDataMap(reportDetail, phase, REMARK_SECTION_BUSINESS_CHARACTERISTICS);
        Map<IntroductionSection, IntroductionData> introductionDataMap = xlsUtilService.getIntroductionSectionIntroductionDataMap(reportDetail, phase, INTRODUCTION_SECTION_BUSINESS_CHARACTERISTICS);
        addRemark(locale, remarkSequence, parameters, isExcel, isWord, changed, remarkSectionRemarkMap, introductionDataMap);

        List<ExportYearStructureUnit> exportProductIncomeList =
                prepareExportIncomeList(incomeStructureResponse.getProductIncome(), locale, changed, Boolean.FALSE,
                        phase, isExcel, isWord);
        List<ExportYearStructureUnit> exportExportIncomeList = prepareExportIncomeList(incomeStructureResponse.getExportIncome(), locale, changed, Boolean.FALSE, phase, isExcel, isWord);
        List<ExportYearStructureUnit> exportProductIncomeRatioList = prepareExportIncomeList(incomeStructureResponse.getProductIncome(), locale, changed, Boolean.TRUE, phase, isExcel, isWord);
        List<ExportYearStructureUnit> exportExportIncomeRatioList = prepareExportIncomeList(incomeStructureResponse.getExportIncome(), locale, changed, Boolean.TRUE, phase, isExcel, isWord);
        List<ExportYearStructureUnit> productInfoStructures = prepareExportProductInfo(productInfo, locale, changed, phase, isExcel, isWord);
        List<ExportYearStructureUnit> shareProfitExportList = prepareShareProfitList(shareProfitResponse, locale, changed, phase, isExcel, isWord);
        List<ExportMarketPolicyInfo> productServiceInfoBusinessInnoDevList = prepareProductServiceInfoBusinessInnoDevList(businessCharacteristicsResponse.getProductServiceCharacteristicsList(), locale,
                businessCharacteristicsResponse.getProductOrServiceImgInfos(), reportDetail, isExcel, isWord);
        List<ExportYearStructureUnit> otherIncomeStructureList = prepareOtherIncomeStructureList(otherIncomeStructureResponse.getOperation(), locale, changed, phase, isExcel, isWord);
        List<ExportMarketPolicyInfo> marketPolicyInfoList = prepareMarketPolicyInfoList(businessCharacteristicsResponse.getMarketingPolicyInfoList(), businessCharacteristicsResponse.getMarketingPolicy(), locale, businessCharacteristicsResponse.getMarketingPolicyImgInfos(), reportDetail, isExcel, isWord, remarkSequence);
        List<ExportProcurementInfo> procurementInfoList = prepareProcurementInfoList(businessCharacteristicsResponse.getProcurementInfoList(), businessCharacteristicsResponse.getProcurement(), businessCharacteristicsResponse.getBusinessCharacteristics(), locale, isExcel, isWord);
        List<Export6Column> permanentAppraisalPriceList = preparePermanentAppraisalPriceList(businessCharacteristicsResponse.getPermanentAppraisalPriceList(), locale, isExcel, isWord);
        List<Export6Column> intangibleAppraisalPriceList = prepareIntangibleAppraisalPriceList(businessCharacteristicsResponse.getIntangibleAppraisalPriceList(), locale, isExcel, isWord);
        List<Export6Column> underConstructionProjectInfoList = prepareUnderConstructionProjectInfoList(businessCharacteristicsResponse.getUnderConstructionProjectInfoList(), locale, isExcel, isWord);
        List<Export3Column> underConstructionProjectDetail = prepareUnderConstructionProjectDetail(businessCharacteristicsResponse.getUnderConstructionProject(), locale, isExcel, isWord);

        otherIncomeStructureList.addAll(prepareOtherIncomeStructureList(otherIncomeStructureResponse.getNotOperation(), locale, changed, phase, isExcel, isWord));

        List<ExportBusinessAndCorporatePolicy> exportBusinessAndCorporatePolicyList3 = new ArrayList<>();
        exportBusinessAndCorporatePolicyList3.add(
                new ExportBusinessAndCorporatePolicy(
                        utilService.checkChanged(messageSource.getMessage("main_node1.node1.section2.topic2.question1", null, locale), productInfo.isResearchPolicyIsChanged(), changed, locale)
                        , checkNullSetDefault(productInfo.getResearchPolicy(), locale)));

        UtilService.setResourceBundle(parameters, locale);
        parameters.put("SUBREPORT_DIR", UtilService.getTemplatePath(phase));
        xlsUtilService.generatePageTitle(reportDetail, locale, parameters, remarkSequence, isExcel, isWord);

        parameters.put("BusinessAndCorporatePolicyList3", new JRBeanCollectionDataSource(exportBusinessAndCorporatePolicyList3));

        if (!exportProductIncomeList.isEmpty() || !exportProductIncomeRatioList.isEmpty()) {
            exportProductIncomeList.addAll(exportProductIncomeRatioList);
            parameters.put("ProductIncomeList", new JRBeanCollectionDataSource(
                    Collections.singletonList(
                            new ExportDataSource(
                                    new JRBeanCollectionDataSource(exportProductIncomeList)))));
        }
        prepareImgInfos(businessCharacteristicsResponse.getIncomeStructureImgInfos(), parameters, locale, reportDetail,
                "IncomeStructureImage");

        if (!exportExportIncomeList.isEmpty() || !exportExportIncomeRatioList.isEmpty()) {
            exportExportIncomeList.addAll(exportExportIncomeRatioList);
            parameters.put("ExportIncomeList", new JRBeanCollectionDataSource(
                    Collections.singletonList(
                            new ExportDataSource(
                                    new JRBeanCollectionDataSource(exportExportIncomeList)))));
        }
        if (!shareProfitExportList.isEmpty()) {
            parameters.put("ShareProfitList", new JRBeanCollectionDataSource(
                    Collections.singletonList(
                            new ExportDataSource(
                                    new JRBeanCollectionDataSource(shareProfitExportList)))));
        }
        if (!otherIncomeStructureList.isEmpty()) {
            parameters.put("OtherIncomeList", new JRBeanCollectionDataSource(
                    Collections.singletonList(
                            new ExportDataSource(
                                    new JRBeanCollectionDataSource(otherIncomeStructureList)))));
        }

        ExportYearStructureUnit researchPolicy = new ExportYearStructureUnit(messageSource.getMessage("main_node1.node1.section2.topic2.question3", null, locale),
                xlsUtilService.checkPolicyNull(productInfo.getResearchPolicy(), locale));
        if (researchPolicy.getAsOfYear() != null) {
            parameters.put("ResearchPolicy", new JRBeanCollectionDataSource(List.of(researchPolicy)));
        }
        parameters.put("ProductServiceInfoBusinessInnoDevList", new JRBeanCollectionDataSource(productServiceInfoBusinessInnoDevList));

        if (!productInfoStructures.isEmpty()) {
            parameters.put("ProductInfoList", new JRBeanCollectionDataSource(
                    Collections.singletonList(
                            new ExportDataSource(
                                    new JRBeanCollectionDataSource(productInfoStructures)))));
        }
        parameters.put("ProductInfoDescription", getHtmlByLanguage(productInfo.getDescriptionTh(),
                productInfo.getDescriptionEn(), locale, isWord));
        parameters.put("MarketPolicyInfoList", new JRBeanCollectionDataSource(marketPolicyInfoList));
        parameters.put("ProcurementList", new JRBeanCollectionDataSource(procurementInfoList));

        parameters.put("FixedAssets", getHtmlByLanguage(businessCharacteristicsResponse.getFixedAssets().getDescriptionTh(), businessCharacteristicsResponse.getFixedAssets().getDescriptionEn(), locale, isWord));
        parameters.put("IntangibleAssets", getHtmlByLanguage(businessCharacteristicsResponse.getIntangibleAssets().getDescriptionTh(), businessCharacteristicsResponse.getIntangibleAssets().getDescriptionEn(), locale, isWord));

        parameters.put("PermanentAppraisalPriceList", new JRBeanCollectionDataSource(permanentAppraisalPriceList));
        parameters.put("IntangibleAppraisalPriceList", new JRBeanCollectionDataSource(intangibleAppraisalPriceList));
        prepareBusinessImgInfos(businessCharacteristicsResponse.getIntangibleAppraisalPriceImgInfoList(), parameters, locale, reportDetail, "IntangibleAppraisalPriceImgInfoList");

        ExportYearStructureUnit subsidiariesInvestmentPolicy = new ExportYearStructureUnit(messageSource.getMessage("main_node1.node1.section2.topic2.question20", null, locale), xlsUtilService.checkPolicyNull(businessCharacteristicsResponse.getSubsidiariesInvestmentPolicy().getSubsidiariesInvestmentPolicy(), locale));
        if (subsidiariesInvestmentPolicy.getAsOfYear() != null) {
            parameters.put("SubsidiariesInvestmentPolicy", new JRBeanCollectionDataSource(List.of(subsidiariesInvestmentPolicy)));
        }
        parameters.put("SubsidiariesInvestmentPolicyDescription", getHtmlByLanguage(businessCharacteristicsResponse.getSubsidiariesInvestmentPolicy().getDescriptionTh(), businessCharacteristicsResponse.getSubsidiariesInvestmentPolicy().getDescriptionEn(), locale, isWord));
        if (businessCharacteristicsResponse.getUnderConstructionProject().getUnderConstructionProject() != null) {
            ExportYearStructureUnit underConstructionProjectPolicy = new ExportYearStructureUnit(messageSource.getMessage("main_node1.node1.section2.topic2.question22", null, locale), xlsUtilService.checkPolicyNull(businessCharacteristicsResponse.getUnderConstructionProject().getUnderConstructionProject(), locale));
            parameters.put("UnderConstructionProjectPolicy", new JRBeanCollectionDataSource(List.of(underConstructionProjectPolicy)));
            parameters.put("UnderConstructionProjectPolicyDescription", getHtmlByLanguage(businessCharacteristicsResponse.getUnderConstructionProject().getDescriptionTh(), businessCharacteristicsResponse.getUnderConstructionProject().getDescriptionEn(), locale, isWord));
            parameters.put("UnderConstructionProjectPolicyList", new JRBeanCollectionDataSource(underConstructionProjectInfoList));
            parameters.put("UnderConstructionProjectPolicyDetail", new JRBeanCollectionDataSource(underConstructionProjectDetail));
            prepareBusinessImgInfos(businessCharacteristicsResponse.getUnderConstructionProjectInfoImgInfoList(), parameters, locale, reportDetail, "UnderConstructionProjectInfoImgInfoList");
        }

        UtilService.addYearStructure(parameters, asOfYear, locale);
        parameters.put("FirstPage", currentStartPage);
        parameters.put("IsExcel", isExcel);

        parameters.put("Procurement", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question9", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC0203),
                locale,
                isExcel
        ));

        parameters.put("Asset", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question14", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC0204),
                locale,
                isExcel
        ));

        parameters.put("UnderConstructionProject", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question21", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC0205),
                locale,
                isExcel
        ));

        return parameters;
    }

    private void addRemark(Locale locale, RemarkSequence remarkSequence, Map<String, Object> parameters,
                           Boolean isExcel, Boolean isWord, boolean changed, Map<RemarkSection, RemarkData> remarkSectionRemarkMap,
                           Map<IntroductionSection, IntroductionData> introductionDataMap) {
       parameters.put("BusinessOperations", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BUSINESS_CHARACTERISTICS),
                locale,
                isExcel
        ));
        parameters.put("RevenueStructure", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic1", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC01),
                locale,
                isExcel
        ));

        parameters.put("ProductsServiceInfo", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC02),
                locale,
                isExcel
        ));
        parameters.put("ProductServiceInfoBusinessInnoDev", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question1", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC0201),
                locale,
                isExcel
        ));
        parameters.put("ResearchDevelopmentTopic", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question2", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.PRODUCT_INFO),
                locale,
                isExcel
        ));

        parameters.put("MarketPolicy", xlsUtilService.addRemarkSeq(
                messageSource.getMessage("main_node1.node1.section2.topic2.question6", null, locale),
                remarkSequence,
                remarkSectionRemarkMap.get(RemarkSection.BC0202),
                locale,
                isExcel
        ));


        xlsUtilService.prepareRemarkSequencePdf(locale, parameters, REMARK_BUSINESS_CHARACTERISTICS_SECTION, changed,
                remarkSequence, isExcel, isWord);
        xlsUtilService.prepareIntroductionPdf(locale, parameters, introductionDataMap, INTRODUCTION_BUSINESS_CHARACTERISTICS_SECTION, changed, isExcel, isWord);
    }

    private JasperPrint generateBusinessCharacteristics(ReportDetail reportDetail, Locale locale, Boolean changed, Integer currentStartPage, Boolean isExcel, Boolean isWord, double phase, RemarkSequence remarkSequence) throws IOException, JRException {
        InputStream inputStream = UtilService.getTemplate(isExcel, isWord, phase, (JASPER_FOLDER_NAME + File.separator + JASPER_FILE_NAME));
        //prepare data
        ReportDetail businessCharacteristicsReport = reportDetail;
        IncomeStructureResponse incomeStructureResponse = incomeStructureService.getIncomeStructure(businessCharacteristicsReport);
        ProductInfo productInfo = productInfoService.getProductInfo(businessCharacteristicsReport);
        ShareProfitResponse shareProfitResponse = otherIncomeStructureService.getShareProfit(businessCharacteristicsReport);
        OtherIncomeStructureResponse otherIncomeStructureResponse = otherIncomeStructureService.getOtherIncomeStructure(businessCharacteristicsReport);

        BusinessCharacteristicsResponse businessCharacteristicsResponse = new BusinessCharacteristicsResponse();
        businessCharacteristicsResponse.setIncomeStructureResponse(incomeStructureResponse);
        businessCharacteristicsResponse.setProductInfo(productInfo);
        businessCharacteristicsResponse.setOtherIncomeStructureResponse(otherIncomeStructureResponse);
        businessCharacteristicsResponse.setShareProfitResponse(shareProfitResponse);
        businessCharacteristicsResponse.setProductServiceCharacteristicsList(productInfoService.getProductServiceCharacteristics(businessCharacteristicsReport));
        businessCharacteristicsResponse.setMarketingPolicyInfoList(productInfoService.getMarketPolicyInfoWithDetail(businessCharacteristicsReport));
        businessCharacteristicsResponse.setMarketingPolicy(productInfoService.getMarketingPolicy(businessCharacteristicsReport));
        businessCharacteristicsResponse.setProcurement(productInfoService.getProcurement(businessCharacteristicsReport));
        businessCharacteristicsResponse.setProcurementInfoList(productInfoService.getProcurementInfoWithDetail(businessCharacteristicsReport));
        businessCharacteristicsResponse.setBusinessCharacteristics(productInfoService.getBusinessCharacteristics(businessCharacteristicsReport));
        businessCharacteristicsResponse.setFixedAssets(productInfoService.getFixedAssets(businessCharacteristicsReport));
        businessCharacteristicsResponse.setIntangibleAssets(productInfoService.getIntangibleAssets(businessCharacteristicsReport));
        businessCharacteristicsResponse.setPermanentAppraisalPriceList(productInfoService.getPermanentAppraisalPriceList(businessCharacteristicsReport));
        businessCharacteristicsResponse.setIntangibleAppraisalPriceList(productInfoService.getIntangibleAppraisalPriceList(businessCharacteristicsReport));
        businessCharacteristicsResponse.setIntangibleAppraisalPriceImgInfoList(productInfoService.getIntangibleAppraisalPriceImage(businessCharacteristicsReport));
        businessCharacteristicsResponse.setSubsidiariesInvestmentPolicy(productInfoService.getSubsidiariesInvestmentPolicy(businessCharacteristicsReport));
        businessCharacteristicsResponse.setUnderConstructionProject(productInfoService.getUnderConstructionProject(businessCharacteristicsReport));
        businessCharacteristicsResponse.setUnderConstructionProjectInfoList(productInfoService.getUnderConstructionProjectInfoList(businessCharacteristicsReport));
        businessCharacteristicsResponse.setUnderConstructionProjectInfoImgInfoList(productInfoService.getUnderConstructionProjectInfoImage(businessCharacteristicsReport));
        businessCharacteristicsResponse.setIncomeStructureImgInfos(additionalImageInfoService.getAdditionalImgInfo(businessCharacteristicsReport, AdditionalImgInfoType.IncomeStructure));
        businessCharacteristicsResponse.setProductOrServiceImgInfos(additionalImageInfoService.getAdditionalImgInfo(businessCharacteristicsReport, AdditionalImgInfoType.ProductOrService));
        businessCharacteristicsResponse.setMarketingPolicyImgInfos(additionalImageInfoService.getAdditionalImgInfo(businessCharacteristicsReport, AdditionalImgInfoType.MarketPolicy));
        Map<String, Object> parameters = getParameter(reportDetail.getReport().getAsOfYear(), locale, changed, currentStartPage, isExcel, isWord, phase, remarkSequence, reportDetail, businessCharacteristicsResponse);
        parameters.put("SUBREPORT_FOLDER", JASPER_FOLDER_NAME + File.separator);
        return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
    }

    private List<ExportYearStructureUnit> prepareExportIncomeList(List<IncomeStructure> incomeStructureList, Locale locale, Boolean changed, Boolean isRatio, double phase, Boolean isExcel, Boolean isWord) {

        List<ExportYearStructureUnit> productIncomeStructureList = new ArrayList<>();
        for (IncomeStructure i : incomeStructureList) {
            if (i.isEmptyDefaultExportIncome()) continue;
            if (i.getInfoTypeConfig() != null && "other".equals(i.getInfoTypeConfig().getName())) {
                //check value 3 year
                if (checkNull(i.getAsOfYear()) && checkNull(i.getAsOfYesteryear()) && checkNull(i.getAsOfYearBeforeYesteryear())) {
                    break;
                }
            }
            productIncomeStructureList.add(prepareExportIncome(i, locale, changed, isRatio, phase, isExcel, isWord));
        }
        return productIncomeStructureList;
    }

    private String getDefaultQuestionProportion(String nameTh, String nameEn, Locale locale) {
        nameTh = nameTh == null ? messageSource.getMessage("default_product_income", null, locale) : nameTh;
        nameEn = nameEn == null ? messageSource.getMessage("default_product_income", null, locale) : nameEn;
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ? nameTh + " (%)" : nameEn + " (%)";
    }

    private String getQuestionExportProportionCountry(String nameTh, String nameEn, Locale locale) {
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ? messageSource.getMessage("country", null, locale) + nameTh + " (%)" : nameEn + " (%)";
    }

    private String getQuestionByInfoTypeConfig1_5(Boolean isRatio, IncomeStructure incomeStructure, Locale locale) {
        if (isRatio) {
            if (incomeStructure.getInfoTypeConfig().getName().contains("total")
                    || incomeStructure.getInfoTypeConfig().getName().contains("other")
                    || incomeStructure.getInfoTypeConfig().getName().contains("domestic")
                    || incomeStructure.getInfoTypeConfig().getName().contains("international")
                    || InfoType.DEFAULT == incomeStructure.getInfoTypeConfig().getInfoType()) {
                return getDefaultQuestionProportion(incomeStructure.getInfoTypeConfig().getNameTh(), incomeStructure.getInfoTypeConfig().getNameEn(), locale);
            } else {
                return getQuestionExportProportionCountry(incomeStructure.getInfoTypeConfig().getNameTh(), incomeStructure.getInfoTypeConfig().getNameEn(), locale);
            }
        } else {
            if (incomeStructure.getInfoTypeConfig().getName().contains("total")
                    || incomeStructure.getInfoTypeConfig().getName().contains("other")
                    || incomeStructure.getInfoTypeConfig().getName().contains("domestic")
                    || incomeStructure.getInfoTypeConfig().getName().contains("international")
                    || InfoType.DEFAULT == incomeStructure.getInfoTypeConfig().getInfoType()) {
                return addDefaultThousandBaht(locale.equals(Locale.forLanguageTag(Constants.TH)) ? incomeStructure.getInfoTypeConfig().getNameTh() : incomeStructure.getInfoTypeConfig().getNameEn(), locale);
            } else {
                return addExportIncomeThousandBahtCountry(locale.equals(Locale.forLanguageTag(Constants.TH)) ? incomeStructure.getInfoTypeConfig().getNameTh() : incomeStructure.getInfoTypeConfig().getNameEn(), locale);
            }
        }
    }

    private String addDefaultThousandBaht(String question, Locale locale) {
        question = question == null ? messageSource.getMessage("default_product_income", null, locale) : question;
        return question + " " + messageSource.getMessage("thousand_baht", null, locale);
    }

    private String addExportIncomeThousandBahtCountry(String question, Locale locale) {
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                messageSource.getMessage("country", null, locale) + question + " " + messageSource.getMessage("thousand_baht", null, locale) :
                question + " " + messageSource.getMessage("thousand_baht", null, locale);
    }

    private ExportYearStructureUnit prepareExportIncome(IncomeStructure incomeStructure, Locale locale, Boolean changed, Boolean isRatio, double phase, Boolean isExcel, Boolean isWord) {
        //product income
        ExportYearStructureUnit exportIncomeStructure = new ExportYearStructureUnit();
        String question;

        if (incomeStructure.getInfoTypeConfig() == null) {
            if (isRatio) {
                question = getDefaultQuestionProportion(incomeStructure.getProductIncomeTh(), incomeStructure.getProductIncomeEn(), locale);
            } else {
                question = addDefaultThousandBaht(locale.equals(Locale.forLanguageTag(Constants.TH)) ? incomeStructure.getProductIncomeTh() : incomeStructure.getProductIncomeEn(), locale);
            }
        } else {
            question = getQuestionByInfoTypeConfig1_5(isRatio, incomeStructure, locale);
        }


        String questionIsChange = utilService.checkChanged(question, incomeStructure.isChanged(), changed, locale);
        exportIncomeStructure.setQuestion(questionIsChange);
        if (isRatio) {
            exportIncomeStructure.setAsOfYearBeforeYesteryear(addPercent(incomeStructure.getAsOfYearBeforeYesteryearPercent()));
            exportIncomeStructure.setAsOfYesteryear(addPercent(incomeStructure.getAsOfYesteryearPercent()));
            exportIncomeStructure.setAsOfYear(addPercent(incomeStructure.getAsOfYearPercent()));
        } else {
            exportIncomeStructure.setAsOfYearBeforeYesteryear(checkNullSetDefault(incomeStructure.getAsOfYearBeforeYesteryear(), df_2));
            exportIncomeStructure.setAsOfYesteryear(checkNullSetDefault(incomeStructure.getAsOfYesteryear(), df_2));
            exportIncomeStructure.setAsOfYear(checkNullSetDefault(incomeStructure.getAsOfYear(), df_2));
        }

        exportIncomeStructure.setBold(incomeStructure.getInfoTypeConfig() != null && Y.equals(incomeStructure.getInfoTypeConfig().getInitial()) && !"defaultExportIncome".equals(incomeStructure.getInfoTypeConfig().getName()));
        exportIncomeStructure.setBold(incomeStructure.getInfoTypeConfig() != null
                && (incomeStructure.getInfoTypeConfig().getName().contains("total")
//                || incomeStructure.getInfoTypeConfig().getName().contains("other")
                || incomeStructure.getInfoTypeConfig().getName().contains("domestic")
                || incomeStructure.getInfoTypeConfig().getName().contains("international")
                || InfoType.DEFAULT == incomeStructure.getInfoTypeConfig().getInfoType()));

        return exportIncomeStructure;
    }

    private String addPercent(BigDecimal value) {
        if (value != null) {
            return value + "%";
        }
        return N_A;
    }

    public List<ExportYearStructureUnit> prepareExportProductInfo(ProductInfo productInfo, Locale locale,
                                                                  Boolean changed, double phase, boolean isExcel, boolean isWord) {
        List<ExportYearStructureUnit> exportProductInfoStructureList = new ArrayList<>();
        if (Stream.of(productInfo.getResearchCostAsOfYear(),
                productInfo.getResearchCostAsOfYesteryear(),
                productInfo.getResearchCostAsOfYearBeforeYesteryear()).allMatch(Objects::isNull)
        ) {
            return new ArrayList<>();
        }
        ExportYearStructureUnit exportProductInfoStructure = new ExportYearStructureUnit();
        String question = utilService.checkChanged(messageSource.getMessage("main_node1.node1.section2.topic2.question5.sub_topic1", null, locale)
                , (productInfo.isResearchCostAsOfYearIsChanged()
                        || productInfo.isResearchCostAsOfYesteryearIsChanged()
                        || productInfo.isResearchCostAsOfYearBeforeYesteryearIsChanged())
                , changed
                , locale);

        exportProductInfoStructure.setQuestion(question);
        exportProductInfoStructure.setBold(true);
        exportProductInfoStructure.setAsOfYear(phase == 1 ? checkNullSetDefault(productInfo.getResearchCostAsOfYear(), df_2) : checkNullSetDefault(productInfo.getResearchCostAsOfYear(), df_2, N_A));
        exportProductInfoStructure.setAsOfYesteryear(phase == 1 ? checkNullSetDefault(productInfo.getResearchCostAsOfYesteryear(), df_2) : checkNullSetDefault(productInfo.getResearchCostAsOfYesteryear(), df_2, N_A));
        exportProductInfoStructure.setAsOfYearBeforeYesteryear(phase == 1 ? checkNullSetDefault(productInfo.getResearchCostAsOfYearBeforeYesteryear(), df_2) : checkNullSetDefault(productInfo.getResearchCostAsOfYearBeforeYesteryear(), df_2, N_A));
        exportProductInfoStructureList.add(exportProductInfoStructure);
        return exportProductInfoStructureList;
    }

    private static String checkNullSetDefault(String input) {
        return input != null && !input.isEmpty() ? input : DASH;
    }

    private String checkNullSetDefault(Boolean input, Locale locale) {
        return input != null ?
                (input ? messageSource.getMessage("answer_have", null, locale) : messageSource.getMessage("answer_not_have", null, locale))
                : DASH;
    }

    private static String checkNullSetDefault(BigDecimal input, DecimalFormat df) {
        return input != null ? df.format(input) : N_A;
    }

    private static String checkNullSetDefaultDash(BigDecimal input, DecimalFormat df) {
        return input != null ? df.format(input) : DASH;
    }

    private static String checkNullSetDefault(String input, DecimalFormat df) {
        return input != null && !input.isEmpty() ?
                input.equals(DASH) ? DASH : df.format(new BigDecimal(input)) : DASH;
    }

    private static String checkNullSetDefault(String input, DecimalFormat df, String defaultValue) {
        return input != null && !input.isEmpty() ?
                input.equals(DASH) ? defaultValue : df.format(new BigDecimal(input)) : defaultValue;
    }

    private List<ExportYearStructureUnit> prepareShareProfitList(ShareProfitResponse shareProfitResponse,
                                                                 Locale locale, Boolean changed, double phase, boolean isExcel, boolean isWord) {
        if (phase < 3 ||
                Stream.of(shareProfitResponse.getAsOfYear(),
                        shareProfitResponse.getAsOfYesteryear(),
                        shareProfitResponse.getAsOfYearBeforeYesteryear()).allMatch(Objects::isNull)
        ) {
            return new ArrayList<>();
        }
        List<ExportYearStructureUnit> incomeStructures = new ArrayList<>();
        ExportYearStructureUnit exportIncomeStructure = new ExportYearStructureUnit();

        String question = locale.equals(Locale.forLanguageTag(Constants.TH)) ? shareProfitResponse.getInfoTypeConfig().getNameTh() : shareProfitResponse.getInfoTypeConfig().getNameEn();
        question = utilService.checkChanged(question
                , (shareProfitResponse.isAsOfYearIsChanged()
                        || shareProfitResponse.isAsOfYesteryearIsChanged()
                        || shareProfitResponse.isAsOfYearBeforeYesteryearIsChanged())
                , changed
                , locale);

        exportIncomeStructure.setBold(true);
        exportIncomeStructure.setQuestion(addDefaultThousandBaht(question, locale));
        exportIncomeStructure.setAsOfYear(checkNullSetDefault(shareProfitResponse.getAsOfYear(), df_2));
        exportIncomeStructure.setAsOfYesteryear(checkNullSetDefault(shareProfitResponse.getAsOfYesteryear(), df_2));
        exportIncomeStructure.setAsOfYearBeforeYesteryear(checkNullSetDefault(shareProfitResponse.getAsOfYearBeforeYesteryear(), df_2));
        incomeStructures.add(exportIncomeStructure);

        return incomeStructures;
    }

    private List<ExportMarketPolicyInfo> prepareProductServiceInfoBusinessInnoDevList(List<ProductServiceCharacteristics> productServiceCharacteristicsList,
                                                                                      Locale locale,
                                                                                      List<ImageInfoResponse> productOrServiceImgInfos,
                                                                                      ReportDetail reportDetail,
                                                                                      boolean isExcel, boolean isWord) {
        return productServiceCharacteristicsList.stream()
                .map(x -> {
                    ExportMarketPolicyInfo exportMarketPolicyInfo = new ExportMarketPolicyInfo();
                    exportMarketPolicyInfo.setTitle1(getNameByLanguage(x.getNameTh(), x.getNameEn(), locale));
                    exportMarketPolicyInfo.setDescription1(getHtmlByLanguage(x.getDescriptionTh(), x.getDescriptionEn(), locale, isWord));
                    exportMarketPolicyInfo.setTitle4(messageSource.getMessage(
                            "main_node1.node1.section2.topic2.question5.sub_topic2", null, locale) + exportMarketPolicyInfo.getTitle1());
                    if (productOrServiceImgInfos != null) {
                        List<ImageInfoResponse> imageInfoResponses = productOrServiceImgInfos.stream().filter(img -> img.getParentId() != null && img.getParentId().longValue() == x.getId())
                                .collect(Collectors.toList());
                        exportMarketPolicyInfo.setImages(getImgInfos(imageInfoResponses, locale, reportDetail));
                    }
                    return exportMarketPolicyInfo;
                })
                .collect(Collectors.toList());
    }

    private List<ExportMarketPolicyInfo> prepareMarketPolicyInfoList(List<MarketingPolicyInfoResponse> marketingPolicyInfoList,
                                                                     MarketingPolicy marketingPolicy, Locale locale,
                                                                     List<ImageInfoResponse> marketingPolicyImgInfos,
                                                                     ReportDetail reportDetail,
                                                                     boolean isExcel, boolean isWord, RemarkSequence remarkSequence){
        return marketingPolicyInfoList.stream()
                .map(x -> {
                    return getExportMarketPolicyInfo(x, marketingPolicy, locale,
                            marketingPolicyImgInfos.stream().filter(img -> img.getParentId() != null && img.getParentId().longValue() == x.getId()).collect(Collectors.toList()),
                            reportDetail, isExcel, isWord, remarkSequence);
                })
                .collect(Collectors.toList());
    }

    private ExportMarketPolicyInfo getExportMarketPolicyInfo(MarketingPolicyInfoResponse marketingPolicyInfo,
                                                             MarketingPolicy marketingPolicy, Locale locale,
                                                             List<ImageInfoResponse> imageInfoResponses,
                                                             ReportDetail reportDetail,
                                                             boolean isExcel, boolean isWord, RemarkSequence remarkSequence) {
        ExportMarketPolicyInfo exportMarketPolicyInfo = new ExportMarketPolicyInfo();
        if (N.equals(marketingPolicy.getMarketingPolicyClarifyType())) {
            exportMarketPolicyInfo.setTitle1(getNameByLanguage(marketingPolicyInfo.getNameTh(), marketingPolicyInfo.getNameEn(), locale));
        }

        String title2 = messageSource.getMessage("main_node1.node1.section2.topic2.question7", null, locale);
        if (marketingPolicyInfo.getDomesticExportSalesRatioRemarkId() != null) {
            RemarkData remarkData = remarkService.getRemarkDataById(marketingPolicyInfo.getDomesticExportSalesRatioRemarkId());
            title2 = xlsUtilService.addRemarkSeq(title2,
                    remarkSequence, remarkData, locale, false);
            RemarkTable remarkTable = xlsUtilService.getRemarkTable(locale, false, remarkData, remarkSequence.getCurrent() - 1);
            remarkTable.setTitle(xlsUtilService.getRemarkLabel(locale));
            exportMarketPolicyInfo.setRemark(new JRBeanCollectionDataSource(List.of(remarkTable)));
        }
        exportMarketPolicyInfo.setTitle2(title2);
        exportMarketPolicyInfo.setTitle3(messageSource.getMessage("main_node1.node1.section2.topic2.question8", null, locale));
        exportMarketPolicyInfo.setTitle4(messageSource.getMessage("main_node1.node1.section2.topic2.question8.sub_topic1", null, locale));
        exportMarketPolicyInfo.setDescription1(getHtmlByLanguage(marketingPolicyInfo.getDescriptionTh(),
                marketingPolicyInfo.getDescriptionEn(), locale, isWord));
        exportMarketPolicyInfo.setDescription3(getHtmlByLanguage(marketingPolicyInfo.getCompetitionWithinIndustryPastYear().getDescriptionTh(), marketingPolicyInfo.getCompetitionWithinIndustryPastYear().getDescriptionEn(), locale, isWord));
        exportMarketPolicyInfo.setDescription2(new JRBeanCollectionDataSource(marketingPolicyInfo.getDomesticExportSalesRatioValueResponseList()
                .stream().map(x -> getDomesticExportSalesRatio(x, locale)).collect(Collectors.toList())));

        if (marketingPolicyInfo.getDomesticExportSalesRatioIntroId() != null) {
            IntroductionData introductionData = introductionService.getIntroductionDataById(marketingPolicyInfo.getDomesticExportSalesRatioIntroId());
            IntroductionTable introductionTable = xlsUtilService.getIntroductionTable(locale, false, introductionData);
            exportMarketPolicyInfo.setIntroduction(new JRBeanCollectionDataSource(List.of(introductionTable)));
        }
        if (imageInfoResponses != null) {
            exportMarketPolicyInfo.setImages(getImgInfos(imageInfoResponses, locale, reportDetail));
        }
        return exportMarketPolicyInfo;
    }

    private ExportDescriptive getDomesticExportSalesRatio(DomesticExportSalesRatioValueResponse domesticExportSalesRatioValueResponse, Locale locale) {
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                new ExportDescriptive(domesticExportSalesRatioValueResponse.getInfoTypeConfig().getNameTh(), checkNullSetDefault(domesticExportSalesRatioValueResponse.getPercent(), df_2)) :
                new ExportDescriptive(domesticExportSalesRatioValueResponse.getInfoTypeConfig().getNameEn(), checkNullSetDefault(domesticExportSalesRatioValueResponse.getPercent(), df_2));
    }

    private List<ExportYearStructureUnit> prepareOtherIncomeStructureList(List<OtherIncomeStructure> otherIncomeStructureList, Locale locale, Boolean changed, double phase, boolean isExcel, boolean isWord) {
        if (phase < 3 || otherIncomeStructureList.stream().allMatch(x -> Stream.of(x.getAsOfYear(),
                x.getAsOfYesteryear(),
                x.getAsOfYearBeforeYesteryear()).allMatch(Objects::isNull))) {
            return new ArrayList<>();
        }
        List<ExportYearStructureUnit> exportIncomeStructures = new ArrayList<>();
        for (OtherIncomeStructure e : otherIncomeStructureList) {
            ExportYearStructureUnit exportIncomeStructure = new ExportYearStructureUnit();
            String question;
            if (e.getInfoTypeConfig() != null) {
                question = locale.equals(Locale.forLanguageTag(Constants.TH)) ? e.getInfoTypeConfig().getNameTh() : e.getInfoTypeConfig().getNameEn();
            } else {
                question = locale.equals(Locale.forLanguageTag(Constants.TH)) ? e.getNameTh() : e.getNameEn();
            }
            question = utilService.checkChanged(question, e.isChanged(), changed, locale);

            exportIncomeStructure.setBold(e.getInfoTypeConfig() != null &&
                    (e.getInfoTypeConfig().getName().contains("total")
                            || e.getInfoTypeConfig().getName().contains("operOtherIncome")
                            || e.getInfoTypeConfig().getName().contains("notOperOtherIncome")
                    )
            );
            exportIncomeStructure.setQuestion(addDefaultThousandBaht(question, locale));
            exportIncomeStructure.setAsOfYear(checkNullSetDefault(e.getAsOfYear(), df_2));
            exportIncomeStructure.setAsOfYesteryear(checkNullSetDefault(e.getAsOfYesteryear(), df_2));
            exportIncomeStructure.setAsOfYearBeforeYesteryear(checkNullSetDefault(e.getAsOfYearBeforeYesteryear(), df_2));

            // optional item
            if (e.getInfoTypeConfig() == null && Stream.of(e.getAsOfYear(),
                    e.getAsOfYesteryear(),
                    e.getAsOfYearBeforeYesteryear()).anyMatch(Objects::nonNull)) {
                exportIncomeStructures.add(exportIncomeStructure);
            }
            // require item
            if (e.getInfoTypeConfig() != null) {
                exportIncomeStructures.add(exportIncomeStructure);
            }
        }

        return exportIncomeStructures;
    }

    private List<ExportProcurementInfo> prepareProcurementInfoList(List<ProcurementInfoResponse> procurementInfoList,
                                                                   Procurement procurement,
                                                                   BusinessCharacteristics businessCharacteristics,
                                                                   Locale locale, boolean isExcel, boolean isWord) {
        return procurementInfoList.stream()
                .map(x -> getExportProcurementInfo(x, procurement, businessCharacteristics, locale, isExcel, isWord))
                .collect(Collectors.toList());
    }

    private ExportProcurementInfo getExportProcurementInfo(ProcurementInfoResponse procurementInfo,
                                                           Procurement procurement,
                                                           BusinessCharacteristics businessCharacteristics,
                                                           Locale locale, boolean isExcel, boolean isWord){
        ExportProcurementInfo exportProcurementInfo = new ExportProcurementInfo();
        if (N.equals(procurement.getProcurementClarifyType())) {
            exportProcurementInfo.setTitle1(getNameByLanguage(procurementInfo.getNameTh(), procurementInfo.getNameEn(), locale));
            exportProcurementInfo.setAnyValue(exportProcurementInfo.getTitle1() != null);
        }
        exportProcurementInfo.setDescription1(getHtmlByLanguage(procurementInfo.getDescriptionTh(), procurementInfo.getDescriptionEn(), locale, isWord));
        if (Y.equals(businessCharacteristics.getBusinessType())) {
            exportProcurementInfo.setTitle2(messageSource.getMessage("main_node1.node1.section2.topic2.question10", null, locale));
            exportProcurementInfo.setTitle4(messageSource.getMessage("main_node1.node1.section2.topic2.question11", null, locale));
            exportProcurementInfo.setTitle5(messageSource.getMessage("main_node1.node1.section2.topic2.question12", null, locale));
            exportProcurementInfo.setTitle6(messageSource.getMessage("main_node1.node1.section2.topic2.question13", null, locale));
            exportProcurementInfo.setDescription3(getHtmlByLanguage(procurementInfo.getProductCapacityPolicy().getDescriptionTh(), procurementInfo.getProductCapacityPolicy().getDescriptionEn(), locale, isWord));
            exportProcurementInfo.setDescription4(getHtmlByLanguage(procurementInfo.getProcurementRawMaterial().getDescriptionTh(), procurementInfo.getProcurementRawMaterial().getDescriptionEn(), locale, isWord));
            exportProcurementInfo.setDescription6(getHtmlByLanguage(procurementInfo.getRawMaterialDistributor().getDescriptionTh(), procurementInfo.getRawMaterialDistributor().getDescriptionEn(), locale, isWord));
            exportProcurementInfo.setNumberOfRawMaterialDistributorsTitle(messageSource.getMessage("main_node1.node1.section2.topic2.question13.sub_topic1", null, locale));
            exportProcurementInfo.setNumberOfRawMaterialDistributorsDescription(checkNullSetDefaultNull(procurementInfo.getRawMaterialDistributor().getNumberOfRawMaterialDistributors(), df));
            exportProcurementInfo.setDescription2(new JRBeanCollectionDataSource(procurementInfo.getProductCapacityValueResponseList().stream().map(x -> getProductCapacity(x, locale)).collect(Collectors.toList())));
            exportProcurementInfo.setDescription5(new JRBeanCollectionDataSource(procurementInfo.getProportionPurchasingRawMaterialList().stream().map(x -> getProportionPurchasingRawMaterial(x, locale)).collect(Collectors.toList())));

            exportProcurementInfo.setAnyValue(exportProcurementInfo.getDescription3() != null
                    || exportProcurementInfo.getDescription4() != null
                    || exportProcurementInfo.getDescription6() != null
                    || !procurementInfo.getProductCapacityValueResponseList().isEmpty()
                    || !procurementInfo.getProportionPurchasingRawMaterialList().isEmpty()
            );
        }

        return exportProcurementInfo;
    }

    private Export3Column getProductCapacity(ProductCapacityValueResponse productCapacityValueResponse, Locale locale) {
        return new Export3Column(getProductCapacityName(productCapacityValueResponse, locale), checkNullSetDefault(productCapacityValueResponse.getTotal(), df_2), checkNullSetDefault(productCapacityValueResponse.getSpent(), df_2));
    }

    private Export3Column getProportionPurchasingRawMaterial(ProportionPurchasingRawMaterialValueResponse proportionPurchasingRawMaterial, Locale locale) {
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                new Export3Column(proportionPurchasingRawMaterial.getInfoTypeConfig().getNameTh(), checkNullSetDefault(proportionPurchasingRawMaterial.getRawMaterialNameTh()), checkNullSetDefault(proportionPurchasingRawMaterial.getValue(), df_2)) :
                new Export3Column(proportionPurchasingRawMaterial.getInfoTypeConfig().getNameEn(), checkNullSetDefault(proportionPurchasingRawMaterial.getRawMaterialNameEn()), checkNullSetDefault(proportionPurchasingRawMaterial.getValue(), df_2));
    }

    private String getProductCapacityName(ProductCapacityValueResponse productCapacityValueResponse, Locale locale) {
        String unit;

        if (productCapacityValueResponse.getProductUnitType() == null) {
            unit = "-";
        } else {
            if (locale.equals(Locale.forLanguageTag(Constants.TH))) {
                unit = "other".equals(productCapacityValueResponse.getProductUnitType().getName()) ? checkNullSetDefault(productCapacityValueResponse.getOtherNameTh()) : productCapacityValueResponse.getProductUnitType().getNameTh();
                return checkNullSetDefault(productCapacityValueResponse.getNameTh()).concat("\n").concat("("+unit+")");
            } else {
                unit = "other".equals(productCapacityValueResponse.getProductUnitType().getName()) ? checkNullSetDefault(productCapacityValueResponse.getOtherNameEn()) : productCapacityValueResponse.getProductUnitType().getNameEn();
                return checkNullSetDefault(productCapacityValueResponse.getNameEn()).concat("\n").concat("("+unit+")");
            }
        }
        return checkNullSetDefault(locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                checkNullSetDefault(productCapacityValueResponse.getNameTh()) :
                checkNullSetDefault(productCapacityValueResponse.getNameEn()))
                .concat("\n").concat(unit);
    }

    private List<Export6Column> preparePermanentAppraisalPriceList(List<PermanentAppraisalPrice> permanentAppraisalPriceList, Locale locale, boolean isExcel, boolean isWord) {
        List<Export6Column> export6Columns = permanentAppraisalPriceList.stream()
                .map(x -> getExportPermanentAppraisalPrice(x, locale, isExcel, isWord))
                .collect(Collectors.toList());
        if (!export6Columns.isEmpty()) {
            export6Columns.get(0).setFirst(true);
            export6Columns.get(export6Columns.size() - 1).setLast(true);
        }
        return export6Columns;
    }

    private Export6Column getExportPermanentAppraisalPrice(PermanentAppraisalPrice permanentAppraisalPrice,
                                                           Locale locale, boolean isExcel, boolean isWord) {
        Export6Column exportPermanentAppraisalPrice = new Export6Column();
        exportPermanentAppraisalPrice.setColumn1(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(permanentAppraisalPrice.getNameTh(), permanentAppraisalPrice.getNameEn(), locale), 27, isWord)));
        exportPermanentAppraisalPrice.setColumn2(checkNullSetDefault(permanentAppraisalPrice.getAppraisalPrice(), df_2));
        exportPermanentAppraisalPrice.setColumn3(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(permanentAppraisalPrice.getOwnershipTypeTh(), permanentAppraisalPrice.getOwnershipTypeEn(), locale),14, isWord)));
        exportPermanentAppraisalPrice.setColumn4(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(permanentAppraisalPrice.getObligationTh(), permanentAppraisalPrice.getObligationEn(), locale),15, isWord)));
        exportPermanentAppraisalPrice.setColumn5(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(permanentAppraisalPrice.getDescriptionTh(), permanentAppraisalPrice.getDescriptionEn(), locale),15, isWord)));
        return exportPermanentAppraisalPrice;
    }

    private List<Export6Column> prepareIntangibleAppraisalPriceList(List<IntangibleAppraisalPriceResponse> intangibleAppraisalPriceList, Locale locale, boolean isExcel, boolean isWord) {
        List<Export6Column> export6Columns = intangibleAppraisalPriceList.stream()
                .map(x -> getExportIntangibleAppraisalPrice(x, locale, isExcel, isWord))
                .collect(Collectors.toList());
        if (!export6Columns.isEmpty()) {
            export6Columns.get(0).setFirst(true);
            export6Columns.get(export6Columns.size() - 1).setLast(true);
        }
        return export6Columns;
    }

    private Export6Column getExportIntangibleAppraisalPrice(IntangibleAppraisalPriceResponse intangibleAppraisalPrice
            , Locale locale, boolean isExcel, boolean isWord) {
        Export6Column exportPermanentAppraisalPrice = new Export6Column();
        exportPermanentAppraisalPrice.setColumn1(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(intangibleAppraisalPrice.getNameTh(), intangibleAppraisalPrice.getNameEn(), locale), 27, isWord)));
        exportPermanentAppraisalPrice.setColumn2(getLabelWithOtherType(intangibleAppraisalPrice.getIntangibleType(), intangibleAppraisalPrice.getOtherNameTh(), intangibleAppraisalPrice.getOtherNameEn(), locale));
        exportPermanentAppraisalPrice.setColumn3(checkNullSetDefault(intangibleAppraisalPrice.getAppraisalPrice(), df_2));
        exportPermanentAppraisalPrice.setColumn4(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(intangibleAppraisalPrice.getDescriptionTh(), intangibleAppraisalPrice.getDescriptionEn(), locale), 20, isWord)));
        return exportPermanentAppraisalPrice;
    }

    private String getLabelWithOtherType(InfoTypeConfig infoTypeConfig, String otherNameTh, String otherNameEn, Locale locale) {
        if (infoTypeConfig == null) {
            return "-";
        }
        if ("other".equals(infoTypeConfig.getName())) {
            return locale.equals(Locale.forLanguageTag(Constants.TH)) ? checkNullSetDefault(otherNameTh) : checkNullSetDefault(otherNameEn);
        }
        return locale.equals(Locale.forLanguageTag(Constants.TH)) ? infoTypeConfig.getNameTh() : infoTypeConfig.getNameEn();
    }

    private List<Export6Column> prepareUnderConstructionProjectInfoList(List<UnderConstructionProjectInfo> underConstructionProjectInfoList, Locale locale, boolean isExcel, boolean isWord) {
        List<Export6Column> export6Columns = underConstructionProjectInfoList.stream()
                .map(x -> getUnderConstructionProjectInfo(x, locale, isExcel, isWord))
                .collect(Collectors.toList());
        if (!export6Columns.isEmpty()) {
            export6Columns.get(0).setFirst(true);
            export6Columns.get(export6Columns.size() - 1).setLast(true);
        }
        return export6Columns;
    }

    private Export6Column getUnderConstructionProjectInfo(UnderConstructionProjectInfo underConstructionProjectInfo,
                                                          Locale locale, boolean isExcel, boolean isWord) {
        Export6Column exportUnderConstructionProjectInfo = new Export6Column();
        exportUnderConstructionProjectInfo.setColumn1(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(underConstructionProjectInfo.getNameTh(), underConstructionProjectInfo.getNameEn(), locale), 20, isWord)));
        exportUnderConstructionProjectInfo.setColumn2(checkNullSetDefault(underConstructionProjectInfo.getIncomePercent(), df_2));
        exportUnderConstructionProjectInfo.setColumn3(getEstimateTime(underConstructionProjectInfo.getMonthEstimateTime(), underConstructionProjectInfo.getYearEstimateTime(), locale));
        exportUnderConstructionProjectInfo.setColumn4(UtilService.getMonthYear(underConstructionProjectInfo.getEstimateCompletionTime(), locale));
        exportUnderConstructionProjectInfo.setColumn5(checkNullSetDefault(underConstructionProjectInfo.getValue(), df_2));
        exportUnderConstructionProjectInfo.setColumn6(checkNullSetDefaultBlank(Thaicut.wordwrap(getNameByLanguage(underConstructionProjectInfo.getDescriptionTh(), underConstructionProjectInfo.getDescriptionEn(), locale), 17, isWord)));
        return exportUnderConstructionProjectInfo;
    }

    public String getEstimateTime(BigDecimal monthEstimateTime, BigDecimal yearEstimateTime, Locale locale) {
        return String.format("%s %s %s %s", checkNullSetDefaultDash(yearEstimateTime, df_2),
                messageSource.getMessage("year", null, locale),
                checkNullSetDefaultDash(monthEstimateTime, df_2),
                messageSource.getMessage("month", null, locale));
    }

    private List<Export3Column> prepareUnderConstructionProjectDetail(UnderConstructionProject underConstructionProject, Locale locale, boolean isExcel, boolean isWord) {
        List<Export3Column> exportUnderConstructionProjectDetail = new ArrayList<>();
        exportUnderConstructionProjectDetail.add(new Export3Column(messageSource.getMessage("main_node1.node1.section2.topic2.question23.header1", null, locale),
                checkNullSetDefaultNull(underConstructionProject.getNumberOfProject(), df)));
        exportUnderConstructionProjectDetail.add(new Export3Column(messageSource.getMessage("main_node1.node1.section2.topic2.question23.header2", null, locale),
                checkNullSetDefaultNull(underConstructionProject.getProjectValue(), df_2)));
        exportUnderConstructionProjectDetail.add(new Export3Column(messageSource.getMessage("main_node1.node1.section2.topic2.question23.header3", null, locale),
                checkNullSetDefaultNull(underConstructionProject.getAcknowledgedValue(), df_2)));
        exportUnderConstructionProjectDetail.add(new Export3Column(messageSource.getMessage("main_node1.node1.section2.topic2.question23.header4", null, locale),
                checkNullSetDefaultNull(underConstructionProject.getRemainingUnknownValue(), df_2)));
        exportUnderConstructionProjectDetail.add(new Export3Column(messageSource.getMessage("main_node1.node1.section2.topic2.question23.header5", null, locale),
                getNameByLanguage(underConstructionProject.getMoreDetailTh(), underConstructionProject.getMoreDetailEn(), locale)));
        return exportUnderConstructionProjectDetail.stream().filter(x -> !checkFieldIsNull(x.getDescription1())).collect(Collectors.toList());
    }

    private void prepareBusinessImgInfos(List<ImageInfoResponse> imgList, Map<String, Object> parameters, Locale locale,
                                         ReportDetail reportDetail, String param) {
        if (imgList != null && !imgList.isEmpty()) {
            String reportPath = getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());
            List<ExportImage> exportImages = imgList.stream()
                    .filter(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)
                    .map(img -> getExportImage(locale, img, reportPath)).collect(Collectors.toList());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportImages);
            parameters.put(param, dataSource);
        }
    }

    private void prepareImgInfos(List<ImageInfoResponse> additionalImgInfos, Map<String, Object> parameters,
                                 Locale locale, ReportDetail reportDetail, String param) {
        parameters.put(param, getImgInfos(additionalImgInfos, locale, reportDetail));
    }


    private JRBeanCollectionDataSource getImgInfos(List<ImageInfoResponse> additionalImgInfos,
                                                   Locale locale, ReportDetail reportDetail) {
        if (additionalImgInfos != null && !additionalImgInfos.isEmpty() && additionalImgInfos.stream()
                .anyMatch(img -> XlsUtilService.getNameByLanguage(img.getGenerateFileNameTh(), img.getGenerateFileNameEn(), locale) != null)) {
            String reportPath = getReportPath(reportDetail, reportDetail.getReport().getAsOfYear().toString());

            List<ExportImage> exportImages = additionalImgInfos.stream()
                    .filter(img -> TH.equals(locale.getLanguage()) ? img.getGenerateFileNameTh() != null : img.getGenerateFileNameEn() != null)
                    .map(img -> getExportImage(locale, img, reportPath)).collect(Collectors.toList());
            return new JRBeanCollectionDataSource(exportImages);
        }
        return null;
    }

}

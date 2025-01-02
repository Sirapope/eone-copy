package th.or.set.setportal.eonereport.config;

import lombok.Getter;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import th.or.set.setportal.eonereport.bean.InfoTypeConfigKey;
import th.or.set.setportal.eonereport.constants.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@PropertySource(value = "classpath:env.properties", encoding = "UTF-8")
@Configuration
public class Constants {

    @Value("${constants.jwt_public_key:/opt/eonereport/key/jwt_public_key}")
    public String jwtPublicKeyPath;

    @Value("${sso.auth.url:https://sso.scptest.set/authen}")
    public String ssoAuthUrl;

    @Value("${constants.sso_refresh_url:https://sso.scpdev.set/authen/refreshToken}")
    public String ssoRefreshUrl;

    @Value("${constants.sso_profile_url:https://sso.scpdev.set/profile/profile}")
    public String ssoProfileUrl;

    @Value("${ignore.expired.jwt.path:/clear-current-user}")
    public String ignoreExpiredJWTPath;

    @Value("${download.document.path:/opt/setlink/eone/back/dat/document/}")
    public String downloadDocumentPath;

    @Value("${smd.url.service.default:https://apidev.simsdw.set/api}")
    public String smdUrlServiceDefault;

    @Value("${smd.secret.string.default:ONEREPORT:7SJYQXDFJJE5A2YVGJIHKQ6CRQAF2I7N}")
    public String smdSecretStringDefault;

    @Value("${smd.path.company_profile:/companyProfile}")
    public String smdPathCompanyProfile;

    @Value("${smd.path.current_share:/currentShare}")
    public String smdPathCurrentShare;

    @Value("${smd.path.board.all:/board/all}")
    public String smdPathBoardAll;

    @Value("${smd.path.shareholder:/shareholder}")
    public String smdPathShareholder;

    @Value("${set_link.url.service.default:https://api.setlinktest.set.or.th/api}")
    public String setLinkUrlServiceDefault;

    @Value("${set_link.secret.string.default:LDP:YNJP83DIFZZVYMZJJ410VKDS0NOV05ZK}")
    public String setLinkSecretStringDefault;

    @Value("${set_link.path.noti_send:/noti/send}")
    public String setLinkPathNotiSend;

    @Value("${set_link.path.one_report_info.mapping:/oneReportInfo/mapping}")
    public String setLinkPathOneReportInfoMapping;

    @Value("${set_link.path.one_report_info.agm:/oneReportInfo/redirectLink}")
    public String setLinkPathOneReportInfoAgm;

    @Value("${set_link.path.noti.acknowledge:/noti/acknowledge}")
    public String setLinkPathNotiAcknowledge;

    @Value("${set_link.path.one_report_info.publish:/oneReportInfo/publish}")
    public String setLinkPathPublish;

    @Value("${ldp.url.service.auth:https://one.setlinkdev.set/auth?url=}")
    public String ldpUrlServiceDefault;

    @Value("${ldp.send_to_approve.path:/report/%s/review-and-approve-%s}")
    public String ldpSendToApprovePath;

    @Value("${ldp.publish_report.path:/report/%s/publish-report-%s}")
    public String ldpPublishReportPath;

    @Value("${cpm.url.service.default:https://cpmapitest.set.or.th/crm/rm/api}")
    public String cpmUrlServiceDefault;

    @Value("${cpm.secret.string.default:ONEREPORT:HFDDB2FTXYVS3CAI298TNPP3LXX0FRFH}")
    public String cpmSecretStringDefault;

    @Value("${cpm.path.listed_list:/crm/listedCaseOwnerList}")
    public String cpmPathListedList;

    @Value("${fsc.url.service.default:https://fsc.scpdev.set/taxonomy/one-report}")
    public String fscUrlServiceDefault;

    @Value("${fsc.path.financial_value:/financial-value}")
    public String fscFinancialValuePath;

    @Value("${fsc.path.taxonomy:/taxonomy}")
    public String fscTaxonomyPath;

    @Value("${scp.url.service.default:https://api.scpdev.set/api}")
    public String scpUrlServiceDefault;

    @Value("${scp.path.check_send_structured_data:/document/sent/check-send-structured-data}")
    public String scpCheckSendStructuredDatPath;

    @Value("${scp.path.lasted_send_structured_data:/document/sent/latest-send-structured-data}")
    public String scpLastedSendStructuredDatPath;

    @Value("${scp.path.list_active_company:/company/list-active-company}")
    public String scpActiveCompanyResponsePath;

    @Value("${scp.path.listed_company_users_by_company_and_roles:/user/getListUserByRolesAndCompanySCPId}")
    public String scpListedCompanyUsersByCompanyAndRolesPath;

    @Value("${scp.batch_key:djhfrdghrtyv}")
    public String scpBatchKey;

    @Value("${esg.url.service.default:https://esg.setlinkdev.set/api}")
    public String esgUrlServiceDefault;

    @Value("${esg.secret.string.default:ONEREPORT:LD4D625165OFMG876D5A7134743AI298}")
    public String esgSecretStringDefault;

    @Value("${esg.url.path.sustainable_development:/ldp/sustainableDevelopment/{companySmdId}/{asOfYear}}")
    public String esgUrlPathSustainableDevelopment;

    @Value("${esg.url.path.download_file:/ldp/download/{companySmdId}/{asOfYear}?name={name}}")
    public String esgUrlPathDownloadFile;

    @Value("${esg.url.path.corporate_governance_policy:/ldp/corporateGovernancePolicy/{companySmdId}/{asOfYear}}")
    public String esgUrlPathCorporateGovernancePolicy;

    @Value("${esg.url.path.corporate_governance_structure:/ldp/corporateGovernanceStructure/{companySmdId}/{asOfYear}}")
    public String esgUrlPathCorporateGovernanceStructure;

    @Value("${esg.url.path.report_status:/ldp/reportStatus/{companySmdId}/{asOfYear}}")
    public String esgUrlPathReportStatus;

    @Value("${esg.url.path.other_energy_mgmt:/ldp/sustainableDevelopment/{companySmdId}/otherEnergyMgmt}")
    public String esgUrlPathOtherEnergyMgmt;

    @Value("${esg.url.path.other_energy_mgmt_list:/ldp/sustainableDevelopment/{companySmdId}/otherEnergyMgmtList}")
    public String esgUrlPathOtherEnergyMgmtList;

    @Value("#{'${ldp.secret.string.default}'.split(',')}")
    public List<String> ldpSecretStringDefaultList;

    @Value("${task.secret.string:djkadlkjadljalkjaljsljsdlkjflalsdk}")
    public String taskSecretString;

    @Value("${setdd.url.service.default:http://www.scpdev.set/setdd}")
    public String setddUrlServiceDefault;
    @Value("${setdd.path.postUploadFile:/genReport.do?method=postUploadFile}")
    public String setddPathPostUploadFile;
    @Value("${setdd.path.apiGenReport:/genReport.do?method=apiGenReport}")
    public String setddPathApiGenReport;
    @Value("${setdd.path.apiSentToApprover:/draft.do?method=apiSentToApprover}")
    public String setddPathApiSentToApprover;
    @Value("${setdd.path.apiPublic:/sent.do?method=apiPublic}")
    public String setddPathApiPublic;
    @Value("${setdd.path.apiRequestDelete:/sent.do?method=apiRequestDelete}")
    public String setddPathApiRequestDelete;

    @Value("${sec.client.id.default:SETOneReport}")
    public String secClientIdDefault;
    @Value("${sec.client.secret.default:2068fb382b28410cb241a62a45c918af}")
    public String secClientSecretDefault;
    @Value("${sec.url.service.default:https://web-bos-api-iwt.sec.or.th/api/Bos}")
    public String secUrlServiceDefault;
    @Value("${sec.path.apiAuthToken:/Auth/Token}")
    public String secAuthToken;
    @Value("${sec.path.apiCompApproval:/GetCompApprovalByCompLicExp/audit}")
    public String secCompanyApprovalPath;
    @Value("${sec.path.apiControllerAndAuditorDetail:/GetControllerAndAuditorDetail/%s/audit}")
    public String secControllerAndAuditorDetailPath;

    @Value("${set_carbon.url.service.default:https://setcarbon.setstage.or.th/api}")
    public String setCarbonUrlServiceDefault;
    @Value("${set_carbon.data.path:/public/total_ghg/overview}")
    public String setCarbonDataPath;
    @Value("${set_carbon.login.path:/public/auth/login}")
    public String setCarbonLoginPath;
    @Value("${set_carbon.username:tsd_dev}")
    public String setCarbonUsername;
    @Value("${set_carbon.password:SETDevOB~nm1ocnObq}")
    public String setCarbonPassword;

    public static final String BUSINESS_AND_OPERATING_RESULTS = "businessAndOperatingResults";
    public static final String STRUCTURE_AND_OPERATION = "structureAndOperation";
    public static final String BAC_02 = "bac02";
    public static final String BAC_03 = "bac03";
    public static final String BAC_04 = "bac04";
    public static final String BAC_05 = "bac05";
    public static final String BAC_01 = "bac01";

    public static final String ISSUANCE_OF_OTHER_SECURITIES = "issuanceOfOtherSecurities";
    public static final String IOS_01 = "ios01";
    public static final String IOS_02 = "ios02";

    public static final String BC_01 = "bc01"; // 1.2.1
    public static final String BC_02 = "bc02"; // 1.2.2
    public static final String BC_02_01 = "bc0201"; //1.2.2.1
    public static final String BC_02_02 = "bc0202"; // 1.2.2.2
    public static final String BC_02_03 = "bc0203"; // 1.2.2.3
    public static final String BC_02_04 = "bc0204"; // 1.2.2.4
    public static final String BC_02_05 = "bc0205"; //1.2.2.5
    public static final String SHAREHOLDING_STRUCTURE = "shareholdingStructure"; // 1.3
    public static final String SS_01 = "ss01"; // 1.3.1
    public static final String SS_02 = "ss02"; // 1.3.2
    public static final String SS_03 = "ss03"; // 1.3.3
    public static final String SS_04 = "ss04"; // 1.3.4
    public static final String MAJOR_SHAREHOLDERS = "majorShareholders";

    public static final String REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL = "registeredCapitalAndPaidUpCapital";
    public static final String RCAPC_01 = "rcapc01";
    public static final String RCAPC_02 = "rcapc02";
    public static final String RCAPC_03 = "rcapc03";

    public static final String RISK_MGMT_POLICY_AND_PLAN = "riskMgmtPolicyAndPlan";
    public static final String RMPAP_01 = "rmpap01";
    public static final String RISK_MANAGEMENT = "riskManagement";
    public static final String ESG = "esg";
    public static final String BUSINESS_AND_CORPORATE_POLICY = "businessAndCorporatePolicy";
    public static final String BUSINESS_CHARACTERISTICS = "businessCharacteristics";
    public static final String DIVIDEND_POLICY = "dividendPolicy"; // 1.6
    public static final String DP_01 = "dp01";
    public static final String RISK_FACTORS = "riskFactors";
    public static final String RF_O1 = "rf01";
    public static final String RF_O2 = "rf02";
    public static final String RF_O3 = "rf03";

    public static final String ATTACHMENT = "attachment";
    public static final String ATTACHMENT_DETAILS = "attachmentDetails";
    public static final String ATTACHMENT_TOPIC = "attachmentTopic";
    public static final String AD_01 = "ad01";

    public static final String PROCEDURE_FOR_SUBMITTING_TH = "procedureForSubmittingTh";
    public static final String REVIEW_AND_UPLOAD_TH = "reviewAndUploadTh";
    public static final String REVIEW_AND_APPROVE_TH = "reviewAndApproveTh";
    public static final String PUBLISH_REPORT_TH = "publishReportTh";

    public static final String PROCEDURE_FOR_SUBMITTING_EN = "procedureForSubmittingEn";
    public static final String REVIEW_AND_UPLOAD_EN = "reviewAndUploadEn";
    public static final String REVIEW_AND_APPROVE_EN = "reviewAndApproveEn";
    public static final String PUBLISH_REPORT_EN = "publishReportEn";

    public static final String CORPORATE_GOVERNANCE = "corporateGovernance";
    public static final String CORPORATE_GOVERNANCE_POLICY = "corporateGovernancePolicy";
    public static final String OVERVIEW_POLICIES = "overviewPolicies";
    public static final String OP_01 = "op01";
    public static final String OP_02 = "op02";
    public static final String CODE_OF_CONDUCT = "codeOfConduct";
    public static final String COC_01 = "coc01";
    public static final String CHANGES_AND_DEV_POLICY = "changesAndDevPolicy";
    public static final String CADP_01 = "cadp01";
    public static final String CADP_02 = "cadp02";
    public static final String CADP_03 = "cadp03";

    public static final String CORPORATE_GOVERNANCE_STRUCTURE = "corporateGovernanceStructure";
    public static final String CGS_01 = "cgs01";

    public static final String BOARD_OF_DIRECTORS = "boardOfDirectors";//7.2
    public static final String SUB_COMMITTEES = "subCommittees";//7.3
    public static final String EXECUTIVES = "executives";//7.4
    public static final String EMPLOYEES = "employees";//7.5
    public static final String OTHER_IMPORTANT_INFORMATION = "otherImportantInformation";//7.6
    public static final String OTHER_SHAREHOLDER_ISSUES = "otherShareholderIssues";
    public static final String SHAREHOLDER = "shareholder";
    public static final String EMPLOYEE = "employee";
    public static final String CUSTOMER = "customer";
    public static final String BUSINESS_COMPETITOR = "businessCompetitor";
    public static final String CREDITOR = "creditor";
    public static final String GOVERNMENT_AGENCY = "governmentAgency";
    public static final String COMMUNITY_AND_SOCIETY = "communityAndSociety";
    public static final String BUSINESS_PARTNER = "businessPartner";


    public static final String BOARD_OF_DIRECTORS_INFORMATION = "boardOfDirectorsInformation";//7.2
    public static final String BODI_01 = "bodi01";
    public static final String BODI_02 = "bodi02";
    public static final String BODI_03 = "bodi03";

    public static final String SUBCOMMITTEES_INFORMATION = "subcommitteesInformation";//7.3
    public static final String SI_01 = "si01";
    public static final String SI_02 = "si02";

    public static final String EXECUTIVES_INFORMATION = "executivesInformation";//7.4
    public static final String EXI_01 = "exi01";
    public static final String EXI_02 = "exi02";
    public static final String EXI_03 = "exi03";

    public static final String EMPLOYEES_INFORMATION = "employeesInformation";//7.5
    public static final String EMI_01 = "emi01";
    public static final String EMI_02 = "emi02";

    public static final String OTHER_SIGNIFICANT_INFORMATION = "otherSignificantInformation";//7.6
    public static final String OSI_01 = "osi01";
    public static final String OSI_02 = "osi02";
    public static final String OSI_03 = "osi03";
    public static final String OSI_04 = "osi04";

    public static final String RESULT_OF_CORPORATE_GOVERNANCE = "resultOfCorporateGovernance";//8
    public static final String BOARD_OF_DIRECTORS_PERF_SUMMARY = "boardOfDirectorsPerfSummary";//8.1
    public static final String BODPS_01 = "bodps01";//8.1.1
    public static final String BODPS_0101 = "bodps0101";//8.1.1(1)ข้อมูลเกี่ยวกับการสรรหาคณะกรรมการ
    public static final String BODPS_0102 = "bodps0102";//8.1.1(2)ข้อมูลเกี่ยวกับการพัฒนากรรมการ
    public static final String BODPS_0103 = "bodps0103";//8.1.1(3)ข้อมูลเกี่ยวกับการประเมินผลการปฎิบัติหน้าที่ของกรรมการ

    public static final String BODPS_02 = "bodps02";//8.1.2
    public static final String BODPS_0201 = "bodps0201";//8.1.2(1)การเข้าร่วมประชุมของคณะกรรมการบริษัท
    public static final String BODPS_0202 = "bodps0202";//8.1.2(2)ค่าตอบแทนของคณะกรรมการ

    public static final String BODPS_03 = "bodps03";//8.1.3
    public static final String BODPS_04 = "bodps04";//8.1.4
    public static final String BODPS_04_01 = "bodps0401";
    public static final String BODPS_04_02 = "bodps0402";
    public static final String BODPS_04_03 = "bodps0403";
    public static final String BODPS_04_04 = "bodps0404";

    public static final String AUDIT_COMMITTEES_PERF_SUMMARY = "auditCommitteesPerfSummary";//8.2
    public static final String APS_01 = "aps01";//8.2.1
    public static final String APS_02 = "aps02";//8.2.2

    public static final String SUBCOMMITTEES_PERF_SUMMARY = "subcommitteesPerfSummary";//8.3
    public static final String SPS_01 = "sps01";

    public static final String SUSTAINABILITY_POLICY_AND_GOAL = "sustainabilityPolicyAndGoal";//3.1
    public static final String SPAG_01 = "spag01";
    public static final String SPAG_02 = "spag02";
    public static final String STAKEHOLDERS_IMPACT_MGMT = "stakeholdersImpactMgmt";//3.2
    public static final String SIM_01 = "sim01";//3.2.1
    public static final String SIM_02 = "sim02";//3.2.2
    public static final String ENVIRONMENTAL_SUSTAINABILITY_MGMT = "environmentalSustainabilityMgmt";//3.3

    public static final String ESM_01 = "esm01";
    public static final String ESM_02 = "esm02";
    public static final String ESM_03 = "esm03";
    public static final String ESM_04 = "esm04";
    public static final String ESM_05 = "esm05";
    public static final String ESM_06 = "esm06";
    public static final String ESM_07 = "esm07";
    public static final String ESM_08 = "esm08";

    //4.1
    public static final String MD_A = "mdAndA";
    public static final String DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION = "discussionOperationAndFinancialCondition";
    public static final String DOAFC_01 = "doafc01";
    public static final String POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION = "potentialFactorAffectFinancialCondition";
    public static final String PFAFC_01 = "pfafc01";
    public static final String FINANCIAL_STATEMENTS_AND_FINANCIAL_RATIOS = "financialStatementsAndFinancialRatios";
    public static final String FSFR_01 = "fsfr01";
    public static final String FSFR_01_01 = "fsfr0101";
    public static final String FSFR_01_02 = "fsfr0102";
    public static final String FSFR_01_03 = "fsfr0103";
    public static final String FSFR_02 = "fsfr02";

    public static final String SOCIAL_SUSTAINABILITY_MGMT = "socialSustainabilityMgmt";//3.4
    public static final String SSM_01 = "ssm01";
    public static final String SSM_02 = "ssm02";
    public static final String SSM_03 = "ssm03";
    public static final String SSM_04 = "ssm04";
    public static final String SSM_05 = "ssm05";
    public static final String SSM_06 = "ssm06";
    public static final String SSM_07 = "ssm07";
    public static final String OTHER_SUSTAINABILITY_INFO = "otherSustainabilityInfo";

    public static final String INTERNAL_CONTROL = "internalControl";//9.1
    public static final String IC_01 = "ic01";//9.1.1
    public static final String IC_02 = "ic02";//9.1.2
    public static final String IC_03 = "ic03";//9.1.3
    public static final String IC_04 = "ic04";//9.1.4
    public static final String IC_05 = "ic05";//9.1.5

    public static final String RELATED_PARTY_TRANSACTIONS = "relatedPartyTransactions";//9.2
    public static final String RPT_01 = "rpt01";
    public static final String RPT_02 = "rpt02";
    public static final String RPT_03 = "rpt03";
    public static final String RPT_04 = "rpt04";

    public static final String FINANCIAL_STATEMENT = "financialStatement";
    public static final String BALANCE_SHEET = "balanceSheet";
    public static final String INCOME_STATEMENT = "incomeStatement";
    public static final String CASH_FLOW = "cashFlow";
    public static final String FINANCIAL_RATIO = "financialRatio";

    public static final String ASSETS = "assets";
    public static final String LIABILITIES = "liabilities";
    public static final String EQUITIES = "equities";

    public static final String STATEMENT_OF_COMPREHENSIVE_INCOME = "statementOfComprehensiveIncome";

    public static final String CASH_FLOW_STATEMENT = "cashFlowStatement";

    public static final String LIQUIDITY = "liquidity";
    public static final String PROFITABILITY = "profitability";
    public static final String FINANCIAL_POLICY = "financialPolicy";
    public static final String ASSET_QUALITY = "assetQuality";
    public static final String EFFICIENCY = "efficiency";
    public static final String OTHER_RATIO = "otherRatio";

    public static final String OTHER_MATERIAL_FACTS = "otherMaterialFacts";
    public static final String OMF_01 = "omf01";
    public static final String OMF_02 = "omf02";

    public static final String GENERAL_INFORMATION = "generalInformation";
    public static final String GI_01 = "gi01";

    public static final String LEGAL_DISPUTES = "legalDisputes";
    public static final String LD_01 = "ld01";

    public static final String SECONDARY_MARKET = "secondaryMarket";
    public static final String SM_01 = "sm01";

    public static final String FINANCIAL_INSTITUTION_WITH_REGULAR_CONTACT = "financialInstitutionWithRegularContact";
    public static final String FIWRC_01 = "fiwrc01";

    public static final String FINANCIAL_REPORT_TOPIC = "financialReportTopic";
    public static final String FINANCIAL_REPORT = "financialReport";
    public static final String FR_01 = "fr01";

    public static final String PRODUCT_INCOME_TITLE = "สัดส่วนรายได้แบ่งตามสายผลิตภัณฑ์หรือกลุ่มธุรกิจ";
    public static final String EXPORT_INCOME_TITLE = "สัดส่วนรายได้ในประเทศต่อการส่งออกไปจำหน่ายต่างประเทศ";
    public static final String PDF_EXTENSION = ".pdf";
    public static final String JSON_EXTENSION = ".json";
    public static final String DOCX_EXTENSION = ".docx";
    public static final String XLSX_EXTENSION = ".xlsx";
    public static final String CSV_EXTENSION = ".csv";
    public static final String ZIP_EXTENSION = ".zip";
    public static final String PREVIEW_FILE_NAME = "preview_";
    public static final String BASE_PATH = "opt/setlink/eone/back/dat/report/";
    public static final String LDP_BASE_PATH = "opt/setlink/ldp/dat/report/";
    public static final String INITIAL_JSON_PATH = BASE_PATH + "initial/";
    public static final String GENERATE_BASE_PATH = "opt/setlink/eone/back/dat/report/generate/";
    public static final String MAXIMUM_REPORT_FILE_SIZE_KEY = "reportMaxFileSize";
    public static final String MAXIMUM_ESG_FILE_SIZE_KEY = "esgMaxFileSize";
    public static final String SUFFIX_MAXIMUM_FILE_SIZE_KEY = "MaxFileSize";
    public static final String MAXIMUM_ATTACH_FILE_SIZE_KEY = "attachMaxFileSize";
    public static final String CUSTOM_FISCAL_YEAR_COMPANY_KEY = "customFiscalYearCompany";
    public static final String REMARK_MAX_LENGTH_KEY = "remarkMaxLength";
    public static final String INTRODUCTION_MAX_LENGTH_KEY = "introductionMaxLength";
    public static final String IMAGE_MAX_FILE_SIZE = "imageMaxFileSize";
    public static final String FINANCIAL_REPORT_MAX_FILE_SIZE = "financialReportMaxFileSize";
    public static final String UPLOAD_FILE_NAME_TH = "uploadFileNameTh";
    public static final String UPLOAD_FILE_NAME_EN = "uploadFileNameEn";
    public static final String SKIP_TH = "skipTh";
    public static final String SKIP_EN = "skipEn";
    public static final String PDF = "pdf";
    public static final String WORD = "word";
    public static final String EXCEL = "excel";
    public static final String JPEG = "jpeg";
    public static final String JPG = "jpg";
    public static final String PNG = "png";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String TH = "th";
    public static final String EN = "en";
    public static final String BOTH = "both";
    public static final String _CHANGED = "_changed";
    public static final String REVIEW_TH = "reviewTh";
    public static final String REVIEW_EN = "reviewEn";
    public static final String APPROVE_TH = "approveTh";
    public static final String APPROVE_EN = "approveEn";
    public static final String PUBLISH_TH = "publishTh";
    public static final String PUBLISH_EN = "publishEn";
    public static final String VIRUS_SERVER_NAME_KEY = "virusServerAddress";
    public static final String VIRUS_SERVER_PORT_KEY = "virusServerPort";
    public static final String BY_PASS_SCAN_VIRUS_KEY = "fsvBypassScanVirus";

    public static final String Y = "Y";
    public static final String N = "N";

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    //    info type config name
    public static final String TITLE_MR = "titleMr";
    public static final String TITLE_MS = "titleMs";
    public static final String TITLE_MRS = "titleMrs";
    public static final String TITLE_OTHER = "titleOther";

    public static final String BOARD_POSITION_CHAIRMAN = "chairman";
    public static final String BOARD_POSITION_VICE_CHAIRMAN = "viceChairman";
    public static final String BOARD_POSITION_DIRECTOR = "director";

    public static final String BOARD_STATUS_EXECUTIVE = "executive";
    public static final String BOARD_STATUS_NON_EXECUTIVE = "nonExecutive";

    public static final String COMMITTEE_POSITION_CHAIRMAN = "chairmanAuditCommittee";
    public static final String COMMITTEE_POSITION_AUDIT_COMMITTEE = "auditCommittee";

    public static final int YEAR_QUARTER = 9;
    public static final int THOUSAND = 1000;
    public static final int MILLION = 1000000;


    public static final String ATTACH_FILE_PATTERN_056 = "056";
    public static final String ATTACH_FILE_PATTERN_WEBSITE = "Website";

    public static final Long ONE = 1L;
    public static final Long TWO = 2L;
    public static final Long THREE = 3L;
    public static final Long FOUR = 4L;

    public static final DecimalFormat df_2 = new DecimalFormat("#,##0.00");
    public static final DecimalFormat df = new DecimalFormat("#,##0");

    public static final String REPORT = "REPORT";
    public static final String STRUCTURE = "STRUCTURE";

    public static final String FSC_SPECIAL_SET_CODE = "fscSpecialSetCode";

    public static final String NOMINATION = "nomination";
    public static final String COMPENSATION = "compensation";
    public static final String INDEPENDENT = "independent";
    public static final String DEVELOPMENT = "development";
    public static final String PERFORMANCE = "performance";
    public static final String SUBSIDIARIES_CG = "subsidiariesCg";
    public static final String OTHER_GUIDELINES_REGARDING = "otherGuidelinesRegarding";

    public static final String CONFLICTS_OF_INTEREST = "conflictsOfInterest";
    public static final String ANTI_CORRUPTION = "antiCorruption";
    public static final String INSIDE_INFO = "insideInfo";
    public static final String WHISTLE_BLOWING = "whistleBlowing";
    public static final String COC_FOR_PARTNERS = "cocForPartners";
    public static final String MONEY_LAUNDERING_PREVENTION = "moneyLaunderingPrevention";
    public static final String GIVING_RECEIVING_GIFTS = "givingReceivingGifts";
    public static final String COMPLIANCE_LAW = "complianceLaw";
    public static final String DATA_ASSETS_MAINTENANCE = "dataAssetsMaintenance";
    public static final String ANTI_COMPETITIVE = "antiCompetitive";
    public static final String CYBER_SECURITY = "cyberSecurity";
    public static final String ENVIRONMENT_MGMT = "environmentalMgmt";
    public static final String HUMAN_RIGHTS = "humanRights";
    public static final String HEALTH_AND_SAFETY = "healthAndSafety";
    public static final String OTHER_COC_ISSUE = "otherCocIssue";

    public static final String THAI_CAC = "thaiCac";
    public static final String UNGC = "ungc";
    public static final String OTHER_AGAINST_CORRUPTION_PROJECT = "otherAgainstCorruptionProject";

    public static final String INCREASE = "INCREASE";
    public static final String DECREASE = "DECREASE";

    public static final int MAX_LENGTH_INTRODUCTION = 111;
    public static final int MAX_LENGTH_REMARK = 150;
    public static final int MAX_LENGTH_URL_QUESTION = 65;


    public static final BigDecimal PHASE_4 = BigDecimal.valueOf(4);

    public static final String SUCCESS = "success";

    public static final String[] THAI_MONTHS = {
            "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.",
            "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.",
            "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
    public static final String[] ENG_MONTHS = {
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"};

    public static final String[] FULL_THAI_MONTHS = {
            "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
            "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
    public static final String[] FULL_ENG_MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};

    public static final String OTHER_MAX_EDUCATE_DEPT = "otherMaxEducateDept";

    public static final String SLASH = "/";
    public static final String DASH = "-";
    public static final String N_A = "N/A";

    public static final String DIRECTOR_INFO = "directorInfo";
    public static final String AUDIT_COMMITTEE_INFO = "auditCommitteeInfo";
    public static final String EXECUTIVE_INFO = "executiveInfo";

    public static final String BEARER = "Bearer ";

    public static final String BASIC = "Basic ";

    public static final String INITIAL = "initial";

    public static final int MAX_OTHER_ATTACH_TYPE = 10;

    public static final String IMAGE_URL = "/image/{reportDetailId}/download?name={generateName}";

    public static final Map<InfoTypeConfigKey, Integer> FUEL_PHASE_1 = Map.of(
            new InfoTypeConfigKey("fuelOil", 1), 1,
            new InfoTypeConfigKey("naturalGas", 1), 2,
            new InfoTypeConfigKey("crudeOil", 1), 3,
            new InfoTypeConfigKey("coal", 1), 4
    );

    public static final Map<InfoTypeConfigKey, Integer> FUEL_PHASE_1_5 = Map.of(
            new InfoTypeConfigKey("jetFuel", 1.5), 1,
            new InfoTypeConfigKey("diesel", 1.5), 2,
            new InfoTypeConfigKey("gasoline", 1.5), 3,
            new InfoTypeConfigKey("fuelOil", 1.5), 4,
            new InfoTypeConfigKey("crudeOil", 1), 5,
            new InfoTypeConfigKey("naturalGas", 1.5), 6,
            new InfoTypeConfigKey("liquidPropaneGas", 1.5), 7,
            new InfoTypeConfigKey("steam", 1.5), 8,
            new InfoTypeConfigKey("coal", 1), 9
    );

    public static final Map<InfoTypeConfigKey, Integer> FUEL_PHASE_3 = Map.of(
            new InfoTypeConfigKey("jetFuel", 3), 1,
            new InfoTypeConfigKey("diesel", 3), 2,
            new InfoTypeConfigKey("gasoline", 3), 3,
            new InfoTypeConfigKey("fuelOil", 3), 4,
            new InfoTypeConfigKey("crudeOil", 3), 5,
            new InfoTypeConfigKey("naturalGas", 3), 6,
            new InfoTypeConfigKey("liquidPropaneGas", 3), 7,
            new InfoTypeConfigKey("steam", 3), 8,
            new InfoTypeConfigKey("coal", 3), 9
    );

    public static final Map<InfoTypeConfigKey, Integer> FUEL_PHASE_4 = Map.of(
            new InfoTypeConfigKey("jetFuel", 4), 1,
            new InfoTypeConfigKey("diesel", 4), 2,
            new InfoTypeConfigKey("gasoline", 4), 3,
            new InfoTypeConfigKey("fuelOil", 4), 4,
            new InfoTypeConfigKey("crudeOil", 4), 5,
            new InfoTypeConfigKey("naturalGas", 4), 6,
            new InfoTypeConfigKey("liquidPropaneGas", 4), 7,
            new InfoTypeConfigKey("steam", 4), 8,
            new InfoTypeConfigKey("coal", 4), 9
    );

    public static final Map<Double, Map<InfoTypeConfigKey, Integer>> FUEL_PHASE = Map.of(
            1.0, FUEL_PHASE_1,
            1.5, FUEL_PHASE_1_5,
            3.0, FUEL_PHASE_3,
            4.0, FUEL_PHASE_4
    );

    public static final Map<InfoTypeConfigKey, Integer> GREEN_HOUSE_GAS_PHASE_1 = Map.of(
            new InfoTypeConfigKey("ghGasScope1", 1), 1,
            new InfoTypeConfigKey("ghGasScope2", 1), 2,
            new InfoTypeConfigKey("ghGasScope3", 1), 3,
            new InfoTypeConfigKey("total", 1), 4
    );

    public static final Map<InfoTypeConfigKey, Integer> GREEN_HOUSE_GAS_PHASE_1_5 = Map.of(
            new InfoTypeConfigKey("ghGasGoal", 1.5), 1,
            new InfoTypeConfigKey("ghGasScope1", 1), 2,
            new InfoTypeConfigKey("ghGasScope2", 1), 3,
            new InfoTypeConfigKey("ghGasScope3", 1), 4,
            new InfoTypeConfigKey("total", 1), 5
    );

    public static final Map<InfoTypeConfigKey, Integer> GREEN_HOUSE_GAS_PHASE_3 = Map.of(
            new InfoTypeConfigKey("ghGasGoal", 3), 1,
            new InfoTypeConfigKey("ghGasScope1", 3), 2,
            new InfoTypeConfigKey("ghGasScope2", 3), 3,
            new InfoTypeConfigKey("ghGasScope3", 3), 4,
            new InfoTypeConfigKey("total", 3), 5
    );

    public static final Map<InfoTypeConfigKey, Integer> GREEN_HOUSE_GAS_PHASE_4 = Map.of(
            new InfoTypeConfigKey("total", 4), 1,
            new InfoTypeConfigKey("ghGasScope1", 4), 2,
            new InfoTypeConfigKey("ghGasScope2", 4), 3,
            new InfoTypeConfigKey("ghGasScope3", 4), 4
    );

    public static final Map<Double, Map<InfoTypeConfigKey, Integer>> GREEN_HOUSE_GAS_PHASE = Map.of(
            1.0, GREEN_HOUSE_GAS_PHASE_1,
            1.5, GREEN_HOUSE_GAS_PHASE_1_5,
            3.0, GREEN_HOUSE_GAS_PHASE_3,
            4.0, GREEN_HOUSE_GAS_PHASE_4
    );

    public static List<String> PHASE_1_5_FISCAL_END = Arrays.asList("31/12", "31/01", "28/02");
    public static final String URL_REGEX = "^(?:(?:https?:\\/\\/)|(?:www\\.)|(?=.*\\..{2,}))(?=[^\\/]*\\..{2,})[^\\s\\.]+\\.[^\\s\\/]{2,}";
    public static final String EMAIL_REGEX = "^((([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-zA-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$";
    public static final String REMARK_TH_REGEX = "^[\\u0E00-\\u0E7F\\u00A0\\w\\s!@#$&()\\\\-`×.+,/\"/'/%/^/*/=/+/:/;/?/</>/’/‘/–/—/~\\[\\]{}”“!-#%-\\\\*,-\\\\/:;\\\\?@\\\\[-\\\\]_\\\\{\\\\}\\xA1\\xA7\\xAB\\xB6\\xB7\\xBB\\xBF\\u037E\\u0387\\u055A-\\u055F\\u0589\\u058A\\u05BE\\u05C0\\u05C3\\u05C6\\u05F3\\u05F4\\u0609\\u060A\\u060C\\u060D\\u061B\\u061E\\u061F\\u066A-\\u066D\\u06D4\\u0700-\\u070D\\u07F7-\\u07F9\\u0830-\\u083E\\u085E\\u0964\\u0965\\u0970\\u09FD\\u0A76\\u0AF0\\u0C77\\u0C84\\u0DF4\\u0E4F\\u0E5A\\u0E5B\\u0F04-\\u0F12\\u0F14\\u0F3A-\\u0F3D\\u0F85\\u0FD0-\\u0FD4\\u0FD9\\u0FDA\\u104A-\\u104F\\u10FB\\u1360-\\u1368\\u1400\\u166E\\u169B\\u169C\\u16EB-\\u16ED\\u1735\\u1736\\u17D4-\\u17D6\\u17D8-\\u17DA\\u1800-\\u180A\\u1944\\u1945\\u1A1E\\u1A1F\\u1AA0-\\u1AA6\\u1AA8-\\u1AAD\\u1B5A-\\u1B60\\u1BFC-\\u1BFF\\u1C3B-\\u1C3F\\u1C7E\\u1C7F\\u1CC0-\\u1CC7\\u1CD3\\u2010-\\u2027\\u2030-\\u2043\\u2045-\\u2051\\u2053-\\u205E\\u207D\\u207E\\u208D\\u208E\\u2308-\\u230B\\u2329\\u232A\\u2768-\\u2775\\u27C5\\u27C6\\u27E6-\\u27EF\\u2983-\\u2998\\u29D8-\\u29DB\\u29FC\\u29FD\\u2CF9-\\u2CFC\\u2CFE\\u2CFF\\u2D70\\u2E00-\\u2E2E\\u2E30-\\u2E4F\\u2E52\\u3001-\\u3003\\u3008-\\u3011\\u3014-\\u301F\\u3030\\u303D\\u30A0\\u30FB\\uA4FE\\uA4FF\\uA60D-\\uA60F\\uA673\\uA67E\\uA6F2-\\uA6F7\\uA874-\\uA877\\uA8CE\\uA8CF\\uA8F8-\\uA8FA\\uA8FC\\uA92E\\uA92F\\uA95F\\uA9C1-\\uA9CD\\uA9DE\\uA9DF\\uAA5C-\\uAA5F\\uAADE\\uAADF\\uAAF0\\uAAF1\\uABEB\\uFD3E\\uFD3F\\uFE10-\\uFE19\\uFE30-\\uFE52\\uFE54-\\uFE61\\uFE63\\uFE68\\uFE6A\\uFE6B\\uFF01-\\uFF03\\uFF05-\\uFF0A\\uFF0C-\\uFF0F\\uFF1A\\uFF1B\\uFF1F\\uFF20\\uFF3B-\\uFF3D\\uFF3F\\uFF5B\\uFF5D\\uFF5F-\\uFF65\\u200B\\|\\uFEFF|\\u2028| \\p{Sc}]+$";
    public static final String REMARK_EN_REGEX = "^[\\u00A0\\w\\s!@#$&()\\\\-`×.+,/\"/'/%/^/*/=/+/:/;/?/</>/’/‘/–/—/~/฿\\[\\]{}”“!-#%-\\\\*,-\\\\/:;\\\\?@\\\\[-\\\\]_\\\\{\\\\}\\xA1\\xA7\\xAB\\xB6\\xB7\\xBB\\xBF\\u037E\\u0387\\u055A-\\u055F\\u0589\\u058A\\u05BE\\u05C0\\u05C3\\u05C6\\u05F3\\u05F4\\u0609\\u060A\\u060C\\u060D\\u061B\\u061E\\u061F\\u066A-\\u066D\\u06D4\\u0700-\\u070D\\u07F7-\\u07F9\\u0830-\\u083E\\u085E\\u0964\\u0965\\u0970\\u09FD\\u0A76\\u0AF0\\u0C77\\u0C84\\u0DF4\\u0E4F\\u0E5A\\u0E5B\\u0F04-\\u0F12\\u0F14\\u0F3A-\\u0F3D\\u0F85\\u0FD0-\\u0FD4\\u0FD9\\u0FDA\\u104A-\\u104F\\u10FB\\u1360-\\u1368\\u1400\\u166E\\u169B\\u169C\\u16EB-\\u16ED\\u1735\\u1736\\u17D4-\\u17D6\\u17D8-\\u17DA\\u1800-\\u180A\\u1944\\u1945\\u1A1E\\u1A1F\\u1AA0-\\u1AA6\\u1AA8-\\u1AAD\\u1B5A-\\u1B60\\u1BFC-\\u1BFF\\u1C3B-\\u1C3F\\u1C7E\\u1C7F\\u1CC0-\\u1CC7\\u1CD3\\u2010-\\u2027\\u2030-\\u2043\\u2045-\\u2051\\u2053-\\u205E\\u207D\\u207E\\u208D\\u208E\\u2308-\\u230B\\u2329\\u232A\\u2768-\\u2775\\u27C5\\u27C6\\u27E6-\\u27EF\\u2983-\\u2998\\u29D8-\\u29DB\\u29FC\\u29FD\\u2CF9-\\u2CFC\\u2CFE\\u2CFF\\u2D70\\u2E00-\\u2E2E\\u2E30-\\u2E4F\\u2E52\\u3001-\\u3003\\u3008-\\u3011\\u3014-\\u301F\\u3030\\u303D\\u30A0\\u30FB\\uA4FE\\uA4FF\\uA60D-\\uA60F\\uA673\\uA67E\\uA6F2-\\uA6F7\\uA874-\\uA877\\uA8CE\\uA8CF\\uA8F8-\\uA8FA\\uA8FC\\uA92E\\uA92F\\uA95F\\uA9C1-\\uA9CD\\uA9DE\\uA9DF\\uAA5C-\\uAA5F\\uAADE\\uAADF\\uAAF0\\uAAF1\\uABEB\\uFD3E\\uFD3F\\uFE10-\\uFE19\\uFE30-\\uFE52\\uFE54-\\uFE61\\uFE63\\uFE68\\uFE6A\\uFE6B\\uFF01-\\uFF03\\uFF05-\\uFF0A\\uFF0C-\\uFF0F\\uFF1A\\uFF1B\\uFF1F\\uFF20\\uFF3B-\\uFF3D\\uFF3F\\uFF5B\\uFF5D\\uFF5F-\\uFF65\\u200B\\|\\uFEFF|\\u2028| \\p{Sc}]+$";
    public static final String EMOJI_REGEX = "\\uD83D[\\uDC00-\\uDCFF]|[\\uDC00-\\uDFFF]|[\\uDE00-\\uDE4F]|[\\uDE00-\\uDE4F]|[\\uDF80-\\uDFFF][\\uDC00-\\uDDFF]|[\\uDE50-\\uDE7F]|[\\uDE80-\\uDEFF]|[\\uDED8-\\uDEDF\\uDEED-\\uDEEF\\uDEFD-\\uDEFF\\uDF74-\\uDF7F\\uDFD9-\\uDFDF\\uDFEC-\\uDFFF]|[\\uDC00-\\uDED7\\uDEE0-\\uDEEC\\uDEF0-\\uDEFC\\uDF00-\\uDF73\\uDF80-\\uDFD8\\uDFE0-\\uDFEB]|\\uD83C[\\uDF00-\\uDFFF]|[\\u2700-\\u27BF]|[\\u2600-\\u26FF]";
    public static final String UNICODE_SPACE_REGEX = "[\\u2028]";
    public static final String COMBINED_EMOJIS_AND_REMARK_TH_REGEX = "(?:" + EMOJI_REGEX + ")|(?:" + REMARK_TH_REGEX + ")|(?:" + UNICODE_SPACE_REGEX + ")";
    public static final String COMBINED_EMOJIS_AND_REMARK_EN_REGEX = "(?:" + EMOJI_REGEX + ")|(?:" + REMARK_EN_REGEX + ")|(?:" + UNICODE_SPACE_REGEX + ")";
    public static final String ELECTRIC = "electric";
    public static final String FUEL = "fuel";
    public static final String RENEWABLE = "renewable";
    public static final String WATER = "water";
    public static final String WASTE = "waste";
    public static final String BIODIVERSITY = "biodiversity";
    public static final String GH_GAS = "ghGas";
    public static final String AIR_QUALITY = "airQuality";
    public static final String NOISE_POLLUTION = "noisePollution";
    public static final String OTHER = "other";

    public static final String FOREIGN_WORKER = "foreignWorker";
    public static final String CHILD_LABOR = "childLabor";
    public static final String CONSUMER_RIGHTS = "consumerRights";
    public static final String COMMUNITY_AND_ENVIRONMENT_RIGHTE = "communityAndEnvironmentRights";
    public static final String OCCUPATIONAL_SAFETY = "occupationalSafety";
    public static final String EMPLOYEE_RIGHTS = "employeeRights";
    public static final String NON_DISCRIMINATION = "nonDiscrimination";

    public static final String TOTAL_MALE = "totalMale";
    public static final String TOTAL_FEMALE = "totalFemale";
    public static final String TOTAL_EMPLOYEES = "totalEmployees";
    public static final String TRAINING_HOURS = "trainingHours";
    public static final String TRAINING_EXPENSES = "trainingExpenses";
    public static final String EMPLOYEE_REMUNERATION = "employeeRemuneration";
    public static final String BOD_PROFILE = "bodProfile";
    public static final String AUDIT_COMMITTEE_PROFILE = "auditCommitteeProfile";
    public static final String JASPERREPORT_FONT_NAME = "TH Sarabun New";
    public static final int JASPERREPORT_FONT_STYLE_PLAIN = java.awt.Font.PLAIN;
    public static final int JASPERREPORT_FONT_STYLE_BOLD = java.awt.Font.BOLD;
    public static final int JASPERREPORT_FONT_STYLE_ITALIC = java.awt.Font.ITALIC;

    public static final List<String> RESULT_OF_CORPORATE_PROFILE_FIELDS = Arrays.asList("titleIdEn", "otherTitleEn", "firstNameEn", "lastNameEn", "titleIdTh", "otherTitleTh", "firstNameTh", "lastNameTh", "position", "endDate");
    public static final List<String> RESULT_OF_CORPORATE_BOD_FIELDS = Arrays.asList("training", "totalBoardMeetingAttendance", "totalBoardMeeting", "agmMeeting", "egmMeeting", "meetingAllowance", "otherRemuneration", "nonMonetaryRemuneration");
    public static final List<String> RESULT_OF_CORPORATE_AUDIT_FIELDS = Arrays.asList("totalMeetingAttendance", "totalMeeting");

    public static final Map<String, Integer> RELATE_FIELD_CORPORATE = Stream.of(new Object[][]{
            {TOTAL_MALE, 1},
            {TOTAL_FEMALE, 2},
            {TOTAL_EMPLOYEES, 3},
            {EMPLOYEE_REMUNERATION, 4},
            {BOD_PROFILE, 5},
            {"training", 6},
            {"directorMeeting", 7},
            {"directorRemuneration", 8},
            {AUDIT_COMMITTEE_PROFILE, 9},
            {"auditCommitteeMeeting", 10},
            {"employeeInfoRemark", 11},
            {"employeeRemunerationRemark", 12},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public static final String BOARD_OF_DIRECTOR = "boardOfDirector";
    public static final String AUDIT_COMMITTEE = "auditCommittee";
    public static final String EXECUTIVE_COMMITTEE = "executiveCommittee";
    public static final String EXECUTIVE = "executive";

    public static final List<String> NEW_ELEMENT = Arrays.asList("newTh", "newEn");

    public static final Map<String, Integer> EMPLOYEE_INFO_ORDER = Map.of(
            TOTAL_MALE, 1,
            TOTAL_FEMALE, 2,
            TOTAL_EMPLOYEES, 3,
            EMPLOYEE_REMUNERATION, 4,
            TRAINING_HOURS, 5,
            TRAINING_EXPENSES, 6,
            "occupationalAccidents", 7,
            "employeeRelationship", 8,
            "laborDispute", 9
    );

    public static final Map<String, Integer> EMPLOYEE_ORDER = Map.of(
            TOTAL_MALE, 1,
            TOTAL_FEMALE, 2,
            "total", 3,
            "totalRemuneration", 4,
            "numberOfPvd", 5,
            "percentPvd", 6
    );

    public static final Map<String, Integer> EXECUTIVE_ORDER = Map.of(
            "remunerationPolicy", 1,
            "asOfYear", 2,
            "asOfYesteryear", 3,
            "asOfYearBeforeYesteryear", 4,
            "esop", 5,
            "ejip", 6
    );

    public static final Map<String, String> RM_DEPARTMENT_MAPPING = Map.of(
            "Issuer Department 1", "IS1",
            "Issuer Department 2", "IS2",
            "Issuer Department 3", "IS3",
            "Issuer Department 4", "IS4",
            "Issuer Department mai 1", "mai1",
            "Issuer Department mai 2", "mai2"
    );

    public static final Map<ReportStatus, String> MONITORING_STATUS = Map.of(
            ReportStatus.DRAFT, "Draft",
            ReportStatus.WAIT_FOR_APPROVE, "Wait for approval",
            ReportStatus.APPROVE, "Approved",
            ReportStatus.CANCEL, "Canceled"
    );

    public static final List<String> MONITORING_REPORT_HEADERS = Arrays.asList("Symbol",
            "Company Name",
            "Market",
            "Industry",
            "Sector",
            "Fiscal Year End",
            "One Report Year",
            "แบบรายงาน 56-1 One Report",
            "Report ID",
            "เลือกภาษา",
            "One Report_TH",
            "Action Date_TH",
            "One Report_EN",
            "Action Date_EN",
            "RM Name",
            "Team");

    public static final String REMARK_DELIMITER = "_";

    public static final Map<RemarkSection, String> REMARK_SECTION_REPORT_DETAIL = Stream.of(new Object[][]{
            {RemarkSection.BAC01, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {RemarkSection.BAC02, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {RemarkSection.BAC03, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {RemarkSection.BAC04, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {RemarkSection.BAC05, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {RemarkSection.SIM01, Constants.STAKEHOLDERS_IMPACT_MGMT},
            {RemarkSection.SIM02, Constants.STAKEHOLDERS_IMPACT_MGMT},
            {RemarkSection.BC01, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC02, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC0201, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.PRODUCT_INFO, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC0202, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC0203, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC0204, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.BC0205, Constants.BUSINESS_CHARACTERISTICS},
            {RemarkSection.OPERATIONAL_ORGANIZATION_POLICY, SHAREHOLDING_STRUCTURE},
            {RemarkSection.SS02, SHAREHOLDING_STRUCTURE},
            {RemarkSection.SS03, SHAREHOLDING_STRUCTURE},
            {RemarkSection.SHAREHOLDING_DIAGRAM, SHAREHOLDING_STRUCTURE},
            {RemarkSection.TEN_PERCENT, SHAREHOLDING_STRUCTURE},
            {RemarkSection.SS04, SHAREHOLDING_STRUCTURE},
            {RemarkSection.MAJOR_SHAREHOLDERS, SHAREHOLDING_STRUCTURE},
            {RemarkSection.SHAREHOLDER_AGREEMENT, SHAREHOLDING_STRUCTURE},
            {RemarkSection.RCAPC01, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {RemarkSection.RCAPC02, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {RemarkSection.RCAPC03, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {RemarkSection.IOS01, ISSUANCE_OF_OTHER_SECURITIES},
            {RemarkSection.IOS02, ISSUANCE_OF_OTHER_SECURITIES},
            {RemarkSection.RMPAP01, Constants.RISK_MGMT_POLICY_AND_PLAN},
            {RemarkSection.RF01, RISK_FACTORS},
            {RemarkSection.RF02, RISK_FACTORS},
            {RemarkSection.RF03, RISK_FACTORS},
            {RemarkSection.SUSTAINABILITY_POLICY, SUSTAINABILITY_POLICY_AND_GOAL},
            {RemarkSection.SUSTAINABILITY_GOAL, SUSTAINABILITY_POLICY_AND_GOAL},
            {RemarkSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL, SUSTAINABILITY_POLICY_AND_GOAL},
            {RemarkSection.ESM01, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.ENVIRONMENTAL_ISSUE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.REVIEWED_POLICY, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.ENERGY_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.ENERGY_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.ENERGY_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.FUEL_SUMMARY, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WATER_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WATER_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WATER_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WASTE_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WASTE_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.WASTE_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_COMPLIANCE_STANDARDS, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_NET_ZERO, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_CARBON, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.GH_GAS_OTHER, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.VERIFY_DEPT_OF_GH_GAS, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.OTHER_ENVIRONMENTAL_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.INCIDENT_ENVIRONMENTAL_INFO, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {RemarkSection.SSM01, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.HUMAN_RIGHTS_COMPLIANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.REVIEWED_HUMAN_RIGHTS_ISSUE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.HRDD, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.EMPLOYEE_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.EMPLOYEE_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.EMPLOYEE_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.EMPLOYMENT, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.CUSTOMER_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.CUSTOMER_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.CUSTOMER_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.SOCIAL_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.SOCIAL_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.SOCIAL_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.OTHER_SOCIAL_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.INCIDENT_SOCIAL_INFO, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {RemarkSection.OVERALL_OPERATION, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {RemarkSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {RemarkSection.DEBT_SECURITIES_ISSUANCE, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {RemarkSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION_FUTURE, Constants.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION},
            {RemarkSection.PROJECT_OR_RESEARCH, Constants.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION},
            {RemarkSection.OTHER_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION, Constants.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION},
            {RemarkSection.GI01, Constants.GENERAL_INFORMATION},
            {RemarkSection.OMF01, Constants.OTHER_MATERIAL_FACTS},
            {RemarkSection.OMF02, Constants.OTHER_MATERIAL_FACTS},
            {RemarkSection.LEGAL_DISPUTES_INFO, Constants.LEGAL_DISPUTES},
            {RemarkSection.OP02, Constants.OVERVIEW_POLICIES},
            {RemarkSection.COC01, Constants.CODE_OF_CONDUCT},
            {RemarkSection.COC_ISSUE, Constants.CODE_OF_CONDUCT},
            {RemarkSection.ENCOURAGE_COMPLIANCE, Constants.CODE_OF_CONDUCT},
            {RemarkSection.AGAINST_FRAUD_AND_CORRUPTION, Constants.CODE_OF_CONDUCT},
            {RemarkSection.CADP01, Constants.CHANGES_AND_DEV_POLICY},
            {RemarkSection.CADP02, Constants.CHANGES_AND_DEV_POLICY},
            {RemarkSection.CADP03, Constants.CHANGES_AND_DEV_POLICY},
            {RemarkSection.BOD_OPINION_SUMMARY, Constants.INTERNAL_CONTROL},
            {RemarkSection.IC01, Constants.INTERNAL_CONTROL},
            {RemarkSection.IC02, Constants.INTERNAL_CONTROL},
            {RemarkSection.IC03, Constants.INTERNAL_CONTROL},
            {RemarkSection.IC04, Constants.INTERNAL_CONTROL},
            {RemarkSection.IC05, Constants.INTERNAL_CONTROL},
            {RemarkSection.RELATED_PARTY_TRANSACTIONS, Constants.RELATED_PARTY_TRANSACTIONS},
            {RemarkSection.RPT02, Constants.RELATED_PARTY_TRANSACTIONS},
            {RemarkSection.RPT03, Constants.RELATED_PARTY_TRANSACTIONS},
            {RemarkSection.RPT04, Constants.RELATED_PARTY_TRANSACTIONS},
            {RemarkSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.DIRECTOR_DEVELOPMENT, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.BODPS0102, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.BODPS0202, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.DIRECTOR_REMUNERATION_CHARACTERISTIC, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.DIRECTOR_REMUNERATION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.PENDING_PAYMENT, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {RemarkSection.FSFR01, Constants.FINANCIAL_STATEMENTS_AND_FINANCIAL_RATIOS},
            {RemarkSection.FSFR02, Constants.FINANCIAL_STATEMENTS_AND_FINANCIAL_RATIOS},
    }).collect(Collectors.toMap(data -> (RemarkSection) data[0], data -> (String) data[1]));

    public static final Map<IntroductionSection, String> INTRODUCTION_SECTION_REPORT_DETAIL = Stream.of(new Object[][]{
            {IntroductionSection.BAC01, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {IntroductionSection.BAC02, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {IntroductionSection.BAC03, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {IntroductionSection.BAC04, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {IntroductionSection.BAC05, Constants.BUSINESS_AND_CORPORATE_POLICY},
            {IntroductionSection.SIM01, Constants.STAKEHOLDERS_IMPACT_MGMT},
            {IntroductionSection.SIM02, Constants.STAKEHOLDERS_IMPACT_MGMT},
            {IntroductionSection.BC01, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC02, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC0201, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.PRODUCT_INFO, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC0202, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC0203, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC0204, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.BC0205, Constants.BUSINESS_CHARACTERISTICS},
            {IntroductionSection.OPERATIONAL_ORGANIZATION_POLICY, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.SS02, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.SS03, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.SHAREHOLDING_DIAGRAM, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.TEN_PERCENT, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.SS04, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.MAJOR_SHAREHOLDERS, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.SHAREHOLDER_AGREEMENT, SHAREHOLDING_STRUCTURE},
            {IntroductionSection.RCAPC01, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {IntroductionSection.RCAPC02, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {IntroductionSection.RCAPC03, REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL},
            {IntroductionSection.IOS01, ISSUANCE_OF_OTHER_SECURITIES},
            {IntroductionSection.IOS02, ISSUANCE_OF_OTHER_SECURITIES},
            {IntroductionSection.RMPAP01, Constants.RISK_MGMT_POLICY_AND_PLAN},
            {IntroductionSection.RF01, RISK_FACTORS},
            {IntroductionSection.RF02, RISK_FACTORS},
            {IntroductionSection.RF03, RISK_FACTORS},
            {IntroductionSection.SUSTAINABILITY_POLICY, SUSTAINABILITY_POLICY_AND_GOAL},
            {IntroductionSection.SUSTAINABILITY_GOAL, SUSTAINABILITY_POLICY_AND_GOAL},
            {IntroductionSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL, SUSTAINABILITY_POLICY_AND_GOAL},
            {IntroductionSection.ESM01, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.ENVIRONMENTAL_ISSUE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.REVIEWED_POLICY, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.ENERGY_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.ENERGY_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.ENERGY_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.FUEL_SUMMARY, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WATER_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WATER_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WATER_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WASTE_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WASTE_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.WASTE_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_COMPLIANCE_STANDARDS, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_TARGET, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_MGMT_PERFORMANCE, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_NET_ZERO, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_CARBON, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.GH_GAS_OTHER, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.VERIFY_DEPT_OF_GH_GAS, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.OTHER_ENVIRONMENTAL_MGMT_PLAN, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.INCIDENT_ENVIRONMENTAL_INFO, Constants.ENVIRONMENTAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.SSM01, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.HUMAN_RIGHTS_COMPLIANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.REVIEWED_HUMAN_RIGHTS_ISSUE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.HRDD, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.EMPLOYEE_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.EMPLOYEE_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.EMPLOYEE_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.EMPLOYMENT, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.CUSTOMER_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.CUSTOMER_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.CUSTOMER_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.SOCIAL_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.SOCIAL_MGMT_TARGET, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.SOCIAL_MGMT_PERFORMANCE, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.OTHER_SOCIAL_MGMT_PLAN, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.INCIDENT_SOCIAL_INFO, Constants.SOCIAL_SUSTAINABILITY_MGMT},
            {IntroductionSection.OVERALL_OPERATION, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {IntroductionSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {IntroductionSection.DEBT_SECURITIES_ISSUANCE, Constants.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION},
            {IntroductionSection.GI01, Constants.GENERAL_INFORMATION},
            {IntroductionSection.LEGAL_DISPUTES_INFO, Constants.LEGAL_DISPUTES},
            {IntroductionSection.OP02, Constants.OVERVIEW_POLICIES},
            {IntroductionSection.COC01, Constants.CODE_OF_CONDUCT},
            {IntroductionSection.COC_ISSUE, Constants.CODE_OF_CONDUCT},
            {IntroductionSection.ENCOURAGE_COMPLIANCE, Constants.CODE_OF_CONDUCT},
            {IntroductionSection.AGAINST_FRAUD_AND_CORRUPTION, Constants.CODE_OF_CONDUCT},
            {IntroductionSection.CADP01, Constants.CHANGES_AND_DEV_POLICY},
            {IntroductionSection.CADP02, Constants.CHANGES_AND_DEV_POLICY},
            {IntroductionSection.CADP03, Constants.CHANGES_AND_DEV_POLICY},
            {IntroductionSection.BOD_OPINION_SUMMARY, Constants.INTERNAL_CONTROL},
            {IntroductionSection.IC01, Constants.INTERNAL_CONTROL},
            {IntroductionSection.IC02, Constants.INTERNAL_CONTROL},
            {IntroductionSection.IC03, Constants.INTERNAL_CONTROL},
            {IntroductionSection.IC04, Constants.INTERNAL_CONTROL},
            {IntroductionSection.IC05, Constants.INTERNAL_CONTROL},
            {IntroductionSection.RELATED_PARTY_TRANSACTIONS, Constants.RELATED_PARTY_TRANSACTIONS},
            {IntroductionSection.RPT02, Constants.RELATED_PARTY_TRANSACTIONS},
            {IntroductionSection.RPT03, Constants.RELATED_PARTY_TRANSACTIONS},
            {IntroductionSection.RPT04, Constants.RELATED_PARTY_TRANSACTIONS},
            {IntroductionSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.DIRECTOR_DEVELOPMENT, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.BODPS0102, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.BODPS0202, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.DIRECTOR_REMUNERATION_CHARACTERISTIC, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.DIRECTOR_REMUNERATION, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.PENDING_PAYMENT, Constants.BOARD_OF_DIRECTORS_PERF_SUMMARY},
            {IntroductionSection.FSFR01, Constants.FINANCIAL_STATEMENTS_AND_FINANCIAL_RATIOS},
            {IntroductionSection.FSFR02, Constants.FINANCIAL_STATEMENTS_AND_FINANCIAL_RATIOS},
    }).collect(Collectors.toMap(data -> (IntroductionSection) data[0], data -> (String) data[1]));

    public static List<RemarkSection> REMARK_SECTION_STRUCTURE_AND_OPERATION = Arrays.asList(
            RemarkSection.BAC05,
            RemarkSection.BC01,
            RemarkSection.COMPANY_INFO,
            RemarkSection.PRODUCT_INFO
    );

    public static List<RemarkSection> REMARK_SECTION_BUSINESS_CHARACTERISTICS = Arrays.asList(
            RemarkSection.BUSINESS_CHARACTERISTICS,
            RemarkSection.BC01,
            RemarkSection.BC02,
            RemarkSection.BC0201,
            RemarkSection.PRODUCT_INFO,
            RemarkSection.BC0202,
            RemarkSection.BC0203,
            RemarkSection.BC0204,
            RemarkSection.BC0205,
            RemarkSection.RESEARCH_COST
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_BUSINESS_CHARACTERISTICS = Arrays.asList(
            IntroductionSection.BUSINESS_CHARACTERISTICS,
            IntroductionSection.BC01,
            IntroductionSection.BC02,
            IntroductionSection.BC0201,
            IntroductionSection.PRODUCT_INFO,
            IntroductionSection.BC0202,
            IntroductionSection.BC0203,
            IntroductionSection.BC0204,
            IntroductionSection.BC0205
    );

    public static List<RemarkSection> REMARK_SECTION_CORPORATE_GOVERNANCE_POLICY = Arrays.asList(
            RemarkSection.OVERVIEW_POLICIES,
            RemarkSection.DIRECTOR_ISSUE,
            RemarkSection.CODE_OF_CONDUCT,
            RemarkSection.COC_ISSUE
    );

    public static List<RemarkSection> REMARK_SECTION_RESULT_OF_CORPORATE_GOVERNANCE = Arrays.asList(
            RemarkSection.DEVELOP_DIRECTOR,
            RemarkSection.ASSESSMENT,
            RemarkSection.DIRECTOR_MEETING,
            RemarkSection.DIRECTOR_REMUNERATION,
            RemarkSection.AUDIT_COMMITTEE_MEETING
    );

    public static List<RemarkSection> REMARK_SECTION_CORPORATE_STRUCTURE = Arrays.asList(
            RemarkSection.PRESENT_BOD,
            RemarkSection.PAST_BOD,
            RemarkSection.SAME_FAMILY,
            RemarkSection.INDEPENDENT_DETERMINE_AGENDA,
            RemarkSection.PRESENT_AUDIT_COMMITTEE,
            RemarkSection.PAST_AUDIT_COMMITTEE,
            RemarkSection.PRESENT_EXECUTIVE_COMMITTEE,
            RemarkSection.PAST_EXECUTIVE_COMMITTEE,
            RemarkSection.PRESENT_SUB_COMMITTEE,
            RemarkSection.RISK_MANAGEMENT,
            RemarkSection.NOMINATION,
            RemarkSection.REMUNERATION,
            RemarkSection.NOMINATION_REMUNERATION,
            RemarkSection.SUSTAINABILITY,
            RemarkSection.PRESENT_EXECUTIVE,
            RemarkSection.EXECUTIVE_REMUNERATION_POLICY,
            RemarkSection.EXECUTIVE_REMUNERATION,
            RemarkSection.ESOP,
            RemarkSection.EJIP,
            RemarkSection.EMPLOYEE_TOTAL,
            RemarkSection.EMPLOYEE_REMUNERATION,
            RemarkSection.NUMBER_OF_PVD,
            RemarkSection.PERCENT_PVD,
            RemarkSection.ACCOUNTANT,
            RemarkSection.SECRETARY,
            RemarkSection.INTERNAL_AUDIT,
            RemarkSection.COMPLIANCE,
            RemarkSection.INVESTOR_RELATION,
            RemarkSection.AUDITOR_INFO
    );

    public static List<RemarkSection> REMARK_SECTION_ESG = Arrays.asList(
            RemarkSection.SUSTAINABILITY_POLICY_AND_GOAL,
            RemarkSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT,
            RemarkSection.ENVIRONMENTAL_ISSUE,
            RemarkSection.FUEL_SUMMARY,
            RemarkSection.ELECTRICITY_CONSUMPTION,
            RemarkSection.WATER_CONSUMPTION,
            RemarkSection.TOTAL_NON_HAZARDOUS,
            RemarkSection.TOTAL_HAZARDOUS,
            RemarkSection.WASTE_SUMMARY_TOTAL,
            RemarkSection.GH_GAS_GOAL,
            RemarkSection.GH_GAS_SCOPE1,
            RemarkSection.GH_GAS_SCOPE2,
            RemarkSection.GH_GAS_SCOPE3,
            RemarkSection.GREEN_HOUSE_GAS_SUMMARY_TOTAL,
            RemarkSection.VERIFY_DEPT_OF_GH_GAS,
            RemarkSection.SOCIAL_SUSTAINABILITY_MGMT,
            RemarkSection.HUMAN_RIGHTS_ISSUE,
            RemarkSection.EMPLOYEE_INFO,
            RemarkSection.EMPLOYEE_REMUNERATION,
            RemarkSection.TRAINING_HOURS,
            RemarkSection.TRAINING_EXPENSES,
            RemarkSection.OCCUPATIONAL_ACCIDENTS,
            RemarkSection.EMPLOYEE_RELATIONSHIP,
            RemarkSection.LABOR_DISPUTE
    );

    public static List<RemarkSection> REMARK_SECTION_BUSINESS_AND_CORPORATE_POLICY = Arrays.asList(
            RemarkSection.BUSINESS_AND_CORPORATE_POLICY,
            RemarkSection.BAC01,
            RemarkSection.BAC02,
            RemarkSection.BAC03,
            RemarkSection.BAC04,
            RemarkSection.BAC05
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_BUSINESS_AND_CORPORATE_POLICY = Arrays.asList(
            IntroductionSection.BUSINESS_AND_CORPORATE_POLICY,
            IntroductionSection.BAC01,
            IntroductionSection.BAC02,
            IntroductionSection.BAC03,
            IntroductionSection.BAC04,
            IntroductionSection.BAC05
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_DIVIDEND_POLICY = Arrays.asList(
            IntroductionSection.DIVIDEND_POLICY
    );

    public static List<RemarkSection> REMARK_SECTION_DIVIDEND_POLICY = Arrays.asList(
            RemarkSection.DIVIDEND_POLICY
    );

    public static final Map<String, RemarkSection> INFO_CONFIG_NAME_REMARK_SECTION = Stream.of(new Object[][]{
            {"totalElectricityConsumption" + InfoCategory.ELECTRIC.getValue(), RemarkSection.ELECTRICITY_CONSUMPTION},
            {"totalWaterWithdrawal" + InfoCategory.WATER.getValue(), RemarkSection.WATER_CONSUMPTION},
            {"totalNonHazardous" + InfoCategory.WASTE.getValue(), RemarkSection.TOTAL_NON_HAZARDOUS},
            {"totalHazardous" + InfoCategory.WASTE.getValue(), RemarkSection.TOTAL_HAZARDOUS},
            {"total" + InfoCategory.WASTE.getValue(), RemarkSection.WASTE_SUMMARY_TOTAL},
            {"ghGasScope1" + InfoCategory.GREEN_HOUSE_GAS.getValue(), RemarkSection.GH_GAS_SCOPE1},
            {"ghGasScope2" + InfoCategory.GREEN_HOUSE_GAS.getValue(), RemarkSection.GH_GAS_SCOPE2},
            {"ghGasScope3" + InfoCategory.GREEN_HOUSE_GAS.getValue(), RemarkSection.GH_GAS_SCOPE3},
            {"total" + InfoCategory.GREEN_HOUSE_GAS.getValue(), RemarkSection.GREEN_HOUSE_GAS_SUMMARY_TOTAL},
            {"ghGasGoal" + InfoCategory.GREEN_HOUSE_GAS.getValue(), RemarkSection.GH_GAS_GOAL},
            {"employeeRemuneration" + InfoCategory.EMPLOYEE_REMUNERATION.getValue(), RemarkSection.EMPLOYEE_REMUNERATION},
            {"trainingHours" + InfoCategory.EMPLOYEE_TRAINING.getValue(), RemarkSection.TRAINING_HOURS},
            {"trainingExpenses" + InfoCategory.EMPLOYEE_TRAINING.getValue(), RemarkSection.TRAINING_EXPENSES},
            {"occupationalAccidents" + InfoCategory.EMPLOYEE_SAFETY.getValue(), RemarkSection.OCCUPATIONAL_ACCIDENTS},
            {"employeeRelationship" + InfoCategory.EMPLOYEE_RELATIONSHIP.getValue(), RemarkSection.EMPLOYEE_RELATIONSHIP},
            {"laborDispute" + InfoCategory.LABOR_DISPUTE.getValue(), RemarkSection.LABOR_DISPUTE},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (RemarkSection) data[1]));


    public static final List<BigDecimal> PHASE_1_1_5 = Arrays.asList(BigDecimal.ONE, BigDecimal.valueOf(1.5));

    public static final Map<String, Pair<String, String>> PUBLISH_APPROVE_UPLOAD_MAP_BY_LANGUAGE = Map.of(
            PUBLISH_REPORT_EN, new Pair<>(REVIEW_AND_APPROVE_EN, REVIEW_AND_UPLOAD_EN),
            PUBLISH_REPORT_TH, new Pair<>(REVIEW_AND_APPROVE_TH, REVIEW_AND_UPLOAD_TH)
    );

    public static final String T = "T";
    public static final String E = "E";
    public static final String B = "B";

    public static List<RemarkSection> REMARK_SECTION_ESG_PARAMETER = Arrays.asList(
            RemarkSection.SUSTAINABILITY_POLICY_AND_GOAL,
            RemarkSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT,
            RemarkSection.ENVIRONMENTAL_ISSUE,
            RemarkSection.FUEL_SUMMARY,
            RemarkSection.ELECTRICITY_CONSUMPTION,
            RemarkSection.WATER_CONSUMPTION,
            RemarkSection.VERIFY_DEPT_OF_GH_GAS,
            RemarkSection.SOCIAL_SUSTAINABILITY_MGMT,
            RemarkSection.HUMAN_RIGHTS_ISSUE,
            RemarkSection.EMPLOYEE_INFO,
            RemarkSection.EMPLOYEE_REMUNERATION,
            RemarkSection.OCCUPATIONAL_ACCIDENTS,
            RemarkSection.EMPLOYEE_RELATIONSHIP,
            RemarkSection.LABOR_DISPUTE
    );


    public static List<RemarkSection> REMARK_SECTION_CORPORATE_STRUCTURE_PARAMETER = Arrays.asList(
            RemarkSection.PRESENT_BOD,
            RemarkSection.PAST_BOD,
            RemarkSection.PRESENT_AUDIT_COMMITTEE,
            RemarkSection.PAST_AUDIT_COMMITTEE,
            RemarkSection.PRESENT_EXECUTIVE_COMMITTEE,
            RemarkSection.PAST_EXECUTIVE_COMMITTEE,
            RemarkSection.PRESENT_SUB_COMMITTEE,
            RemarkSection.PRESENT_EXECUTIVE,
            RemarkSection.EXECUTIVE_REMUNERATION_POLICY,
            RemarkSection.EXECUTIVE_REMUNERATION,
            RemarkSection.EMPLOYEE_TOTAL,
            RemarkSection.EMPLOYEE_REMUNERATION,
            RemarkSection.ACCOUNTANT,
            RemarkSection.SECRETARY,
            RemarkSection.INTERNAL_AUDIT,
            RemarkSection.COMPLIANCE,
            RemarkSection.INVESTOR_RELATION,
            RemarkSection.AUDITOR_INFO
    );

    public static final List<Pair<String, List<String>>> REAMRK_STRUCTURE_AND_OPERATION_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessAndCorporatePolicyRemark", List.of(RemarkSection.COMPANY_INFO.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_STRUCTURE_AND_OPERATION_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessAndCorporatePolicyIntroduction", List.of(IntroductionSection.COMPANY_INFO.getValue())));
    }};

    public static final List<Pair<String, List<String>>> REAMRK_BUSINESS_AND_CORPORATE_POLICY_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessAndCorporatePolicyRemark", List.of(RemarkSection.BUSINESS_AND_CORPORATE_POLICY.getValue())));
        add(new Pair<>("bac01Remark", List.of(RemarkSection.BAC01.getValue())));
        add(new Pair<>("bac02Remark", List.of(RemarkSection.BAC02.getValue())));
        add(new Pair<>("bac03Remark", List.of(RemarkSection.BAC03.getValue())));
        add(new Pair<>("bac04Remark", List.of(RemarkSection.BAC04.getValue())));
        add(new Pair<>("bac05Remark", List.of(RemarkSection.BAC05.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_BUSINESS_AND_CORPORATE_POLICY_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessAndCorporatePolicyIntroduction", List.of(IntroductionSection.BUSINESS_AND_CORPORATE_POLICY.getValue())));
        add(new Pair<>("bac01Introduction", List.of(IntroductionSection.BAC01.getValue())));
        add(new Pair<>("bac02Introduction", List.of(IntroductionSection.BAC02.getValue())));
        add(new Pair<>("bac03Introduction", List.of(IntroductionSection.BAC03.getValue())));
        add(new Pair<>("bac04Introduction", List.of(IntroductionSection.BAC04.getValue())));
        add(new Pair<>("bac05Introduction", List.of(IntroductionSection.BAC05.getValue())));
    }};

    public static final List<Pair<String, List<String>>> REMARK_BUSINESS_CHARACTERISTICS_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessCharacteristicsRemark", List.of(RemarkSection.BUSINESS_CHARACTERISTICS.getValue())));
        add(new Pair<>("bc01Remark", List.of(RemarkSection.BC01.getValue())));
        add(new Pair<>("bc02Remark", List.of(RemarkSection.BC02.getValue())));
        add(new Pair<>("bc0201Remark", List.of(RemarkSection.BC0201.getValue())));
        add(new Pair<>("researchCostRemark", List.of(RemarkSection.RESEARCH_COST.getValue())));
        add(new Pair<>("productInfoRemark", List.of(RemarkSection.PRODUCT_INFO.getValue())));
        add(new Pair<>("bc0202Remark", List.of(RemarkSection.BC0202.getValue())));
        add(new Pair<>("bc0203Remark", List.of(RemarkSection.BC0203.getValue())));
        add(new Pair<>("bc0204Remark", List.of(RemarkSection.BC0204.getValue())));
        add(new Pair<>("bc0205Remark", List.of(RemarkSection.BC0205.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_BUSINESS_CHARACTERISTICS_SECTION = new ArrayList<>() {{
        add(new Pair<>("businessCharacteristicsIntroduction", List.of(IntroductionSection.BUSINESS_CHARACTERISTICS.getValue())));
        add(new Pair<>("bc01Introduction", List.of(IntroductionSection.BC01.getValue())));
        add(new Pair<>("bc02Introduction", List.of(IntroductionSection.BC02.getValue())));
        add(new Pair<>("bc0201Introduction", List.of(IntroductionSection.BC0201.getValue())));
        add(new Pair<>("productInfoIntroduction", List.of(IntroductionSection.PRODUCT_INFO.getValue())));
        add(new Pair<>("bc0202Introduction", List.of(IntroductionSection.BC0202.getValue())));
        add(new Pair<>("bc0203Introduction", List.of(IntroductionSection.BC0203.getValue())));
        add(new Pair<>("bc0204Introduction", List.of(IntroductionSection.BC0204.getValue())));
        add(new Pair<>("bc0205Introduction", List.of(IntroductionSection.BC0205.getValue())));
    }};


    public static List<RemarkSection> REMARK_SECTION_SHAREHOLDING_STRUCTURE = Arrays.asList(
            RemarkSection.SHAREHOLDING_STRUCTURE,
            RemarkSection.SS01,
            RemarkSection.OPERATIONAL_ORGANIZATION_POLICY,
            RemarkSection.SHAREHOLDING_DIAGRAM,
            RemarkSection.TEN_PERCENT,
            RemarkSection.SS02,
            RemarkSection.SS03,
            RemarkSection.SS04,
            RemarkSection.MAJOR_SHAREHOLDERS,
            RemarkSection.SHAREHOLDER_AGREEMENT
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_SHAREHOLDING_STRUCTURE = Arrays.asList(
            IntroductionSection.SHAREHOLDING_STRUCTURE,
            IntroductionSection.SS01,
            IntroductionSection.OPERATIONAL_ORGANIZATION_POLICY,
            IntroductionSection.SHAREHOLDING_DIAGRAM,
            IntroductionSection.TEN_PERCENT,
            IntroductionSection.SS02,
            IntroductionSection.SS03,
            IntroductionSection.SS04,
            IntroductionSection.MAJOR_SHAREHOLDERS,
            IntroductionSection.SHAREHOLDER_AGREEMENT
    );

    public static final List<Pair<String, List<String>>> REMARK_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS = new ArrayList<>() {{
        add(new Pair<>("shareholdingStructureRemark", List.of(RemarkSection.SHAREHOLDING_STRUCTURE.getValue())));
        add(new Pair<>("ss01Remark", List.of(RemarkSection.SS01.getValue())));
        add(new Pair<>("operationalOrganizationPolicyRemark", List.of(RemarkSection.OPERATIONAL_ORGANIZATION_POLICY.getValue())));
        add(new Pair<>("shareholdingDiagramRemark", List.of(RemarkSection.SHAREHOLDING_DIAGRAM.getValue())));
        add(new Pair<>("tenPercentRemark", List.of(RemarkSection.TEN_PERCENT.getValue())));
        add(new Pair<>("ss02Remark", List.of(RemarkSection.SS02.getValue())));
        add(new Pair<>("ss03Remark", List.of(RemarkSection.SS03.getValue())));
        add(new Pair<>("ss04Remark", List.of(RemarkSection.SS04.getValue())));
        add(new Pair<>("majorShareholdersRemark", List.of(RemarkSection.MAJOR_SHAREHOLDERS.getValue())));
        add(new Pair<>("shareholderAgreementRemark", List.of(RemarkSection.SHAREHOLDER_AGREEMENT.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_SHAREHOLDING_STRUCTURE_PARAMETER_KEYS = new ArrayList<>() {{
        add(new Pair<>("shareholdingStructureIntroduction", List.of(IntroductionSection.SHAREHOLDING_STRUCTURE.getValue())));
        add(new Pair<>("ss01Introduction", List.of(IntroductionSection.SS01.getValue())));
        add(new Pair<>("operationalOrganizationPolicyIntroduction", List.of(IntroductionSection.OPERATIONAL_ORGANIZATION_POLICY.getValue())));
        add(new Pair<>("shareholdingDiagramIntroduction", List.of(IntroductionSection.SHAREHOLDING_DIAGRAM.getValue())));
        add(new Pair<>("tenPercentIntroduction", List.of(IntroductionSection.TEN_PERCENT.getValue())));
        add(new Pair<>("ss02Introduction", List.of(IntroductionSection.SS02.getValue())));
        add(new Pair<>("ss03Introduction", List.of(IntroductionSection.SS03.getValue())));
        add(new Pair<>("ss04Introduction", List.of(IntroductionSection.SS04.getValue())));
        add(new Pair<>("majorShareholdersIntroduction", List.of(IntroductionSection.MAJOR_SHAREHOLDERS.getValue())));
        add(new Pair<>("shareholderAgreementIntroduction", List.of(IntroductionSection.SHAREHOLDER_AGREEMENT.getValue())));
    }};

    public static final List<Pair<String, List<String>>> REMARK_DIVIDEND_POLICY_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("dividendPolicyRemark", List.of(RemarkSection.DIVIDEND_POLICY.getValue())));
                add(new Pair<>("dividendPolicyInfoRemark", List.of(
                        RemarkSection.NET_PROFIT_PER_SHARE.getValue(),
                        RemarkSection.DIVIDEND_PER_SHARE.getValue(),
                        RemarkSection.DIVIDEND_STOCK_RATIO.getValue(),
                        RemarkSection.DIVIDEND_SHARE_VALUE_PER_SHARE.getValue()
                )));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_DIVIDEND_POLICY_SECTION = new ArrayList<>() {{
        add(new Pair<>("dividendPolicyIntroduction", List.of(RemarkSection.DIVIDEND_POLICY.getValue())));
    }};

    public static final Map<String, String> MAP_NODE_CONFIG = Stream.of(new Object[][]{
            {BAC_05, BUSINESS_AND_CORPORATE_POLICY},
            {BC_01, BUSINESS_CHARACTERISTICS},
            {BC_02, BUSINESS_CHARACTERISTICS},
            {COC_01, CORPORATE_GOVERNANCE_POLICY},
            {SPAG_01, SUSTAINABILITY_POLICY_AND_GOAL},
            {OVERVIEW_POLICIES, CORPORATE_GOVERNANCE_POLICY},
            {CODE_OF_CONDUCT, CORPORATE_GOVERNANCE_POLICY},
            {BOARD_OF_DIRECTORS_INFORMATION, CORPORATE_GOVERNANCE_STRUCTURE},
            {OTHER_SIGNIFICANT_INFORMATION, CORPORATE_GOVERNANCE_STRUCTURE},
            {BOARD_OF_DIRECTORS_PERF_SUMMARY, RESULT_OF_CORPORATE_GOVERNANCE},
            {SUBCOMMITTEES_INFORMATION, CORPORATE_GOVERNANCE_STRUCTURE},
            {AUDIT_COMMITTEES_PERF_SUMMARY, RESULT_OF_CORPORATE_GOVERNANCE},
            {EXECUTIVES_INFORMATION, CORPORATE_GOVERNANCE_STRUCTURE},
            {EMPLOYEES_INFORMATION, EMPLOYEES},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    public static final Map<String, DividendInfoType> DIVIDEND_INFO_NAME_TO_TYPE_MAP = Stream.of(new Object[][]{
            {"netProfitPerShare", DividendInfoType.NET_PROFIT_PER_SHARE},
            {"dividendRatePerShare", DividendInfoType.DIVIDEND_PER_SHARE},
            {"dividendStockRatio", DividendInfoType.DIVIDEND_STOCK_RATIO},
            {"dividendShareValuePerShare", DividendInfoType.DIVIDEND_SHARE_VALUE_PER_SHARE},
            {"totalDividendPayment", DividendInfoType.DIVIDEND_SHARE_VALUE_PER_SHARE},
            {"dividendPayoutRatio", DividendInfoType.DIVIDEND_SHARE_VALUE_PER_SHARE},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (DividendInfoType) data[1]));

    public static final Map<DividendInfoType, String> DIVIDEND_INFO_TYPE_TO_NAME_MAP = Stream.of(new Object[][]{
            {DividendInfoType.DIVIDEND_PER_SHARE, "dividendRatePerShare"},
            {DividendInfoType.DIVIDEND_STOCK_RATIO, "dividendStockRatio"},
    }).collect(Collectors.toMap(data -> (DividendInfoType) data[0], data -> (String) data[1]));

    public static final Map<String, Long> DIVIDEND_INFO_SEQUNECE_MAP = Stream.of(new Object[][]{
            {"netProfitPerShare", 1L},
            {"dividendRatePerShare", 1L},
            {"dividendStockRatio", 1L},
            {"dividendShareValuePerShare", 1L},
            {"totalDividendPayment", 2L},
            {"dividendPayoutRatio", 3L},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Long) data[1]));

    public static List<IntroductionSection> INTRODUCTION_SECTION_STRUCTURE_AND_OPERATION = Arrays.asList(
            IntroductionSection.BAC05,
            IntroductionSection.OP01
    );

    public static final Map<String, String> TARGET_TYPE_MAP = new HashMap<String, String>() {{
        put("redElecPurchasedForCons", DECREASE);
        put("redFuelCons", DECREASE);
        put("redElecPurchasedAndFuelCons", DECREASE);
        put("incElecConsuFromRenewable", INCREASE);
        put("incFuelConsFromRenewable", INCREASE);
        put("incElecAndFuelConsFromRenewable", INCREASE);
        put("redWaterWithdrawal", DECREASE);
        put("redWaterConsumption", DECREASE);
        put("incRecycleWater", INCREASE);
        put("redWasteVolume", DECREASE);
        put("incWasteUtilization", INCREASE);
    }};

    public static List<String> WATER_DISCHARGES = Arrays.asList(
            "percentWastewaterTreated",
            "totalWaterDischarge",
            "waterDischargeThirdPartyWater",
            "waterDischargeSurfaceWater",
            "waterDischargeGroundwater",
            "waterDischargeSeawater"
    );

    public static List<String> TOTAL_WATER_CONSUMPTIONS = List.of(
            "totalWaterConsumption"
    );
    public static List<String> RECYCLED_WATER = List.of(
            "recycledWater"
    );

    public static List<String> WASTE_GENERATION = Arrays.asList(
            "total",
            "totalNonHazardous",
            "nhwLandfilling",
            "nhwIncinerationWEnergyRecovery",
            "nhwIncinerationWoEnergyRecovery",
            "nhwOther",
            "totalHazardous",
            "hwLandfilling",
            "hwIncinerationWEnergyRecovery",
            "hwIncinerationWoEnergyRecovery",
            "hwOther"
    );


    public static List<String> REUSED_RECYCLED_WASTE = Arrays.asList(
            "SumReuseRecycleWaste",
            "recycleNonHazardous",
            "reusedNonHazardousWaste",
            "recycledNonHazardousWaste",
            "recycleHazardous",
            "reusedHazardousWaste",
            "recycledHazardousWaste"
    );

    public static List<String> GH_GAS_SUMMARY = Arrays.asList(
            "total",
            "ghGasScope1",
            "ghGasScope2",
            "ghGasScope3"
    );

    public static List<String> CUSTOM_YEAR_COMPANY = Arrays.asList("LHK", "PTL", "TMW", "TTT", "KYE");
    public static List<RemarkSection> REMARK_SECTION_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION = Arrays.asList(
            RemarkSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION,
            RemarkSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION_FUTURE,
            RemarkSection.PROJECT_OR_RESEARCH,
            RemarkSection.OTHER_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION
    );
    public static final List<Pair<String, List<String>>> REMARK_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION_SECTION = new ArrayList<>() {{
        add(new Pair<>("potentialFactorAffectRemark", List.of(RemarkSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION.getValue())));
        add(new Pair<>("potentialFactorAffectFutureRemark", List.of(RemarkSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION_FUTURE.getValue())));
        add(new Pair<>("projectOrResearchRemark", List.of(RemarkSection.PROJECT_OR_RESEARCH.getValue())));
        add(new Pair<>("otherPotentialFactorAffectRemark", List.of(RemarkSection.OTHER_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION.getValue())));
    }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION = Arrays.asList(
            IntroductionSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION
    );
    public static final List<Pair<String, List<String>>> INTRODUCTION_POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION_SECTION = new ArrayList<>() {{
        add(new Pair<>("potentialFactorAffectIntroduction", List.of(IntroductionSection.POTENTIAL_FACTOR_AFFECT_FINANCIAL_CONDITION.getValue())));
    }};

    //business_type: Production/Service | Finance
    //business_type: Production/Service | Finance
    public static final String PRODUCTION_SERVICE = "Production/Service";
    public static final String FINANCE = "Finance";


    public static final String OVERALL = "Overall";
    public static final String SPECIFIC = "Specific";


    public static List<IntroductionSection> INTRODUCTION_SECTION_ISSUANCE_OF_OTHER_SECURITIES = Arrays.asList(
            IntroductionSection.ISSUANCE_OF_OTHER_SECURITIES,
            IntroductionSection.IOS01,
            IntroductionSection.IOS02
    );

    public static List<RemarkSection> REMARK_SECTION_ISSUANCE_OF_OTHER_SECURITIES = Arrays.asList(
            RemarkSection.ISSUANCE_OF_OTHER_SECURITIES,
            RemarkSection.IOS01,
            RemarkSection.IOS02
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_ISSUANCE_OF_OTHER_SECURITIES_SECTION = new ArrayList<>() {{
        add(new Pair<>("issuanceOfOtherSecuritiesIntroduction",
                List.of(IntroductionSection.ISSUANCE_OF_OTHER_SECURITIES.getValue()
                )));
        add(new Pair<>("ios01Introduction",
                List.of(IntroductionSection.IOS01.getValue()
                )));
        add(new Pair<>("ios02Introduction",
                List.of(IntroductionSection.IOS02.getValue()))
        );
    }};

    public static final List<Pair<String, List<String>>> REMARK_ISSUANCE_OF_OTHER_SECURITIES_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("issuanceOfOtherSecuritiesRemark",
                        List.of(RemarkSection.ISSUANCE_OF_OTHER_SECURITIES.getValue()
                        )));
                add(new Pair<>("ios01Remark",
                        List.of(RemarkSection.IOS01.getValue()
                        )));
                add(new Pair<>("ios02Remark",
                        List.of(RemarkSection.IOS02.getValue()))
                );
            }};


    public static List<IntroductionSection> INTRODUCTION_SECTION_REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL = Arrays.asList(
            IntroductionSection.REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL,
            IntroductionSection.RCAPC01,
            IntroductionSection.RCAPC02,
            IntroductionSection.RCAPC03
    );

    public static List<RemarkSection> REMARK_SECTION_REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL = Arrays.asList(
            RemarkSection.REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL,
            RemarkSection.RCAPC01,
            RemarkSection.RCAPC02,
            RemarkSection.RCAPC03
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL_SECTION = new ArrayList<>() {{
        add(new Pair<>("registeredCapitalAndPaidUpCapitalIntroduction",
                List.of(IntroductionSection.REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL.getValue()
                )));
        add(new Pair<>("rcapc01Introduction",
                List.of(IntroductionSection.RCAPC01.getValue()
                )));
        add(new Pair<>("rcapc02Introduction",
                List.of(IntroductionSection.RCAPC02.getValue()
                )));
        add(new Pair<>("rcapc03Introduction",
                List.of(IntroductionSection.RCAPC03.getValue()))
        );
    }};

    public static final List<Pair<String, List<String>>> REMARK_REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("registeredCapitalAndPaidUpCapitalRemark",
                        List.of(RemarkSection.REGISTERED_CAPITAL_AND_PAID_UP_CAPITAL.getValue()
                        )));
                add(new Pair<>("rcapc01Remark",
                        List.of(RemarkSection.RCAPC01.getValue()
                        )));
                add(new Pair<>("rcapc02Remark",
                        List.of(RemarkSection.RCAPC02.getValue()
                        )));
                add(new Pair<>("rcapc03Remark",
                        List.of(RemarkSection.RCAPC03.getValue()))
                );
            }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_SUSTAINABILITY_POLICY_AND_GOAL = Arrays.asList(
            IntroductionSection.SUSTAINABILITY_POLICY_AND_GOAL,
            IntroductionSection.SUSTAINABILITY_POLICY,
            IntroductionSection.SUSTAINABILITY_GOAL,
            IntroductionSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL
    );

    public static List<RemarkSection> REMARK_SECTION_SUSTAINABILITY_POLICY_AND_GOAL = Arrays.asList(
            RemarkSection.SUSTAINABILITY_POLICY_AND_GOAL,
            RemarkSection.SUSTAINABILITY_POLICY,
            RemarkSection.SUSTAINABILITY_GOAL,
            RemarkSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_SUSTAINABILITY_POLICY_AND_GOAL_SECTION = new ArrayList<>() {{
        add(new Pair<>("sustainabilityPolicyAndGoalIntroduction",
                List.of(IntroductionSection.SUSTAINABILITY_POLICY_AND_GOAL.getValue()
                )));
        add(new Pair<>("sustainabilityPolicyIntroduction",
                List.of(IntroductionSection.SUSTAINABILITY_POLICY.getValue()
                )));
        add(new Pair<>("sustainabilityGoalIntroduction",
                List.of(IntroductionSection.SUSTAINABILITY_GOAL.getValue()
                )));
        add(new Pair<>("reviewedSustainabilityPolicyAndGoalIntroduction",
                List.of(IntroductionSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL.getValue()))
        );
    }};

    public static final List<Pair<String, List<String>>> REMARK_SUSTAINABILITY_POLICY_AND_GOAL_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("sustainabilityPolicyAndGoalRemark",
                        List.of(RemarkSection.SUSTAINABILITY_POLICY_AND_GOAL.getValue()
                        )));
                add(new Pair<>("sustainabilityPolicyRemark",
                        List.of(RemarkSection.SUSTAINABILITY_POLICY.getValue()
                        )));
                add(new Pair<>("sustainabilityGoalRemark",
                        List.of(RemarkSection.SUSTAINABILITY_GOAL.getValue()
                        )));
                add(new Pair<>("reviewedSustainabilityPolicyAndGoalRemark",
                        List.of(RemarkSection.REVIEWED_SUSTAINABILITY_POLICY_AND_GOAL.getValue()))
                );
            }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_ENVIRONMENTAL_SUSTAINABILITY_MGMT = Arrays.asList(
            IntroductionSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT,
            IntroductionSection.ESM01,
            IntroductionSection.ENVIRONMENTAL_ISSUE,
            IntroductionSection.REVIEWED_POLICY,
            IntroductionSection.ESM02,
            IntroductionSection.ESM03,
            IntroductionSection.ENERGY_MGMT_PLAN,
            IntroductionSection.ENERGY_TARGET,
            IntroductionSection.ENERGY_MGMT_PERFORMANCE,
            IntroductionSection.FUEL_SUMMARY,
            IntroductionSection.ELECTRICITY_SUMMARY,
            IntroductionSection.ESM04,
            IntroductionSection.WATER_MGMT_PLAN,
            IntroductionSection.WATER_TARGET,
            IntroductionSection.WATER_MGMT_PERFORMANCE,
            IntroductionSection.WATER_DISCHARGE,
            IntroductionSection.WATER_CONSUMPTION,
            IntroductionSection.WATER_WITHDRAWAL,
            IntroductionSection.RECYCLED_WATER,
            IntroductionSection.ESM05,
            IntroductionSection.WASTE_MGMT_PLAN,
            IntroductionSection.WASTE_TARGET,
            IntroductionSection.WASTE_MGMT_PERFORMANCE,
            IntroductionSection.WASTE_GENERATION,
            IntroductionSection.REUSED_RECYCLED_WASTE,
            IntroductionSection.ESM06,
            IntroductionSection.GH_GAS_MGMT_PLAN,
            IntroductionSection.GH_GAS_COMPLIANCE_STANDARDS,
            IntroductionSection.GH_GAS_TARGET,
            IntroductionSection.GH_GAS_NET_ZERO,
            IntroductionSection.GH_GAS_CARBON,
            IntroductionSection.GH_GAS_OTHER,
            IntroductionSection.GH_GAS_MGMT_PERFORMANCE,
            IntroductionSection.GH_GAS_MGMT,
            IntroductionSection.VERIFY_DEPT_OF_GH_GAS,
            IntroductionSection.ESM07,
            IntroductionSection.OTHER_ENVIRONMENTAL_MGMT_PLAN,
            IntroductionSection.ESM08,
            IntroductionSection.NUMBER_OF_LEGAL_ENVIRONMENTAL_INCIDENT,
            IntroductionSection.INCIDENT_ENVIRONMENTAL_INFO
    );

    public static List<RemarkSection> REMARK_SECTION_ENVIRONMENTAL_SUSTAINABILITY_MGMT = Arrays.asList(
            RemarkSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT,
            RemarkSection.ESM01,
            RemarkSection.ENVIRONMENTAL_ISSUE,
            RemarkSection.REVIEWED_POLICY,
            RemarkSection.ESM02,
            RemarkSection.ESM03,
            RemarkSection.ENERGY_MGMT_PLAN,
            RemarkSection.ENERGY_TARGET,
            RemarkSection.ENERGY_MGMT_PERFORMANCE,
            RemarkSection.ESM04,
            RemarkSection.WATER_MGMT_PLAN,
            RemarkSection.WATER_TARGET,
            RemarkSection.WATER_MGMT_PERFORMANCE,
            RemarkSection.WATER_CONSUMPTION,
            RemarkSection.RECYCLED_WATER,
            RemarkSection.ESM05,
            RemarkSection.WASTE_MGMT_PLAN,
            RemarkSection.WASTE_TARGET,
            RemarkSection.WASTE_MGMT_PERFORMANCE,
            RemarkSection.ESM06,
            RemarkSection.GH_GAS_MGMT_PLAN,
            RemarkSection.GH_GAS_COMPLIANCE_STANDARDS,
            RemarkSection.GH_GAS_TARGET,
            RemarkSection.GH_GAS_NET_ZERO,
            RemarkSection.GH_GAS_CARBON,
            RemarkSection.GH_GAS_OTHER,
            RemarkSection.GH_GAS_MGMT_PERFORMANCE,
            RemarkSection.GREEN_HOUSE_GAS_SUMMARY_TOTAL,
            RemarkSection.GH_GAS_SCOPE1,
            RemarkSection.GH_GAS_SCOPE2,
            RemarkSection.GH_GAS_SCOPE3,
            RemarkSection.VERIFY_DEPT_OF_GH_GAS,
            RemarkSection.ESM07,
            RemarkSection.OTHER_ENVIRONMENTAL_MGMT_PLAN,
            RemarkSection.ESM08,
            RemarkSection.NUMBER_OF_LEGAL_ENVIRONMENTAL_INCIDENT,
            RemarkSection.INCIDENT_ENVIRONMENTAL_INFO,
            RemarkSection.FUEL_SUMMARY
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_ENVIRONMENTAL_SUSTAINABILITY_MGMT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("EnvironmentalSustainabilityMgmtIntroduction",
                        List.of(IntroductionSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT.getValue())));
                add(new Pair<>("Esm01Introduction",
                        List.of(IntroductionSection.ESM01.getValue())));
                add(new Pair<>("EnvironmentalIssueIntroduction",
                        List.of(IntroductionSection.ENVIRONMENTAL_ISSUE.getValue())));
                add(new Pair<>("ReviewedPolicyIntroduction",
                        List.of(IntroductionSection.REVIEWED_POLICY.getValue())));
                add(new Pair<>("Esm02Introduction",
                        List.of(IntroductionSection.ESM02.getValue())));
                add(new Pair<>("Esm03Introduction",
                        List.of(IntroductionSection.ESM03.getValue())));
                add(new Pair<>("EnergyMgmtPlanIntroduction",
                        List.of(IntroductionSection.ENERGY_MGMT_PLAN.getValue())));
                add(new Pair<>("EnergyTargetIntroduction",
                        List.of(IntroductionSection.ENERGY_TARGET.getValue())));
                add(new Pair<>("EnergyMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.ENERGY_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("FuelSummaryIntroduction",
                        List.of(IntroductionSection.FUEL_SUMMARY.getValue())));
                add(new Pair<>("ElectricitySummaryIntroduction",
                        List.of(IntroductionSection.ELECTRICITY_SUMMARY.getValue())));
                add(new Pair<>("Esm04Introduction",
                        List.of(IntroductionSection.ESM04.getValue())));
                add(new Pair<>("WaterMgmtPlanIntroduction",
                        List.of(IntroductionSection.WATER_MGMT_PLAN.getValue())));
                add(new Pair<>("WaterTargetIntroduction",
                        List.of(IntroductionSection.WATER_TARGET.getValue())));
                add(new Pair<>("WaterMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.WATER_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("WaterSummaryIntroduction",
                        List.of(IntroductionSection.WATER_WITHDRAWAL.getValue())));
                add(new Pair<>("WaterDischargeIntroduction",
                        List.of(IntroductionSection.WATER_DISCHARGE.getValue())));
                add(new Pair<>("WaterConsumptionIntroduction",
                        List.of(IntroductionSection.WATER_CONSUMPTION.getValue())));
                add(new Pair<>("WaterRecycledIntroduction",
                        List.of(IntroductionSection.RECYCLED_WATER.getValue())));
                add(new Pair<>("Esm05Introduction",
                        List.of(IntroductionSection.ESM05.getValue())));
                add(new Pair<>("WasteMgmtPlanIntroduction",
                        List.of(IntroductionSection.WASTE_MGMT_PLAN.getValue())));
                add(new Pair<>("WasteMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.WASTE_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("WasteTargetIntroduction",
                        List.of(IntroductionSection.WASTE_TARGET.getValue())));
                add(new Pair<>("WasteGenerationIntroduction",
                        List.of(IntroductionSection.WASTE_GENERATION.getValue())));
                add(new Pair<>("ReusedRecycledWasteIntroduction",
                        List.of(IntroductionSection.REUSED_RECYCLED_WASTE.getValue())));
                add(new Pair<>("Esm06Introduction",
                        List.of(IntroductionSection.ESM06.getValue())));
                add(new Pair<>("GhGasManagementPlanIntroduction",
                        List.of(IntroductionSection.GH_GAS_MGMT_PLAN.getValue())));
                add(new Pair<>("GhGasComplianceStandardsIntroduction",
                        List.of(IntroductionSection.GH_GAS_COMPLIANCE_STANDARDS.getValue())));
                add(new Pair<>("GhGasTargetIntroduction",
                        List.of(IntroductionSection.GH_GAS_TARGET.getValue())));
                add(new Pair<>("GhGasTargetNetZeroIntroduction",
                        List.of(IntroductionSection.GH_GAS_NET_ZERO.getValue())));
                add(new Pair<>("GhGasTargetCarbonIntroduction",
                        List.of(IntroductionSection.GH_GAS_CARBON.getValue())));
                add(new Pair<>("GhGasTargetOtherIntroduction",
                        List.of(IntroductionSection.GH_GAS_OTHER.getValue())));
                add(new Pair<>("GhGasMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.GH_GAS_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("GhGasMgmtIntroduction",
                        List.of(IntroductionSection.GH_GAS_MGMT.getValue())));
                add(new Pair<>("GhGasVerifyDeptIntroduction",
                        List.of(IntroductionSection.VERIFY_DEPT_OF_GH_GAS.getValue())));
                add(new Pair<>("Esm07Introduction",
                        List.of(IntroductionSection.ESM07.getValue())));
                add(new Pair<>("OtherEnvMgmtPlanIntroduction",
                        List.of(IntroductionSection.OTHER_ENVIRONMENTAL_MGMT_PLAN.getValue())));
                add(new Pair<>("Esm08Introduction",
                        List.of(IntroductionSection.ESM08.getValue())));
                add(new Pair<>("NumberLegalEnvironmentalIncidentIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_LEGAL_ENVIRONMENTAL_INCIDENT.getValue())));
                add(new Pair<>("InformationOfLegalEnvironmentalIncidentIntroduction",
                        List.of(IntroductionSection.INCIDENT_ENVIRONMENTAL_INFO.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_ENVIRONMENTAL_SUSTAINABILITY_MGMT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("EnvironmentalSustainabilityMgmtRemark",
                        List.of(RemarkSection.ENVIRONMENTAL_SUSTAINABILITY_MGMT.getValue())));
                add(new Pair<>("Esm01Remark",
                        List.of(RemarkSection.ESM01.getValue())));
                add(new Pair<>("EnvironmentalIssueRemark",
                        List.of(RemarkSection.ENVIRONMENTAL_ISSUE.getValue())));
                add(new Pair<>("ReviewedPolicyRemark",
                        List.of(RemarkSection.REVIEWED_POLICY.getValue())));
                add(new Pair<>("Esm02Remark",
                        List.of(RemarkSection.ESM02.getValue())));
                add(new Pair<>("Esm03Remark",
                        List.of(RemarkSection.ESM03.getValue())));
                add(new Pair<>("EnergyMgmtPlanRemark",
                        List.of(RemarkSection.ENERGY_MGMT_PLAN.getValue())));
                add(new Pair<>("EnergyTargetRemark",
                        List.of(RemarkSection.ENERGY_TARGET.getValue())));
                add(new Pair<>("EnergyMgmtPerformanceRemark",
                        List.of(RemarkSection.ENERGY_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("FuelSummaryRemark",
                        List.of(RemarkSection.FUEL_SUMMARY.getValue(), RemarkSection.FUEL_SUMMARY_INFO.getValue(), RemarkSection.OTHER_ENERGY_MGMT_INFO.getValue())));
                add(new Pair<>("ElectricSummaryRemark",
                        List.of(RemarkSection.ELECTRICITY_SUMMARY_INFO.getValue())));
                add(new Pair<>("Esm04Remark",
                        List.of(RemarkSection.ESM04.getValue())));
                add(new Pair<>("WaterMgmtPlanRemark",
                        List.of(RemarkSection.WATER_MGMT_PLAN.getValue())));
                add(new Pair<>("WaterTargetRemark",
                        List.of(RemarkSection.WATER_TARGET.getValue())));
                add(new Pair<>("WaterMgmtPerformanceRemark",
                        List.of(RemarkSection.WATER_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("WaterSummaryRemark",
                        List.of(RemarkSection.WATER_SUMMARY_INFO.getValue())));
                add(new Pair<>("WaterDischargeRemark",
                        List.of(RemarkSection.WATER_DISCHARGE.getValue())));
                add(new Pair<>("WaterConsumptionRemark",
                        List.of(RemarkSection.WATER_CONSUMPTION.getValue())));
                add(new Pair<>("WaterRecycledRemark",
                        List.of(RemarkSection.RECYCLED_WATER.getValue())));
                add(new Pair<>("Esm05Remark",
                        List.of(RemarkSection.ESM05.getValue())));
                add(new Pair<>("WasteMgmtPlanRemark",
                        List.of(RemarkSection.WASTE_MGMT_PLAN.getValue())));
                add(new Pair<>("WasteMgmtPerformanceRemark",
                        List.of(RemarkSection.WASTE_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("WasteTargetRemark",
                        List.of(RemarkSection.WASTE_TARGET.getValue())));
                add(new Pair<>("WasteGenerationRemark",
                        List.of(RemarkSection.WASTE_GENERATION.getValue())));
                add(new Pair<>("ReusedRecycledWasteRemark",
                        List.of(RemarkSection.REUSED_RECYCLED_WASTE.getValue())));
                add(new Pair<>("Esm06Remark",
                        List.of(RemarkSection.ESM06.getValue())));
                add(new Pair<>("GhGasManagementPlanRemark",
                        List.of(RemarkSection.GH_GAS_MGMT_PLAN.getValue())));
                add(new Pair<>("GhGasComplianceStandardsRemark",
                        List.of(RemarkSection.GH_GAS_COMPLIANCE_STANDARDS.getValue())));
                add(new Pair<>("GhGasTargetRemark",
                        List.of(RemarkSection.GH_GAS_TARGET.getValue())));
                add(new Pair<>("GhGasTargetNetZeroRemark",
                        List.of(RemarkSection.GH_GAS_NET_ZERO.getValue())));
                add(new Pair<>("GhGasTargetCarbonRemark",
                        List.of(RemarkSection.GH_GAS_CARBON.getValue())));
                add(new Pair<>("GhGasTargetOtherRemark",
                        List.of(RemarkSection.GH_GAS_OTHER.getValue())));
                add(new Pair<>("GhGasMgmtPerformanceRemark",
                        List.of(RemarkSection.GH_GAS_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("GhGasMgmtRemark",
                        List.of(RemarkSection.GREEN_HOUSE_GAS_SUMMARY_TOTAL.getValue(),
                                RemarkSection.GH_GAS_SCOPE1.getValue(),
                                RemarkSection.GH_GAS_SCOPE2.getValue(),
                                RemarkSection.GH_GAS_SCOPE3.getValue())));
                add(new Pair<>("GhGasVerifyDeptRemark",
                        List.of(RemarkSection.VERIFY_DEPT_OF_GH_GAS.getValue())));
                add(new Pair<>("Esm07Remark",
                        List.of(RemarkSection.ESM07.getValue())));
                add(new Pair<>("OtherEnvMgmtPlanRemark",
                        List.of(RemarkSection.OTHER_ENVIRONMENTAL_MGMT_PLAN.getValue())));
                add(new Pair<>("Esm08Remark",
                        List.of(RemarkSection.ESM08.getValue())));
                add(new Pair<>("NumberLegalEnvironmentalIncidentRemark",
                        List.of(RemarkSection.NUMBER_OF_LEGAL_ENVIRONMENTAL_INCIDENT.getValue())));
                add(new Pair<>("InformationOfLegalEnvironmentalIncidentRemark",
                        List.of(RemarkSection.INCIDENT_ENVIRONMENTAL_INFO.getValue())));
            }};

    public static List<String> NUMBER_OF_LEGAL_ENVIRONMENTAL_INCIDENT = Arrays.asList(
            "noOfLegalIncident"
    );

    public static List<String> DISABLED_EMPLOYEE_LIST = Arrays.asList(
            "totalDisabledEmployment",
            "disabledEmployee",
            "maleDisabledEmployees",
            "femaleDisabledEmployees",
            "disabledNotEmployee",
            "contributionsToDisabilityFund"
    );

    public static final List<String> MALE_POSITION_NAME_LIST = List.of("maleEmployee", "maleExecutive", "maleTopExecutive");
    public static final List<String> FEMALE_POSITION_NAME_LIST = List.of("femaleEmployee", "femaleExecutive", "femaleTopExecutive");
    public static final List<String> EMPLOYEE_REMUNERATION_NAME_LIST = List.of("numberOfPvd", "PercentnumberOfPVDtoTotalEmployee", "pvdAmount");
    public static final List<String> EMPLOYEE_INFORMATION_NAME_LIST = Stream.of(MALE_POSITION_NAME_LIST, FEMALE_POSITION_NAME_LIST, EMPLOYEE_REMUNERATION_NAME_LIST).flatMap(Collection::stream).collect(Collectors.toList());

    public static String ATTACH_PATTERN_FILE = "FILE";
    public static String ATTACH_PATTERN_URL = "URL";

    public static final String FILE_URL = "/file/{reportDetailId}/download?name={generateName}";

    public static String LEGAL_ADVISORS = "legalAdvisors";
    public static String OTHER_CONTACTS = "otherContacts";
    public static String MONETARY_UNIT_THOUSAND = "thousand";
    public static String MONETARY_UNIT_MILLION = "million";

    //    C =  งบการเงินรวม  (Consolidate)
//    U = งบการเงินเฉพาะกิจการ (Separate หรือ Unconsolidated)
//    E = งบการเงินตามวิธีส่วนได้เสีย (Equity)

    public static Map<String, String> STATEMENT_TYPE_EN = Map.of(
            Constants.STATEMENT_TYPE_C, "Consolidate",
            Constants.STATEMENT_TYPE_C_RESTATE, "Consolidate Restate",
            Constants.STATEMENT_TYPE_U, "Separate",
            Constants.STATEMENT_TYPE_U_RESTATE, "Separate Restate",
            Constants.STATEMENT_TYPE_E, "Equity",
            Constants.STATEMENT_TYPE_E_RESTATE, "Equity Restate"
    );

    public static Map<String, String> STATEMENT_TYPE_TH = Map.of(
            Constants.STATEMENT_TYPE_C, "งบรวม",
            Constants.STATEMENT_TYPE_C_RESTATE, "งบรวม\n(ปรับปรุงใหม่/\nจัดประเภทใหม่)",
            Constants.STATEMENT_TYPE_U, "งบเฉพาะกิจการ",
            Constants.STATEMENT_TYPE_U_RESTATE, "งบเฉพาะกิจการ\n(ปรับปรุงใหม่/\nจัดประเภทใหม่)",
            Constants.STATEMENT_TYPE_E, "งบการเงินตาม\nวิธีส่วนได้เสีย",
            Constants.STATEMENT_TYPE_E_RESTATE, "งบการเงินตาม\nวิธีส่วนได้เสีย\n(ปรับปรุงใหม่/\nจัดประเภทใหม่)"
    );

    public static Map<String, String> STATEMENT_STATUS_EN = Map.of(
            Constants.STATEMENT_STATUS_REVIEWED, "REVIEWED",
            Constants.STATEMENT_STATUS_AUDITED, "AUDITED"
    );

    public static Map<String, String> STATEMENT_STATUS_TH = Map.of(
            Constants.STATEMENT_STATUS_REVIEWED, "ตรวจสอบ",
            Constants.STATEMENT_STATUS_AUDITED, "สอบทาน"
    );


    public static final String STATEMENT_TYPE_C = "C";
    public static final String STATEMENT_TYPE_C_RESTATE = "C_RESTATE";
    public static final String STATEMENT_TYPE_U = "U";
    public static final String STATEMENT_TYPE_U_RESTATE = "U_RESTATE";
    public static final String STATEMENT_TYPE_E = "E";
    public static final String STATEMENT_TYPE_E_RESTATE = "E_RESTATE";

    public static final String STATEMENT_STATUS_REVIEWED = "REVIEWED";
    public static final String STATEMENT_STATUS_AUDITED = "AUDITED";
    public static List<IntroductionSection> INTRODUCTION_SECTION_FINANCIAL_STATEMENTS = Arrays.asList(
            IntroductionSection.FSFR01,
            IntroductionSection.FSFR02
    );

    public static List<RemarkSection> REMARK_SECTION_FINANCIAL_STATEMENTS = Arrays.asList(
            RemarkSection.FSFR01,
            RemarkSection.FSFR02
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_FINANCIAL_STATEMENTS_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("Fsfr01Introduction",
                        List.of(IntroductionSection.FSFR01.getValue())));
                add(new Pair<>("Fsfr02Introduction",
                        List.of(IntroductionSection.FSFR02.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_FINANCIAL_STATEMENTS_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("Fsfr01Remark",
                        List.of(RemarkSection.FSFR01.getValue())));
                add(new Pair<>("Fsfr02Remark",
                        List.of(RemarkSection.FSFR02.getValue())));
            }};


    public static List<RemarkSection> REMARK_SECTION_GENERAL_INFORMATION = Arrays.asList(
            RemarkSection.GENERAL_INFORMATION,
            RemarkSection.GI01
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_GENERAL_INFORMATION = Arrays.asList(
            IntroductionSection.GENERAL_INFORMATION,
            IntroductionSection.GI01
    );

    public static final List<Pair<String, List<String>>> REMARK_GENERAL_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("generalInformationRemark",
                        List.of(RemarkSection.GENERAL_INFORMATION.getValue())));
                add(new Pair<>("gi01Remark",
                        List.of(RemarkSection.GI01.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_GENERAL_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("generalInformationIntroduction",
                        List.of(IntroductionSection.GENERAL_INFORMATION.getValue())));
                add(new Pair<>("gi01Introduction",
                        List.of(IntroductionSection.GI01.getValue())));
            }};

    public static List<RemarkSection> REMARK_SECTION_FOREIGN_STOCK_EXCHANGES = Arrays.asList(
            RemarkSection.SECONDARY_MARKET
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_FOREIGN_STOCK_EXCHANGES = Arrays.asList(
            IntroductionSection.SECONDARY_MARKET
    );

    public static final List<Pair<String, List<String>>> REMARK_FOREIGN_STOCK_EXCHANGES_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SecondaryMarketRemark",
                        List.of(RemarkSection.SECONDARY_MARKET.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_FOREIGN_STOCK_EXCHANGES_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SecondaryMarketIntroduction",
                        List.of(IntroductionSection.SECONDARY_MARKET.getValue())));
            }};

    public static List<RemarkSection> REMARK_SECTION_FINANCIAL_INSTITUTION_WITH_REGULAR_CONTACT = Arrays.asList(
            RemarkSection.FINANCIAL_INSTITUTION_REGULAR_CONTACT
    );
    public static List<IntroductionSection> INTRODUCTION_SECTION_FINANCIAL_INSTITUTION_WITH_REGULAR_CONTACT = Arrays.asList(
            IntroductionSection.FINANCIAL_INSTITUTION_REGULAR_CONTACT
    );

    public static final List<Pair<String, List<String>>> REMARK_FINANCIAL_INSTITUTION_WITH_REGULAR_CONTACT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("FinancialInstitutionWithRegularContactRemark",
                        List.of(RemarkSection.FINANCIAL_INSTITUTION_REGULAR_CONTACT.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_FINANCIAL_INSTITUTION_WITH_REGULAR_CONTACT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("FinancialInstitutionWithRegularContactIntroduction",
                        List.of(IntroductionSection.FINANCIAL_INSTITUTION_REGULAR_CONTACT.getValue())));
            }};

    public static final String THAI = "TH";


    public static final String OTHER_SUB_COMMITTEE = "OTHER_SUB_COMMITTEE";

    public static Set<SupervisorProfileType> profileTypesOsi01 = EnumSet.of(
            SupervisorProfileType.ACCOUNTANT,
            SupervisorProfileType.SECRETARY,
            SupervisorProfileType.INTERNAL_AUDIT,
            SupervisorProfileType.COMPLIANCE
    );

    public static Set<SupervisorProfileType> profileTypesOsi02 = EnumSet.of(
            SupervisorProfileType.INVESTOR_RELATION
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_RISK_MGMT_POLICY_AND_PLAN_MGMT = Arrays.asList(
            IntroductionSection.RISK_MGMT_POLICY_AND_PLAN,
            IntroductionSection.RMPAP01
    );

    public static List<RemarkSection> REMARK_SECTION_RISK_MGMT_POLICY_AND_PLAN_MGMT = Arrays.asList(
            RemarkSection.RISK_MGMT_POLICY_AND_PLAN,
            RemarkSection.RMPAP01
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_RISK_MGMT_POLICY_AND_PLAN_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("RiskMgmtPolicyAndPlanIntroduction",
                        List.of(IntroductionSection.RISK_MGMT_POLICY_AND_PLAN.getValue())));
                add(new Pair<>("Rmpap01Introduction",
                        List.of(IntroductionSection.RMPAP01.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_RISK_MGMT_POLICY_AND_PLAN_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("RiskMgmtPolicyAndPlanRemark",
                        List.of(RemarkSection.RISK_MGMT_POLICY_AND_PLAN.getValue())));
                add(new Pair<>("Rmpap01Remark",
                        List.of(RemarkSection.RMPAP01.getValue())));
            }};


    public static List<RemarkSection> REMARK_SECTION_RISK_FACTORS = Arrays.asList(
            RemarkSection.RISK_FACTORS,
            RemarkSection.RF01,
            RemarkSection.RF02,
            RemarkSection.RF03
    );

    public static List<IntroductionSection> INTRODUCTION_SECTION_RISK_FACTORS = Arrays.asList(
            IntroductionSection.RISK_FACTORS,
            IntroductionSection.RF01,
            IntroductionSection.RF02,
            IntroductionSection.RF03
    );

    public static final List<Pair<String, List<String>>> REMARK_RISK_FACTORS_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("riskFactorRemark",
                        List.of(RemarkSection.RISK_FACTORS.getValue())));
                add(new Pair<>("rf01Remark",
                        List.of(RemarkSection.RF01.getValue())));
                add(new Pair<>("rf02Remark",
                        List.of(RemarkSection.RF02.getValue())));
                add(new Pair<>("rf03Remark",
                        List.of(RemarkSection.RF03.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_RISK_FACTORS_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("riskFactorIntroduction",
                        List.of(IntroductionSection.RISK_FACTORS.getValue())));
                add(new Pair<>("rf01Introduction",
                        List.of(IntroductionSection.RF01.getValue())));
                add(new Pair<>("rf02Introduction",
                        List.of(IntroductionSection.RF02.getValue())));
                add(new Pair<>("rf03Introduction",
                        List.of(IntroductionSection.RF03.getValue())));
            }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_OTHER_MATERIAL_FACTS = Arrays.asList(
            IntroductionSection.OTHER_MATERIAL_FACTS
    );


    public static List<RemarkSection> REMARK_SECTION_OTHER_MATERIAL_FACTS = Arrays.asList(
            RemarkSection.OTHER_MATERIAL_FACTS,
            RemarkSection.OMF01,
            RemarkSection.OMF02
    );

    public static final List<Pair<String, List<String>>> REMARK_OTHER_MATERIAL_FACTS_SECTION = new ArrayList<>() {{
        add(new Pair<>("otherMaterialFactsRemark", List.of(RemarkSection.OTHER_MATERIAL_FACTS.getValue())));
        add(new Pair<>("omf01Remark", List.of(RemarkSection.OMF01.getValue())));
        add(new Pair<>("omf02Remark", List.of(RemarkSection.OMF02.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_OTHER_MATERIAL_FACTS_SECTION = new ArrayList<>() {{
        add(new Pair<>("otherMaterialFactsIntroduction", List.of(IntroductionSection.OTHER_MATERIAL_FACTS.getValue())));
    }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_SOCIAL_SUSTAINABILITY_MGMT = Arrays.asList(
            IntroductionSection.SOCIAL_SUSTAINABILITY_MGMT,
            IntroductionSection.SSM01,
            IntroductionSection.HUMAN_RIGHTS_COMPLIANCE,
            IntroductionSection.REVIEWED_HUMAN_RIGHTS_ISSUE,
            IntroductionSection.HRDD,
            IntroductionSection.SSM02,
            IntroductionSection.SSM03,
            IntroductionSection.EMPLOYEE_MGMT_PLAN,
            IntroductionSection.EMPLOYEE_MGMT_TARGET,
            IntroductionSection.EMPLOYEE_MGMT_PERFORMANCE,
            IntroductionSection.EMPLOYMENT,
            IntroductionSection.EMPLOYEE_INFO,
            IntroductionSection.DISABLED_EMPLOYEE,
            IntroductionSection.EMPLOYEE_REMUNERATION,
            IntroductionSection.EMPLOYEE_TRAINING,
            IntroductionSection.EMPLOYEE_SAFETY,
            IntroductionSection.EMPLOYEE_RELATIONSHIP,
            IntroductionSection.SSM04,
            IntroductionSection.CUSTOMER_MGMT_PLAN,
            IntroductionSection.CUSTOMER_MGMT_TARGET,
            IntroductionSection.CUSTOMER_MGMT_PERFORMANCE,
            IntroductionSection.CUSTOMER_SATISFACTION,
            IntroductionSection.SSM05,
            IntroductionSection.SOCIAL_MGMT_PLAN,
            IntroductionSection.SOCIAL_MGMT_TARGET,
            IntroductionSection.SOCIAL_MGMT_PERFORMANCE,
            IntroductionSection.SSM06,
            IntroductionSection.OTHER_SOCIAL_MGMT_PLAN,
            IntroductionSection.SSM07,
            IntroductionSection.NUMBER_OF_LEGAL_SOCIAL_INCIDENT,
            IntroductionSection.INCIDENT_SOCIAL_INFO
    );

    public static List<RemarkSection> REMARK_SECTION_SOCIAL_SUSTAINABILITY_MGMT = Arrays.asList(
            RemarkSection.SOCIAL_SUSTAINABILITY_MGMT,
            RemarkSection.SSM01,
            RemarkSection.HUMAN_RIGHTS_COMPLIANCE,
            RemarkSection.REVIEWED_HUMAN_RIGHTS_ISSUE,
            RemarkSection.HRDD,
            RemarkSection.SSM02,
            RemarkSection.SSM03,
            RemarkSection.EMPLOYEE_MGMT_PLAN,
            RemarkSection.EMPLOYEE_MGMT_TARGET,
            RemarkSection.EMPLOYEE_MGMT_PERFORMANCE,
            RemarkSection.EMPLOYEE_RELATIONSHIP,
            RemarkSection.EMPLOYEE_ENGAGEMENT_ASSESSMENT_RESULTS,
            RemarkSection.EMPLOYMENT,
            RemarkSection.SSM04,
            RemarkSection.CUSTOMER_MGMT_PLAN,
            RemarkSection.CUSTOMER_MGMT_TARGET,
            RemarkSection.CUSTOMER_MGMT_PERFORMANCE,
            RemarkSection.CUSTOMER_SATISFACTION,
            RemarkSection.SSM05,
            RemarkSection.SOCIAL_MGMT_PLAN,
            RemarkSection.SOCIAL_MGMT_TARGET,
            RemarkSection.SOCIAL_MGMT_PERFORMANCE,
            RemarkSection.SSM06,
            RemarkSection.OTHER_SOCIAL_MGMT_PLAN,
            RemarkSection.SSM07,
            RemarkSection.INCIDENT_SOCIAL_INFO
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_SOCIAL_SUSTAINABILITY_MGMT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SocialSustainabilityMgmtIntroduction",
                        List.of(IntroductionSection.SOCIAL_SUSTAINABILITY_MGMT.getValue())));
                add(new Pair<>("Ssm01Introduction",
                        List.of(IntroductionSection.SSM01.getValue())));
                add(new Pair<>("HumanRightsComplianceIntroduction",
                        List.of(IntroductionSection.HUMAN_RIGHTS_COMPLIANCE.getValue())));
                add(new Pair<>("ReviewedHumanRightsIssueIntroduction",
                        List.of(IntroductionSection.REVIEWED_HUMAN_RIGHTS_ISSUE.getValue())));
                add(new Pair<>("HrddIntroduction",
                        List.of(IntroductionSection.HRDD.getValue())));
                add(new Pair<>("Ssm02Introduction",
                        List.of(IntroductionSection.SSM02.getValue())));
                add(new Pair<>("Ssm03Introduction",
                        List.of(IntroductionSection.SSM03.getValue())));
                add(new Pair<>("EmployeeMgmtPlanIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_MGMT_PLAN.getValue())));
                add(new Pair<>("EmployeeMgmtTargetIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_MGMT_TARGET.getValue())));
                add(new Pair<>("EmployeeMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("EmploymentIntroduction",
                        List.of(IntroductionSection.EMPLOYMENT.getValue())));
                add(new Pair<>("EmployeeInfoIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_INFO.getValue())));
                add(new Pair<>("DisabledEmployeeInfoIntroduction",
                        List.of(IntroductionSection.DISABLED_EMPLOYEE.getValue())));
                add(new Pair<>("EmployeeRemunerationIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_REMUNERATION.getValue())));
                add(new Pair<>("EmployeeTrainingIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_TRAINING.getValue())));
                add(new Pair<>("EmployeeSafetyIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_SAFETY.getValue())));
                add(new Pair<>("EmployeeRelationshipIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_RELATIONSHIP.getValue())));
                add(new Pair<>("Ssm04Introduction",
                        List.of(IntroductionSection.SSM04.getValue())));
                add(new Pair<>("CustomerMgmtPlanIntroduction",
                        List.of(IntroductionSection.CUSTOMER_MGMT_PLAN.getValue())));
                add(new Pair<>("CustomerMgmtTargetIntroduction",
                        List.of(IntroductionSection.CUSTOMER_MGMT_TARGET.getValue())));
                add(new Pair<>("CustomerMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.CUSTOMER_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("CustomerSatisfactionIntroduction",
                        List.of(IntroductionSection.CUSTOMER_SATISFACTION.getValue())));
                add(new Pair<>("Ssm05Introduction",
                        List.of(IntroductionSection.SSM05.getValue())));
                add(new Pair<>("SocialMgmtPlanIntroduction",
                        List.of(IntroductionSection.SOCIAL_MGMT_PLAN.getValue())));
                add(new Pair<>("SocialMgmtTargetIntroduction",
                        List.of(IntroductionSection.SOCIAL_MGMT_TARGET.getValue())));
                add(new Pair<>("SocialMgmtPerformanceIntroduction",
                        List.of(IntroductionSection.SOCIAL_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("Ssm06Introduction",
                        List.of(IntroductionSection.SSM06.getValue())));
                add(new Pair<>("OtherSocialMgmtPlanIntroduction",
                        List.of(IntroductionSection.OTHER_SOCIAL_MGMT_PLAN.getValue())));
                add(new Pair<>("Ssm07Introduction",
                        List.of(IntroductionSection.SSM07.getValue())));
                add(new Pair<>("NumberOfLegalSocialIncidentIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_LEGAL_SOCIAL_INCIDENT.getValue())));
                add(new Pair<>("IncidentSocialInfoIntroduction",
                        List.of(IntroductionSection.INCIDENT_SOCIAL_INFO.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_SOCIAL_SUSTAINABILITY_MGMT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SocialSustainabilityMgmtRemark",
                        List.of(RemarkSection.SOCIAL_SUSTAINABILITY_MGMT.getValue())));
                add(new Pair<>("Ssm01Remark",
                        List.of(RemarkSection.SSM01.getValue())));
                add(new Pair<>("HumanRightsComplianceRemark",
                        List.of(RemarkSection.HUMAN_RIGHTS_COMPLIANCE.getValue())));
                add(new Pair<>("ReviewedHumanRightsIssueRemark",
                        List.of(RemarkSection.REVIEWED_HUMAN_RIGHTS_ISSUE.getValue())));
                add(new Pair<>("HrddRemark",
                        List.of(RemarkSection.HRDD.getValue())));
                add(new Pair<>("Ssm02Remark",
                        List.of(RemarkSection.SSM02.getValue())));
                add(new Pair<>("Ssm03Remark",
                        List.of(RemarkSection.SSM03.getValue())));
                add(new Pair<>("EmployeeMgmtPlanRemark",
                        List.of(RemarkSection.EMPLOYEE_MGMT_PLAN.getValue())));
                add(new Pair<>("EmployeeMgmtTargetRemark",
                        List.of(RemarkSection.EMPLOYEE_MGMT_TARGET.getValue())));
                add(new Pair<>("EmployeeMgmtPerformanceRemark",
                        List.of(RemarkSection.EMPLOYEE_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("EmploymentRemark",
                        List.of(RemarkSection.EMPLOYMENT.getValue())));
                add(new Pair<>("EmployeeInfoRemark",
                        List.of(RemarkSection.EMPLOYEE_INFO.getValue())));
                add(new Pair<>("DisabledEmployeeInfoRemark",
                        List.of(RemarkSection.DISABLED_EMPLOYEE.getValue())));
                add(new Pair<>("EmployeeRemunerationInfoRemark",
                        List.of(RemarkSection.EMPLOYEE_REMUNERATION.getValue())));
                add(new Pair<>("EmployeeTrainingInfoRemark",
                        List.of(RemarkSection.EMPLOYEE_TRAINING.getValue())));
                add(new Pair<>("EmployeeSafetyInfoRemark",
                        List.of(RemarkSection.EMPLOYEE_SAFETY.getValue())));
                add(new Pair<>("EmployeeRelationshipInfoRemark",
                        List.of(RemarkSection.EMPLOYEE_RELATIONSHIP.getValue(), RemarkSection.EMPLOYEE_RELATIONSHIP_INFO.getValue())));
                add(new Pair<>("EvaluationResultOfEmployeeEngagementRemark",
                        List.of(RemarkSection.EMPLOYEE_ENGAGEMENT_ASSESSMENT_RESULTS.getValue())));
                add(new Pair<>("Ssm04Remark",
                        List.of(RemarkSection.SSM04.getValue())));
                add(new Pair<>("CustomerMgmtPlanRemark",
                        List.of(RemarkSection.CUSTOMER_MGMT_PLAN.getValue())));
                add(new Pair<>("CustomerMgmtTargetRemark",
                        List.of(RemarkSection.CUSTOMER_MGMT_TARGET.getValue())));
                add(new Pair<>("CustomerMgmtPerformanceRemark",
                        List.of(RemarkSection.CUSTOMER_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("CustomerSatisfactionRemark",
                        List.of(RemarkSection.CUSTOMER_SATISFACTION.getValue())));
                add(new Pair<>("Ssm05Remark",
                        List.of(RemarkSection.SSM05.getValue())));
                add(new Pair<>("SocialMgmtPlanRemark",
                        List.of(RemarkSection.SOCIAL_MGMT_PLAN.getValue())));
                add(new Pair<>("SocialMgmtTargetRemark",
                        List.of(RemarkSection.SOCIAL_MGMT_TARGET.getValue())));
                add(new Pair<>("SocialMgmtPerformanceRemark",
                        List.of(RemarkSection.SOCIAL_MGMT_PERFORMANCE.getValue())));
                add(new Pair<>("Ssm06Remark",
                        List.of(RemarkSection.SSM06.getValue())));
                add(new Pair<>("OtherSocialMgmtPlanRemark",
                        List.of(RemarkSection.OTHER_SOCIAL_MGMT_PLAN.getValue())));
                add(new Pair<>("Ssm07Remark",
                        List.of(RemarkSection.SSM07.getValue())));
                add(new Pair<>("NumberOfLegalSocialIncidentRemark",
                        List.of(RemarkSection.NO_OF_LEGAL_SOCIAL_INCIDENT.getValue())));
                add(new Pair<>("IncidentSocialInfoRemark",
                        List.of(RemarkSection.INCIDENT_SOCIAL_INFO.getValue())));

            }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_LEGAL_DISPUTES = Arrays.asList(
            IntroductionSection.LEGAL_DISPUTES,
            IntroductionSection.LEGAL_DISPUTES_INFO
    );


    public static List<RemarkSection> REMARK_SECTION_LEGAL_DISPUTES = Arrays.asList(
            RemarkSection.LEGAL_DISPUTES,
            RemarkSection.LEGAL_DISPUTES_INFO
    );

    public static final List<Pair<String, List<String>>> REMARK_LEGAL_DISPUTES_SECTION = new ArrayList<>() {{
        add(new Pair<>("legalDisputesRemark", List.of(RemarkSection.LEGAL_DISPUTES.getValue())));
        add(new Pair<>("legalDisputesInfoRemark", List.of(RemarkSection.LEGAL_DISPUTES_INFO.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_LEGAL_DISPUTES_SECTION = new ArrayList<>() {{
        add(new Pair<>("legalDisputesIntroduction", List.of(IntroductionSection.LEGAL_DISPUTES.getValue())));
        add(new Pair<>("legalDisputesInfoIntroduction", List.of(IntroductionSection.LEGAL_DISPUTES_INFO.getValue())));
    }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_OVERVIEW_POLICIES = Arrays.asList(
            IntroductionSection.OVERVIEW_POLICIES,
            IntroductionSection.OVERVIEW_POLICIES_SUB_SECTION,
            IntroductionSection.OP01,
            IntroductionSection.OP02
    );


    public static List<RemarkSection> REMARK_SECTION_OVERVIEW_POLICIES = Arrays.asList(
            RemarkSection.OVERVIEW_POLICIES,
            RemarkSection.OVERVIEW_POLICIES_SUB_SECTION,
            RemarkSection.OP01,
            RemarkSection.OP02
    );

    public static final List<Pair<String, List<String>>> REMARK_OVERVIEW_POLICIES_SECTION = new ArrayList<>() {{
        add(new Pair<>("overviewPoliciesRemark", List.of(RemarkSection.OVERVIEW_POLICIES.getValue())));
        add(new Pair<>("overviewPoliciesSubSectionRemark", List.of(RemarkSection.OVERVIEW_POLICIES_SUB_SECTION.getValue())));
        add(new Pair<>("op01Remark", List.of(RemarkSection.OP01.getValue())));
        add(new Pair<>("op02Remark", List.of(RemarkSection.OP02.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_OVERVIEW_POLICIES_SECTION = new ArrayList<>() {{
        add(new Pair<>("overviewPoliciesIntroduction", List.of(IntroductionSection.OVERVIEW_POLICIES.getValue())));
        add(new Pair<>("overviewPoliciesSubSectionIntroduction", List.of(IntroductionSection.OVERVIEW_POLICIES_SUB_SECTION.getValue())));
        add(new Pair<>("op01Introduction", List.of(IntroductionSection.OP01.getValue())));
        add(new Pair<>("op02Introduction", List.of(IntroductionSection.OP02.getValue())));
    }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_CODE_OF_CONDUCT = Arrays.asList(
            IntroductionSection.CODE_OF_CONDUCT,
            IntroductionSection.COC01,
            IntroductionSection.COC_ISSUE,
            IntroductionSection.ENCOURAGE_COMPLIANCE,
            IntroductionSection.AGAINST_FRAUD_AND_CORRUPTION
    );

    public static List<RemarkSection> REMARK_SECTION_CODE_OF_CONDUCT = Arrays.asList(
            RemarkSection.CODE_OF_CONDUCT,
            RemarkSection.COC01,
            RemarkSection.COC_ISSUE,
            RemarkSection.ENCOURAGE_COMPLIANCE,
            RemarkSection.AGAINST_FRAUD_AND_CORRUPTION
    );

    public static final List<Pair<String, List<String>>> REMARK_CODE_OF_CONDUCT_SECTION = new ArrayList<>() {{
        add(new Pair<>("codeOfConductRemark", List.of(RemarkSection.CODE_OF_CONDUCT.getValue())));
        add(new Pair<>("coc01Remark", List.of(RemarkSection.COC01.getValue())));
        add(new Pair<>("cocIssueRemark", List.of(RemarkSection.COC_ISSUE.getValue())));
        add(new Pair<>("encourageComplianceRemark", List.of(RemarkSection.ENCOURAGE_COMPLIANCE.getValue())));
        add(new Pair<>("againstFraudAndCorruptionRemark", List.of(RemarkSection.AGAINST_FRAUD_AND_CORRUPTION.getValue())));
    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_CODE_OF_CONDUCT_SECTION = new ArrayList<>() {{
        add(new Pair<>("codeOfConductIntroduction", List.of(IntroductionSection.CODE_OF_CONDUCT.getValue())));
        add(new Pair<>("coc01Introduction", List.of(IntroductionSection.COC01.getValue())));
        add(new Pair<>("cocIssueIntroduction", List.of(IntroductionSection.COC_ISSUE.getValue())));
        add(new Pair<>("encourageComplianceIntroduction", List.of(IntroductionSection.ENCOURAGE_COMPLIANCE.getValue())));
        add(new Pair<>("againstFraudAndCorruptionIntroduction", List.of(IntroductionSection.AGAINST_FRAUD_AND_CORRUPTION.getValue())));
    }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_CHANGES_AND_DEV_POLICY = Arrays.asList(
            IntroductionSection.CHANGES_AND_DEV_POLICY,
            IntroductionSection.CADP01,
            IntroductionSection.CADP02,
            IntroductionSection.CADP03
    );


    public static List<RemarkSection> REMARK_SECTION_CHANGES_AND_DEV_POLICY = Arrays.asList(
            RemarkSection.CHANGES_AND_DEV_POLICY,
            RemarkSection.CADP01,
            RemarkSection.CADP02,
            RemarkSection.CADP03
    );

    public static final List<Pair<String, List<String>>> REMARK_CHANGES_AND_DEV_POLICY_SECTION = new ArrayList<>() {{
        add(new Pair<>("changesAndDevPolicyRemark", List.of(RemarkSection.CHANGES_AND_DEV_POLICY.getValue())));
        add(new Pair<>("cadp01Remark", List.of(RemarkSection.CADP01.getValue())));
        add(new Pair<>("cadp02Remark", List.of(RemarkSection.CADP02.getValue())));
        add(new Pair<>("cadp03Remark", List.of(RemarkSection.CADP03.getValue())));

    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_CHANGES_AND_DEV_POLICY_SECTION = new ArrayList<>() {{
        add(new Pair<>("changesAndDevPolicyIntroduction", List.of(IntroductionSection.CHANGES_AND_DEV_POLICY.getValue())));
        add(new Pair<>("cadp01Introduction", List.of(IntroductionSection.CADP01.getValue())));
        add(new Pair<>("cadp02Introduction", List.of(IntroductionSection.CADP02.getValue())));
        add(new Pair<>("cadp03Introduction", List.of(IntroductionSection.CADP03.getValue())));
    }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_RELATED_PARTY_TRANSACTIONS = Arrays.asList(
            IntroductionSection.RELATED_PARTY_TRANSACTIONS,
            IntroductionSection.RPT02,
            IntroductionSection.RPT02,
            IntroductionSection.RPT03,
            IntroductionSection.RPT04
    );


    public static List<RemarkSection> REMARK_SECTION_RELATED_PARTY_TRANSACTIONS = Arrays.asList(
            RemarkSection.RELATED_PARTY_TRANSACTIONS,
            RemarkSection.RPT02,
            RemarkSection.RPT03,
            RemarkSection.RPT04
    );

    public static final List<Pair<String, List<String>>> REMARK_RELATED_PARTY_TRANSACTIONS_SECTION = new ArrayList<>() {{
        add(new Pair<>("relatedPartyTransactionsRemark", List.of(RemarkSection.RELATED_PARTY_TRANSACTIONS.getValue())));
        add(new Pair<>("rpt02Remark", List.of(RemarkSection.RPT02.getValue())));
        add(new Pair<>("rpt03Remark", List.of(RemarkSection.RPT03.getValue())));
        add(new Pair<>("rpt04Remark", List.of(RemarkSection.RPT04.getValue())));

    }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_RELATED_PARTY_TRANSACTIONS_SECTION = new ArrayList<>() {{
        add(new Pair<>("relatedPartyTransactionsIntroduction", List.of(IntroductionSection.RELATED_PARTY_TRANSACTIONS.getValue())));
        add(new Pair<>("rpt02Introduction", List.of(IntroductionSection.RPT02.getValue())));
        add(new Pair<>("rpt03Introduction", List.of(IntroductionSection.RPT03.getValue())));
        add(new Pair<>("rpt04Introduction", List.of(IntroductionSection.RPT04.getValue())));
    }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_INTERNAL_CONTROL = Arrays.asList(
            IntroductionSection.INTERNAL_CONTROL,
            IntroductionSection.BOD_OPINION_SUMMARY,
            IntroductionSection.IC01,
            IntroductionSection.IC02,
            IntroductionSection.IC03,
            IntroductionSection.IC04,
            IntroductionSection.IC05
    );

    public static List<RemarkSection> REMARK_SECTION_INTERNAL_CONTROL = Arrays.asList(
            RemarkSection.INTERNAL_CONTROL,
            RemarkSection.BOD_OPINION_SUMMARY,
            RemarkSection.IC01,
            RemarkSection.DEFICIENCIES_INFO,
            RemarkSection.IC02,
            RemarkSection.IC03,
            RemarkSection.IC04,
            RemarkSection.IC05
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_INTERNAL_CONTROL_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("InternalControlIntroduction",
                        List.of(IntroductionSection.INTERNAL_CONTROL.getValue())));
                add(new Pair<>("BodOpinionSummaryIntroduction",
                        List.of(IntroductionSection.BOD_OPINION_SUMMARY.getValue())));
                add(new Pair<>("Ic01Introduction",
                        List.of(IntroductionSection.IC01.getValue())));
                add(new Pair<>("Ic02Introduction",
                        List.of(IntroductionSection.IC02.getValue())));
                add(new Pair<>("Ic03Introduction",
                        List.of(IntroductionSection.IC03.getValue())));
                add(new Pair<>("Ic04Introduction",
                        List.of(IntroductionSection.IC04.getValue())));
                add(new Pair<>("Ic05Introduction",
                        List.of(IntroductionSection.IC05.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_INTERNAL_CONTROL_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("InternalControlRemark",
                        List.of(RemarkSection.INTERNAL_CONTROL.getValue())));
                add(new Pair<>("BodOpinionSummaryRemark",
                        List.of(RemarkSection.BOD_OPINION_SUMMARY.getValue())));
                add(new Pair<>("DeficienciesInfoRemark",
                        List.of(RemarkSection.DEFICIENCIES_INFO.getValue())));
                add(new Pair<>("Ic01Remark",
                        List.of(RemarkSection.IC01.getValue())));
                add(new Pair<>("Ic02Remark",
                        List.of(RemarkSection.IC02.getValue())));
                add(new Pair<>("Ic03Remark",
                        List.of(RemarkSection.IC03.getValue())));
                add(new Pair<>("Ic04Remark",
                        List.of(RemarkSection.IC04.getValue())));
                add(new Pair<>("Ic05Remark",
                        List.of(RemarkSection.IC05.getValue())));
            }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_STAKEHOLDERS_IMPACT_MGMT = Arrays.asList(
            IntroductionSection.STAKEHOLDERS_IMPACT_MGMT,
            IntroductionSection.SIM01,
            IntroductionSection.SIM02
    );

    public static List<RemarkSection> REMARK_SECTION_STAKEHOLDERS_IMPACT_MGMT = Arrays.asList(
            RemarkSection.STAKEHOLDERS_IMPACT_MGMT,
            RemarkSection.SIM01,
            RemarkSection.SIM02
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_STAKEHOLDERS_IMPACT_MGMT_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("StakeholdersImpactMgmtIntroduction",
                        List.of(IntroductionSection.STAKEHOLDERS_IMPACT_MGMT.getValue())));
                add(new Pair<>("Sim01Introduction",
                        List.of(IntroductionSection.SIM01.getValue())));
                add(new Pair<>("Sim02Introduction",
                        List.of(IntroductionSection.SIM02.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_STAKEHOLDERS_IMPACT_MGMT_SECTION =
            new ArrayList<>() {
                {
                    add(new Pair<>("StakeholdersImpactMgmtRemark",
                            List.of(RemarkSection.STAKEHOLDERS_IMPACT_MGMT.getValue())));
                    add(new Pair<>("Sim01Remark",
                            List.of(RemarkSection.SIM01.getValue())));
                    add(new Pair<>("Sim02Remark",
                            List.of(RemarkSection.SIM02.getValue())));
                }
            };

    public static List<IntroductionSection> INTRODUCTION_SECTION_OTHER_IMPORTANT_INFORMATION = Arrays.asList(
            IntroductionSection.OTHER_SIGNIFICANT_INFORMATION,
            IntroductionSection.OSI01,
            IntroductionSection.ACCOUNTANT,
            IntroductionSection.SECRETARY,
            IntroductionSection.INTERNAL_AUDIT,
            IntroductionSection.COMPLIANCE,
            IntroductionSection.OSI02,
            IntroductionSection.INVESTOR_RELATION_LIST,
            IntroductionSection.OSI03,
            IntroductionSection.COMPANY_AUDIT_INFO,
            IntroductionSection.SUBSIDIARY_COMPANY_AUDIT_INFO,
            IntroductionSection.OSI04,
            IntroductionSection.REPRESENTATIVES_IN_THAILAND_INFO
    );

    public static List<RemarkSection> REMARK_SECTION_OTHER_IMPORTANT_INFORMATION = Arrays.asList(
            RemarkSection.OTHER_SIGNIFICANT_INFORMATION,
            RemarkSection.OSI01,
            RemarkSection.ACCOUNTANT,
            RemarkSection.SECRETARY,
            RemarkSection.INTERNAL_AUDIT,
            RemarkSection.COMPLIANCE,
            RemarkSection.OSI02,
            RemarkSection.INVESTOR_RELATION_LIST,
            RemarkSection.OSI03,
            RemarkSection.COMPANY_AUDIT_INFO,
            RemarkSection.SUBSIDIARY_COMPANY_AUDIT_INFO,
            RemarkSection.OSI04,
            RemarkSection.REPRESENTATIVES_IN_THAILAND_INFO
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_OTHER_IMPORTANT_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("OtherSignificantInformationIntroduction",
                        List.of(IntroductionSection.OTHER_SIGNIFICANT_INFORMATION.getValue())));
                add(new Pair<>("Osi01Introduction",
                        List.of(IntroductionSection.OSI01.getValue())));
                add(new Pair<>("AccountantIntroduction",
                        List.of(IntroductionSection.ACCOUNTANT.getValue())));
                add(new Pair<>("SecretaryIntroduction",
                        List.of(IntroductionSection.SECRETARY.getValue())));
                add(new Pair<>("InternalAuditIntroduction",
                        List.of(IntroductionSection.INTERNAL_AUDIT.getValue())));
                add(new Pair<>("ComplianceIntroduction",
                        List.of(IntroductionSection.COMPLIANCE.getValue())));
                add(new Pair<>("Osi02Introduction",
                        List.of(IntroductionSection.OSI02.getValue())));
                add(new Pair<>("InvestorRelationListIntroduction",
                        List.of(IntroductionSection.INVESTOR_RELATION_LIST.getValue())));
                add(new Pair<>("Osi03Introduction",
                        List.of(IntroductionSection.OSI03.getValue())));
                add(new Pair<>("CompanyAuditInfoIntroduction",
                        List.of(IntroductionSection.COMPANY_AUDIT_INFO.getValue())));
                add(new Pair<>("SubsidiaryCompanyAuditInfoIntroduction",
                        List.of(IntroductionSection.SUBSIDIARY_COMPANY_AUDIT_INFO.getValue())));
                add(new Pair<>("Osi04Introduction",
                        List.of(IntroductionSection.OSI04.getValue())));
                add(new Pair<>("RepresentativesInThailandInfoIntroduction",
                        List.of(IntroductionSection.REPRESENTATIVES_IN_THAILAND_INFO.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_OTHER_IMPORTANT_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("OtherSignificantInformationRemark",
                        List.of(RemarkSection.OTHER_SIGNIFICANT_INFORMATION.getValue())));
                add(new Pair<>("Osi01Remark",
                        List.of(RemarkSection.OSI01.getValue())));
                add(new Pair<>("AccountantRemark",
                        List.of(RemarkSection.ACCOUNTANT.getValue())));
                add(new Pair<>("SecretaryRemark",
                        List.of(RemarkSection.SECRETARY.getValue())));
                add(new Pair<>("InternalAuditRemark",
                        List.of(RemarkSection.INTERNAL_AUDIT.getValue())));
                add(new Pair<>("ComplianceRemark",
                        List.of(RemarkSection.COMPLIANCE.getValue())));
                add(new Pair<>("Osi02Remark",
                        List.of(RemarkSection.OSI02.getValue())));
                add(new Pair<>("InvestorRelationListRemark",
                        List.of(RemarkSection.INVESTOR_RELATION_LIST.getValue())));
                add(new Pair<>("Osi03Remark",
                        List.of(RemarkSection.OSI03.getValue())));
                add(new Pair<>("CompanyAuditInfoRemark",
                        List.of(RemarkSection.COMPANY_AUDIT_INFO.getValue())));
                add(new Pair<>("SubsidiaryCompanyAuditInfoRemark",
                        List.of(RemarkSection.SUBSIDIARY_COMPANY_AUDIT_INFO.getValue())));
                add(new Pair<>("Osi04Remark",
                        List.of(RemarkSection.OSI04.getValue())));
                add(new Pair<>("RepresentativesInThailandInfoRemark",
                        List.of(RemarkSection.REPRESENTATIVES_IN_THAILAND_INFO.getValue())));
            }};

    public static final Map<String, Integer> SEQUENCE_OF_NODE_LEVEL_2 = Stream.of(new Object[][]{
            {STRUCTURE_AND_OPERATION, 1},
            {RISK_MANAGEMENT, 2},
            {ESG, 3},
            {"mdAndA", 4},
            {"generalInformationAndOtherMaterialFacts", 5},
            {CORPORATE_GOVERNANCE_POLICY, 6},
            {"cgsInfo", 7},
            {RESULT_OF_CORPORATE_GOVERNANCE, 8},
            {"internalControlAndRelatedPartyTransactions", 9}
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public static final List<Pair<String, List<String>>> INTRODUCTION_AUDIT_COMMITTEES_PERF_SUMMARY_SECTION = new ArrayList<>() {{
        add(new Pair<>("auditCommitteesPerfSummaryIntroduction", List.of(IntroductionSection.AUDIT_COMMITTEES_PERF_SUMMARY.getValue())));
        add(new Pair<>("aps01Introduction", List.of(IntroductionSection.APS01.getValue())));
        add(new Pair<>("aps02Introduction", List.of(IntroductionSection.APS02.getValue())));
    }};

    public static final List<IntroductionSection> INTRODUCTION_AUDIT_COMMITTEES_PERF_SUMMARY_SECTION_LIST = List.of(
            IntroductionSection.AUDIT_COMMITTEES_PERF_SUMMARY,
            IntroductionSection.APS01,
            IntroductionSection.APS02
    );

    public static List<RemarkSection> REMARK_SECTION_AUDIT_COMMITTEES_PERF_SUMMARY = Arrays.asList(
            RemarkSection.AUDIT_COMMITTEES_PERF_SUMMARY,
            RemarkSection.APS01,
            RemarkSection.APS02
    );

    public static final List<Pair<String, List<String>>> REMARK_AUDIT_COMMITTEES_PERF_SUMMARY_SECTION = new ArrayList<>() {{
        add(new Pair<>("auditCommitteesPerfSummaryRemark", List.of(RemarkSection.AUDIT_COMMITTEES_PERF_SUMMARY.getValue())));
        add(new Pair<>("aps01Remark", List.of(RemarkSection.APS01.getValue())));
        add(new Pair<>("aps02Remark", List.of(RemarkSection.APS02.getValue())));
    }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_BOARD_OF_DIRECTOR = Arrays.asList(
            IntroductionSection.BOARD_OF_DIRECTORS_INFORMATION,
            IntroductionSection.BODI01,
            IntroductionSection.BODI02,
            IntroductionSection.PRESENT_BOD,
            IntroductionSection.PAST_BOD,
            IntroductionSection.OTHERS_BOD_INFO,
            IntroductionSection.POWER_MEASURES_OF_BOARD_AND_MANAGEMENT,
            IntroductionSection.BODI03
    );

    public static List<RemarkSection> REMARK_SECTION_BOARD_OF_DIRECTOR = Arrays.asList(
            RemarkSection.BOARD_OF_DIRECTORS_INFORMATION,
            RemarkSection.BODI01,
            RemarkSection.BODI02,
            RemarkSection.PRESENT_BOD,
            RemarkSection.PAST_BOD,
            RemarkSection.INDEPENDENT_CHAIRMAN,
            RemarkSection.SAME_FAMILY,
            RemarkSection.EXECUTIVE_COMMITTEE_MEMBER,
            RemarkSection.INDEPENDENT_DETERMINE_AGENDA,
            RemarkSection.SAME_PERSON,
            RemarkSection.POWER_MEASURES_OF_BOARD_AND_MANAGEMENT,
            RemarkSection.BODI03
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_BOARD_OF_DIRECTOR_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("BoardOfDirectorsInformationIntroduction",
                        List.of(IntroductionSection.BOARD_OF_DIRECTORS_INFORMATION.getValue())));
                add(new Pair<>("Bodi01Introduction",
                        List.of(IntroductionSection.BODI01.getValue())));
                add(new Pair<>("Bodi02Introduction",
                        List.of(IntroductionSection.BODI02.getValue())));
                add(new Pair<>("PresentBodIntroduction",
                        List.of(IntroductionSection.PRESENT_BOD.getValue())));
                add(new Pair<>("PastBodIntroduction",
                        List.of(IntroductionSection.PAST_BOD.getValue())));
                add(new Pair<>("OthersBodInfoIntroduction",
                        List.of(IntroductionSection.OTHERS_BOD_INFO.getValue())));
                add(new Pair<>("PowerMeasuresOfBoardAndManagementIntroduction",
                        List.of(IntroductionSection.POWER_MEASURES_OF_BOARD_AND_MANAGEMENT.getValue())));
                add(new Pair<>("Bodi03Introduction",
                        List.of(IntroductionSection.BODI03.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_BOARD_OF_DIRECTOR_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("BoardOfDirectorsInformationRemark",
                        List.of(RemarkSection.BOARD_OF_DIRECTORS_INFORMATION.getValue())));
                add(new Pair<>("Bodi01Remark",
                        List.of(RemarkSection.BODI01.getValue())));
                add(new Pair<>("Bodi02Remark",
                        List.of(RemarkSection.BODI02.getValue())));
                add(new Pair<>("PresentBodRemark",
                        List.of(RemarkSection.PRESENT_BOD.getValue())));
                add(new Pair<>("PastBodRemark",
                        List.of(RemarkSection.PAST_BOD.getValue())));
                add(new Pair<>("OthersBodInfoRemark1",
                        List.of(RemarkSection.SAME_PERSON.getValue())));
                add(new Pair<>("OthersBodInfoRemark2",
                        List.of(RemarkSection.INDEPENDENT_CHAIRMAN.getValue())));
                add(new Pair<>("OthersBodInfoRemark3",
                        List.of(RemarkSection.SAME_FAMILY.getValue())));
                add(new Pair<>("OthersBodInfoRemark4",
                        List.of(RemarkSection.EXECUTIVE_COMMITTEE_MEMBER.getValue())));
                add(new Pair<>("OthersBodInfoRemark5",
                        List.of(RemarkSection.INDEPENDENT_DETERMINE_AGENDA.getValue())));
                add(new Pair<>("PowerMeasuresOfBoardAndManagementRemark",
                        List.of(RemarkSection.POWER_MEASURES_OF_BOARD_AND_MANAGEMENT.getValue())));
                add(new Pair<>("Bodi03Remark",
                        List.of(RemarkSection.BODI03.getValue())));
            }};


    public static final List<Pair<String, List<String>>> INTRODUCTION_SUBCOMMITTEES_PERF_SUMMARY_SECTION = new ArrayList<>() {{
        add(new Pair<>("subcommitteesPerfSummaryIntroduction", List.of(IntroductionSection.SUBCOMMITTEES_PERF_SUMMARY.getValue())));
        add(new Pair<>("sps01Introduction", List.of(IntroductionSection.SPS01.getValue())));
    }};

    public static final List<IntroductionSection> INTRODUCTION_SUBCOMMITTEES_PERF_SUMMARY_SECTION_LIST = List.of(
            IntroductionSection.SUBCOMMITTEES_PERF_SUMMARY,
            IntroductionSection.SPS01
    );

    public static List<RemarkSection> REMARK_SECTION_SUBCOMMITTEES_PERF_SUMMARY = Arrays.asList(
            RemarkSection.SUBCOMMITTEES_PERF_SUMMARY,
            RemarkSection.SPS01
    );

    public static final List<Pair<String, List<String>>> REMARK_SUBCOMMITTEES_PERF_SUMMARY_SECTION = new ArrayList<>() {{
        add(new Pair<>("subcommitteesPerfSummaryRemark", List.of(RemarkSection.SUBCOMMITTEES_PERF_SUMMARY.getValue())));
        add(new Pair<>("sps01Remark", List.of(RemarkSection.SPS01.getValue())));
    }};
    public static List<IntroductionSection> INTRODUCTION_SECTION_SUBCOMMITTEES_INFORMATION = Arrays.asList(
            IntroductionSection.SUBCOMMITTEES_INFORMATION,
            IntroductionSection.SI01,
            IntroductionSection.SUB_COMMITTEE_ROLE,
            IntroductionSection.SI02,
            IntroductionSection.PRESENT_AUDIT_COMMITTEE,
            IntroductionSection.PAST_AUDIT_COMMITTEE,
            IntroductionSection.PRESENT_EXECUTIVE_COMMITTEE,
            IntroductionSection.PAST_EXECUTIVE_COMMITTEE,
            IntroductionSection.PRESENT_SUB_COMMITTEE
    );

    public static List<RemarkSection> REMARK_SECTION_SUBCOMMITTEES_INFORMATION = Arrays.asList(
            RemarkSection.SUBCOMMITTEES_INFORMATION,
            RemarkSection.SI01,
            RemarkSection.SUB_COMMITTEE_ROLE,
            RemarkSection.SI02,
            RemarkSection.PRESENT_AUDIT_COMMITTEE,
            RemarkSection.PAST_AUDIT_COMMITTEE,
            RemarkSection.PRESENT_EXECUTIVE_COMMITTEE,
            RemarkSection.PAST_EXECUTIVE_COMMITTEE,
            RemarkSection.PRESENT_SUB_COMMITTEE
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_SUBCOMMITTEES_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SubcommitteesInformationIntroduction",
                        List.of(IntroductionSection.SUBCOMMITTEES_INFORMATION.getValue())));
                add(new Pair<>("Si01Introduction",
                        List.of(IntroductionSection.SI01.getValue())));
                add(new Pair<>("SubCommitteeRoleIntroduction",
                        List.of(IntroductionSection.SUB_COMMITTEE_ROLE.getValue())));
                add(new Pair<>("Si02Introduction",
                        List.of(IntroductionSection.SI02.getValue())));
                add(new Pair<>("PresentAuditCommitteeIntroduction",
                        List.of(IntroductionSection.PRESENT_AUDIT_COMMITTEE.getValue())));
                add(new Pair<>("PastAuditCommitteeIntroduction",
                        List.of(IntroductionSection.PAST_AUDIT_COMMITTEE.getValue())));
                add(new Pair<>("PresentExecutiveCommitteeIntroduction",
                        List.of(IntroductionSection.PRESENT_EXECUTIVE_COMMITTEE.getValue())));
                add(new Pair<>("PastExecutiveCommitteeIntroduction",
                        List.of(IntroductionSection.PAST_EXECUTIVE_COMMITTEE.getValue())));
                add(new Pair<>("PresentSubCommitteeIntroduction",
                        List.of(IntroductionSection.PRESENT_SUB_COMMITTEE.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_SUBCOMMITTEES_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("SubcommitteesInformationRemark",
                        List.of(RemarkSection.SUBCOMMITTEES_INFORMATION.getValue())));
                add(new Pair<>("Si01Remark",
                        List.of(RemarkSection.SI01.getValue())));
                add(new Pair<>("SubCommitteeRoleRemark",
                        List.of(RemarkSection.SUB_COMMITTEE_ROLE.getValue())));
                add(new Pair<>("Si02Remark",
                        List.of(RemarkSection.SI02.getValue())));
                add(new Pair<>("PresentAuditCommitteeRemark",
                        List.of(RemarkSection.PRESENT_AUDIT_COMMITTEE.getValue())));
                add(new Pair<>("PastAuditCommitteeRemark",
                        List.of(RemarkSection.PAST_AUDIT_COMMITTEE.getValue())));
                add(new Pair<>("PresentExecutiveCommitteeRemark",
                        List.of(RemarkSection.PRESENT_EXECUTIVE_COMMITTEE.getValue())));
                add(new Pair<>("PastExecutiveCommitteeRemark",
                        List.of(RemarkSection.PAST_EXECUTIVE_COMMITTEE.getValue())));
                add(new Pair<>("PresentSubCommitteeRemark",
                        List.of(RemarkSection.PRESENT_SUB_COMMITTEE.getValue())));
            }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_EXECUTIVES_INFORMATION = Arrays.asList(
            IntroductionSection.EXECUTIVES_INFORMATION,
            IntroductionSection.EXI01,
            IntroductionSection.PRESENT_EXECUTIVE,
            IntroductionSection.PRESENT_EXECUTIVE_STRUCTURE,
            IntroductionSection.EXI02,
            IntroductionSection.EXECUTIVE_REMUNERATION_POLICY,
            IntroductionSection.EXI03,
            IntroductionSection.EXECUTIVE_REMUNERATION,
            IntroductionSection.OTHER_EXECUTIVE_REMUNERATION,
            IntroductionSection.BENEFITS_PENDING_PAYMENT
    );

    public static List<RemarkSection> REMARK_SECTION_EXECUTIVES_INFORMATION = Arrays.asList(
            RemarkSection.EXECUTIVES_INFORMATION,
            RemarkSection.EXI01,
            RemarkSection.PRESENT_EXECUTIVE,
            RemarkSection.PRESENT_EXECUTIVE_STRUCTURE,
            RemarkSection.EXI02,
            RemarkSection.EXECUTIVE_REMUNERATION_POLICY,
            RemarkSection.EXI03,
            RemarkSection.BENEFITS_PENDING_PAYMENT,
            RemarkSection.CURRENT_YEAR_ESTIMATION
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_EXECUTIVES_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("ExecutivesInformationIntroduction",
                        List.of(IntroductionSection.EXECUTIVES_INFORMATION.getValue())));
                add(new Pair<>("Exi01Introduction",
                        List.of(IntroductionSection.EXI01.getValue())));
                add(new Pair<>("PresentExecutiveIntroduction",
                        List.of(IntroductionSection.PRESENT_EXECUTIVE.getValue())));
                add(new Pair<>("PresentExecutiveStructureIntroduction",
                        List.of(IntroductionSection.PRESENT_EXECUTIVE_STRUCTURE.getValue())));
                add(new Pair<>("Exi02Introduction",
                        List.of(IntroductionSection.EXI02.getValue())));
                add(new Pair<>("Exi03Introduction",
                        List.of(IntroductionSection.EXI03.getValue())));
                add(new Pair<>("ExecutiveRemunerationIntroduction",
                        List.of(IntroductionSection.EXECUTIVE_REMUNERATION.getValue())));
                add(new Pair<>("OtherExecutiveRemunerationIntroduction",
                        List.of(IntroductionSection.OTHER_EXECUTIVE_REMUNERATION.getValue())));
                add(new Pair<>("BenefitsPendingPaymentIntroduction",
                        List.of(IntroductionSection.BENEFITS_PENDING_PAYMENT.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_EXECUTIVES_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("ExecutivesInformationRemark",
                        List.of(RemarkSection.EXECUTIVES_INFORMATION.getValue())));
                add(new Pair<>("Exi01Remark",
                        List.of(RemarkSection.EXI01.getValue())));
                add(new Pair<>("PresentExecutiveRemark",
                        List.of(RemarkSection.PRESENT_EXECUTIVE.getValue())));
                add(new Pair<>("PresentExecutiveStructureRemark",
                        List.of(RemarkSection.PRESENT_EXECUTIVE_STRUCTURE.getValue())));
                add(new Pair<>("Exi02Remark",
                        List.of(RemarkSection.EXI02.getValue())));
                add(new Pair<>("Exi03Remark",
                        List.of(RemarkSection.EXI03.getValue())));
                add(new Pair<>("ExecutiveRemunerationRemark",
                        List.of(RemarkSection.EXECUTIVE_REMUNERATION.getValue())));
                add(new Pair<>("OtherExecutiveRemunerationRemark",
                        List.of(RemarkSection.OTHER_EXECUTIVE_REMUNERATION.getValue())));
                add(new Pair<>("BenefitsPendingPaymentRemark",
                        List.of(RemarkSection.BENEFITS_PENDING_PAYMENT.getValue(),
                                RemarkSection.CURRENT_YEAR_ESTIMATION.getValue())));
            }};

    public static List<IntroductionSection> INTRODUCTION_SECTION_BOARD_OF_DIRECTORS_PERF_SUMMARY = Arrays.asList(
            IntroductionSection.BOARD_OF_DIRECTORS_PERF_SUMMARY,
            IntroductionSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION,
            IntroductionSection.BODPS01,
            IntroductionSection.BODPS0101,
            IntroductionSection.INDEPENDENT_DIRECTOR_SELECTION,
            IntroductionSection.INDEPENDENT_DIRECTOR_SELECTION_CRITERIA,
            IntroductionSection.INDEPENDENT_DIRECTOR_BUSINESS_RELATIONSHIP,
            IntroductionSection.EXECUTIVES_SELECTION,
            IntroductionSection.EXECUTIVES_SELECTION_PROCESS,
            IntroductionSection.NUMBER_OF_DIRECTOR_FROM_MAJOR_SHAREHOLDER,
            IntroductionSection.MINOR_SHAREHOLDER_RIGHTS,
            IntroductionSection.DIRECTORS_QUALIFICATION_SPECIFICATION,
            IntroductionSection.BODPS0102,
            IntroductionSection.DIRECTOR_DEVELOPMENT,
            IntroductionSection.BODPS0103,
            IntroductionSection.DIRECTOR_EVALUATION_CRITERIA,
            IntroductionSection.DIRECTOR_EVALUATION,
            IntroductionSection.BODPS02,
            IntroductionSection.BODPS0201,
            IntroductionSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION,
            IntroductionSection.BODPS0202,
            IntroductionSection.DIRECTOR_REMUNERATION_CHARACTERISTIC,
            IntroductionSection.DIRECTOR_REMUNERATION,
            IntroductionSection.PENDING_PAYMENT,
            IntroductionSection.BODPS03,
            IntroductionSection.OVERSIGHT_MECHANISM,
            IntroductionSection.SHAREHOLDERS_AGREEMENT,
            IntroductionSection.BODPS04,
            IntroductionSection.BODPS0401,
            IntroductionSection.CONFLICTS_OF_INTEREST_PREVENTION,
            IntroductionSection.NUMBER_OF_CONFLICTS_OF_INTEREST_INFO, // 3 years
            IntroductionSection.BODPS0402,
            IntroductionSection.SEEK_BENEFITS_INSIDE_INFO_OPERATIONS,
            IntroductionSection.NUMBER_OF_SEEK_BENEFITS_INSIDE_INFO,  // 3 years
            IntroductionSection.BODPS0403,
            IntroductionSection.ANTI_CORRUPTION,
            IntroductionSection.NUMBER_OF_ANTI_CORRUPTION_INFO,  // 3 years
            IntroductionSection.BODPS0404,
            IntroductionSection.WHISTLEBLOWING_OPERATIONS,
            IntroductionSection.NUMBER_OF_WHISTLEBLOWING_INFO,  // 3 years
            IntroductionSection.OTHER_COMPLIANCE_MONITORING
    );

    public static List<RemarkSection> REMARK_SECTION_BOARD_OF_DIRECTORS_PERF_SUMMARY = Arrays.asList(
            RemarkSection.BOARD_OF_DIRECTORS_PERF_SUMMARY,
            RemarkSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION,
            RemarkSection.BODPS01,
            RemarkSection.BODPS0101,
            RemarkSection.INDEPENDENT_DIRECTOR_SELECTION,
            RemarkSection.INDEPENDENT_DIRECTOR_SELECTION_CRITERIA,
            RemarkSection.INDEPENDENT_DIRECTOR_BUSINESS_RELATIONSHIP,
            RemarkSection.EXECUTIVES_SELECTION,
            RemarkSection.EXECUTIVES_SELECTION_PROCESS,
            RemarkSection.NUMBER_OF_DIRECTOR_FROM_MAJOR_SHAREHOLDER,
            RemarkSection.MINOR_SHAREHOLDER_RIGHTS,
            RemarkSection.DIRECTORS_QUALIFICATION_SPECIFICATION,
            RemarkSection.BODPS0102,
            RemarkSection.DIRECTOR_DEVELOPMENT,
            RemarkSection.BODPS0103,
            RemarkSection.DIRECTOR_EVALUATION_CRITERIA,
            RemarkSection.DIRECTOR_EVALUATION,
            RemarkSection.BODPS02,
            RemarkSection.BODPS0201,
            RemarkSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION,
            RemarkSection.BODPS0202,
            RemarkSection.DIRECTOR_REMUNERATION_CHARACTERISTIC,
            RemarkSection.DIRECTOR_REMUNERATION,
            RemarkSection.PENDING_PAYMENT,
            RemarkSection.BODPS03,
            RemarkSection.OVERSIGHT_MECHANISM,
            RemarkSection.SHAREHOLDERS_AGREEMENT,
            RemarkSection.BODPS04,
            RemarkSection.BODPS0401,
            RemarkSection.CONFLICTS_OF_INTEREST_PREVENTION,
            RemarkSection.BODPS0402,
            RemarkSection.SEEK_BENEFITS_INSIDE_INFO_OPERATIONS,
            RemarkSection.BODPS0403,
            RemarkSection.ANTI_CORRUPTION,
            RemarkSection.BODPS0404,
            RemarkSection.WHISTLEBLOWING_OPERATIONS,
            RemarkSection.OTHER_COMPLIANCE_MONITORING
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("boardOfDirectorsPerfSummaryIntroduction", List.of(IntroductionSection.BOARD_OF_DIRECTORS_PERF_SUMMARY.getValue())));
                add(new Pair<>("boardOfDirectorsPerfSummarySubSectionIntroduction", List.of(IntroductionSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION.getValue())));
                add(new Pair<>("bodps01Introduction", List.of(IntroductionSection.BODPS01.getValue())));
                add(new Pair<>("bodps0101Introduction", List.of(IntroductionSection.BODPS0101.getValue())));
                add(new Pair<>("independentDirectorSelectionIntroduction", List.of(IntroductionSection.INDEPENDENT_DIRECTOR_SELECTION.getValue())));
                add(new Pair<>("independentDirectorSelectionCriteriaIntroduction", List.of(IntroductionSection.INDEPENDENT_DIRECTOR_SELECTION_CRITERIA.getValue())));
                add(new Pair<>("independentDirectorBusinessRelationshipIntroduction", List.of(IntroductionSection.INDEPENDENT_DIRECTOR_BUSINESS_RELATIONSHIP.getValue())));
                add(new Pair<>("executivesSelectionIntroduction", List.of(IntroductionSection.EXECUTIVES_SELECTION.getValue())));
                add(new Pair<>("executivesSelectionProcessIntroduction", List.of(IntroductionSection.EXECUTIVES_SELECTION_PROCESS.getValue())));
                add(new Pair<>("numberOfDirectorFromMajorShareholderIntroduction", List.of(IntroductionSection.NUMBER_OF_DIRECTOR_FROM_MAJOR_SHAREHOLDER.getValue())));
                add(new Pair<>("minorShareholderRightsIntroduction", List.of(IntroductionSection.MINOR_SHAREHOLDER_RIGHTS.getValue())));
                add(new Pair<>("directorsQualificationSpecificationIntroduction", List.of(IntroductionSection.DIRECTORS_QUALIFICATION_SPECIFICATION.getValue())));
                add(new Pair<>("bodps0102Introduction", List.of(IntroductionSection.BODPS0102.getValue())));
                add(new Pair<>("directorDevelopmentIntroduction", List.of(IntroductionSection.DIRECTOR_DEVELOPMENT.getValue())));
                add(new Pair<>("bodps0103Introduction", List.of(IntroductionSection.BODPS0103.getValue())));
                add(new Pair<>("directorEvaluationCriteriaIntroduction", List.of(IntroductionSection.DIRECTOR_EVALUATION_CRITERIA.getValue())));
                add(new Pair<>("directorEvaluationIntroduction", List.of(IntroductionSection.DIRECTOR_EVALUATION.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("boardOfDirectorsPerfSummaryRemark", List.of(RemarkSection.BOARD_OF_DIRECTORS_PERF_SUMMARY.getValue())));
                add(new Pair<>("boardOfDirectorsPerfSummarySubSectionRemark", List.of(RemarkSection.BOARD_OF_DIRECTORS_PERF_SUMMARY_SUB_SECTION.getValue())));
                add(new Pair<>("bodps01Remark", List.of(RemarkSection.BODPS01.getValue())));
                add(new Pair<>("bodps0101Remark", List.of(RemarkSection.BODPS0101.getValue())));
                add(new Pair<>("independentDirectorSelectionRemark", List.of(RemarkSection.INDEPENDENT_DIRECTOR_SELECTION.getValue())));
                add(new Pair<>("independentDirectorSelectionCriteriaRemark", List.of(RemarkSection.INDEPENDENT_DIRECTOR_SELECTION_CRITERIA.getValue())));
                add(new Pair<>("independentDirectorBusinessRelationshipRemark", List.of(RemarkSection.INDEPENDENT_DIRECTOR_BUSINESS_RELATIONSHIP.getValue())));
                add(new Pair<>("executivesSelectionRemark", List.of(RemarkSection.EXECUTIVES_SELECTION.getValue())));
                add(new Pair<>("executivesSelectionProcessRemark", List.of(RemarkSection.EXECUTIVES_SELECTION_PROCESS.getValue())));
                add(new Pair<>("numberOfDirectorFromMajorShareholderRemark", List.of(RemarkSection.NUMBER_OF_DIRECTOR_FROM_MAJOR_SHAREHOLDER.getValue())));
                add(new Pair<>("minorShareholderRightsRemark", List.of(RemarkSection.MINOR_SHAREHOLDER_RIGHTS.getValue())));
                add(new Pair<>("directorsQualificationSpecificationRemark", List.of(RemarkSection.DIRECTORS_QUALIFICATION_SPECIFICATION.getValue())));
                add(new Pair<>("bodps0102Remark", List.of(RemarkSection.BODPS0102.getValue())));
                add(new Pair<>("directorDevelopmentRemark", List.of(RemarkSection.DIRECTOR_DEVELOPMENT.getValue())));
                add(new Pair<>("bodps0103Remark", List.of(RemarkSection.BODPS0103.getValue())));
                add(new Pair<>("directorEvaluationCriteriaRemark", List.of(RemarkSection.DIRECTOR_EVALUATION_CRITERIA.getValue())));
                add(new Pair<>("directorEvaluationRemark", List.of(RemarkSection.DIRECTOR_EVALUATION.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_02 =
            new ArrayList<>() {{
                add(new Pair<>("bodps02Introduction", List.of(IntroductionSection.BODPS02.getValue())));
                add(new Pair<>("bodps0201Introduction", List.of(IntroductionSection.BODPS0201.getValue())));
                add(new Pair<>("directorMeetingAttendanceSubSectionIntroduction", List.of(IntroductionSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION.getValue())));
                add(new Pair<>("bodps0202Introduction", List.of(IntroductionSection.BODPS0202.getValue())));
                add(new Pair<>("directorRemunerationCharacteristicIntroduction", List.of(IntroductionSection.DIRECTOR_REMUNERATION_CHARACTERISTIC.getValue())));
                add(new Pair<>("directorRemunerationIntroduction", List.of(IntroductionSection.DIRECTOR_REMUNERATION.getValue())));
                add(new Pair<>("pendingPaymentIntroduction", List.of(IntroductionSection.PENDING_PAYMENT.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_02 =
            new ArrayList<>() {{
                add(new Pair<>("bodps02Remark", List.of(RemarkSection.BODPS02.getValue())));
                add(new Pair<>("bodps0201Remark", List.of(RemarkSection.BODPS0201.getValue())));
                add(new Pair<>("directorMeetingAttendanceSubSectionRemark", List.of(RemarkSection.DIRECTOR_MEETING_ATTENDANCE_SUB_SECTION.getValue())));
                add(new Pair<>("bodps0202Remark", List.of(RemarkSection.BODPS0202.getValue())));
                add(new Pair<>("directorRemunerationCharacteristicRemark", List.of(RemarkSection.DIRECTOR_REMUNERATION_CHARACTERISTIC.getValue())));
                add(new Pair<>("directorRemunerationRemark", List.of(RemarkSection.DIRECTOR_REMUNERATION.getValue())));
                add(new Pair<>("pendingPaymentRemark", List.of(RemarkSection.PENDING_PAYMENT.getValue())));
            }};


    public static final List<Pair<String, List<String>>> INTRODUCTION_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_03 =
            new ArrayList<>() {{
                add(new Pair<>("bodps03Introduction", List.of(IntroductionSection.BODPS03.getValue())));
                add(new Pair<>("oversightMechanismIntroduction", List.of(IntroductionSection.OVERSIGHT_MECHANISM.getValue())));
                add(new Pair<>("shareholdersAgreementIntroduction", List.of(IntroductionSection.SHAREHOLDERS_AGREEMENT.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_03 =
            new ArrayList<>() {{
                add(new Pair<>("bodps03Remark", List.of(RemarkSection.BODPS03.getValue())));
                add(new Pair<>("oversightMechanismRemark", List.of(RemarkSection.OVERSIGHT_MECHANISM.getValue())));
                add(new Pair<>("shareholdersAgreementRemark", List.of(RemarkSection.SHAREHOLDERS_AGREEMENT.getValue())));
            }};


    public static final List<Pair<String, List<String>>> INTRODUCTION_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_04 =
            new ArrayList<>() {{
                add(new Pair<>("bodps04Introduction", List.of(IntroductionSection.BODPS04.getValue())));
                add(new Pair<>("bodps0401Introduction", List.of(IntroductionSection.BODPS0401.getValue())));
                add(new Pair<>("conflictsOfInterestPreventionIntroduction", List.of(IntroductionSection.CONFLICTS_OF_INTEREST_PREVENTION.getValue())));
                add(new Pair<>("numberOfConflictsOfInterestInfoIntroduction", List.of(IntroductionSection.NUMBER_OF_CONFLICTS_OF_INTEREST_INFO.getValue())));
                add(new Pair<>("bodps0402Introduction", List.of(IntroductionSection.BODPS0402.getValue())));
                add(new Pair<>("seekBenefitsInsideInfoOperationsIntroduction", List.of(IntroductionSection.SEEK_BENEFITS_INSIDE_INFO_OPERATIONS.getValue())));
                add(new Pair<>("numberOfSeekBenefitsInsideInfoIntroduction", List.of(IntroductionSection.NUMBER_OF_SEEK_BENEFITS_INSIDE_INFO.getValue())));
                add(new Pair<>("bodps0403Introduction", List.of(IntroductionSection.BODPS0403.getValue())));
                add(new Pair<>("antiCorruptionIntroduction", List.of(IntroductionSection.ANTI_CORRUPTION.getValue())));
                add(new Pair<>("numberOfAntiCorruptionInfoIntroduction", List.of(IntroductionSection.NUMBER_OF_ANTI_CORRUPTION_INFO.getValue())));
                add(new Pair<>("bodps0404Introduction", List.of(IntroductionSection.BODPS0404.getValue())));
                add(new Pair<>("whistleblowingOperationsIntroduction", List.of(IntroductionSection.WHISTLEBLOWING_OPERATIONS.getValue())));
                add(new Pair<>("numberOfWhistleblowingInfoIntroduction", List.of(IntroductionSection.NUMBER_OF_WHISTLEBLOWING_INFO.getValue())));
                add(new Pair<>("otherComplianceMonitoringIntroduction", List.of(IntroductionSection.OTHER_COMPLIANCE_MONITORING.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_BOARD_OF_DIRECTORS_PERF_SUMMARY_SECTION_04 =
            new ArrayList<>() {{
                add(new Pair<>("bodps04Remark", List.of(RemarkSection.BODPS04.getValue())));
                add(new Pair<>("bodps0401Remark", List.of(RemarkSection.BODPS0401.getValue())));
                add(new Pair<>("conflictsOfInterestPreventionRemark", List.of(RemarkSection.CONFLICTS_OF_INTEREST_PREVENTION.getValue())));
                add(new Pair<>("numberOfConflictsOfInterestInfoRemark", List.of(RemarkSection.NUMBER_OF_CONFLICTS_OF_INTEREST_INFO.getValue())));
                add(new Pair<>("bodps0402Remark", List.of(RemarkSection.BODPS0402.getValue())));
                add(new Pair<>("seekBenefitsInsideInfoOperationsRemark", List.of(RemarkSection.SEEK_BENEFITS_INSIDE_INFO_OPERATIONS.getValue())));
                add(new Pair<>("numberOfSeekBenefitsInsideInfoRemark", List.of(RemarkSection.NUMBER_OF_SEEK_BENEFITS_INSIDE_INFO.getValue())));
                add(new Pair<>("bodps0403Remark", List.of(RemarkSection.BODPS0403.getValue())));
                add(new Pair<>("antiCorruptionRemark", List.of(RemarkSection.ANTI_CORRUPTION.getValue())));
                add(new Pair<>("numberOfAntiCorruptionInfoRemark", List.of(RemarkSection.NUMBER_OF_ANTI_CORRUPTION_INFO.getValue())));
                add(new Pair<>("bodps0404Remark", List.of(RemarkSection.BODPS0404.getValue())));
                add(new Pair<>("whistleblowingOperationsRemark", List.of(RemarkSection.WHISTLEBLOWING_OPERATIONS.getValue())));
                add(new Pair<>("numberOfWhistleblowingInfoRemark", List.of(RemarkSection.NUMBER_OF_WHISTLEBLOWING_INFO.getValue())));
                add(new Pair<>("otherComplianceMonitoringRemark", List.of(RemarkSection.OTHER_COMPLIANCE_MONITORING.getValue())));
            }};

    public static final List<IntroductionSection> INTRODUCTION_SECTION_CORPORATE_STRUCTURE = Arrays.asList(IntroductionSection.CORPORATE_GOVERNANCE_STRUCTURE, IntroductionSection.CGS01);
    public static final List<Pair<String, List<String>>> INTRODUCTION_CORPORATE_STRUCTURE_SECTION = new ArrayList<>() {{
        add(new Pair<>("corporateGovernanceStructureIntroduction", List.of(IntroductionSection.CORPORATE_GOVERNANCE_STRUCTURE.getValue())));
        add(new Pair<>("cgs01Introduction", List.of(IntroductionSection.CGS01.getValue())));
    }};

    public static List<RemarkSection> REMARK_SECTION_CORPORATE_STRUCTURE_ARRAY = Arrays.asList(
            RemarkSection.CORPORATE_GOVERNANCE_STRUCTURE,
            RemarkSection.CGS01
    );
    public static final List<Pair<String, List<String>>> REMARK_CORPORATE_STRUCTURE_SECTION = new ArrayList<>() {{
        add(new Pair<>("corporateGovernanceStructureRemark", List.of(RemarkSection.CORPORATE_GOVERNANCE_STRUCTURE.getValue())));
        add(new Pair<>("cgs01Remark", List.of(RemarkSection.CGS01.getValue())));
    }};


    public static List<IntroductionSection> INTRODUCTION_SECTION_EMPLOYEE_INFORMATION = Arrays.asList(
            IntroductionSection.EMPLOYEES_INFORMATION,
            IntroductionSection.EMI01,
            IntroductionSection.NUMBER_OF_EMPLOYEES,
            IntroductionSection.NUMBER_OF_EMPLOYEES_BY_POSITION_AND_DEPARTMENT,
            IntroductionSection.NUMBER_OF_MALE_EMPLOYEES_BY_POSITION,
            IntroductionSection.NUMBER_OF_FEMALE_EMPLOYEES_BY_POSITION,
            IntroductionSection.NUMBER_OF_EMPLOYEES_BY_DEPARTMENT,
            IntroductionSection.EMPLOYEES_SIGNIFICANT_CHANGES,
            IntroductionSection.EMI02,
            IntroductionSection.EMPLOYEES_REMUNERATION,
            IntroductionSection.EMPLOYEES_REMUNERATION_BY_DEPARTMENT,
            IntroductionSection.EMPLOYEE_PVD_POLICY,
            IntroductionSection.EMPLOYEE_PVD
    );

    public static List<RemarkSection> REMARK_SECTION_EMPLOYEE_INFORMATION = Arrays.asList(
            RemarkSection.EMPLOYEES_INFORMATION,
            RemarkSection.EMI01,
            RemarkSection.NUMBER_OF_EMPLOYEES,
            RemarkSection.NUMBER_OF_EMPLOYEES_BY_POSITION_AND_DEPARTMENT,
//            RemarkSection.NUMBER_OF_MALE_EMPLOYEES_BY_POSITION,
//            RemarkSection.NUMBER_OF_FEMALE_EMPLOYEES_BY_POSITION,
            RemarkSection.NUMBER_OF_EMPLOYEES_BY_DEPARTMENT,
            RemarkSection.EMPLOYEES_SIGNIFICANT_CHANGES,
            RemarkSection.EMI02,
            RemarkSection.EMPLOYEES_REMUNERATION_BY_DEPARTMENT,
            RemarkSection.EMPLOYEE_PVD_POLICY
    );

    public static final List<Pair<String, List<String>>> INTRODUCTION_EMPLOYEE_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("EmployeesInformationIntroduction",
                        List.of(IntroductionSection.EMPLOYEES_INFORMATION.getValue())));
                add(new Pair<>("Emi01Introduction",
                        List.of(IntroductionSection.EMI01.getValue())));
                add(new Pair<>("NumberOfEmployeesIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_EMPLOYEES.getValue())));
                add(new Pair<>("NumberOfEmployeesByPositionAndDepartmentIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_EMPLOYEES_BY_POSITION_AND_DEPARTMENT.getValue())));
                add(new Pair<>("NumberOfMaleEmployeesByPositionIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_MALE_EMPLOYEES_BY_POSITION.getValue())));
                add(new Pair<>("NumberOfFemaleEmployeesByPositionIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_FEMALE_EMPLOYEES_BY_POSITION.getValue())));
                add(new Pair<>("NumberOfEmployeesByDepartmentIntroduction",
                        List.of(IntroductionSection.NUMBER_OF_EMPLOYEES_BY_DEPARTMENT.getValue())));
                add(new Pair<>("EmployeesSignificantChangesIntroduction",
                        List.of(IntroductionSection.EMPLOYEES_SIGNIFICANT_CHANGES.getValue())));
                add(new Pair<>("Emi02Introduction",
                        List.of(IntroductionSection.EMI02.getValue())));
                add(new Pair<>("EmployeesRemunerationIntroduction",
                        List.of(IntroductionSection.EMPLOYEES_REMUNERATION.getValue())));
                add(new Pair<>("EmployeesRemunerationByDepartmentIntroduction",
                        List.of(IntroductionSection.EMPLOYEES_REMUNERATION_BY_DEPARTMENT.getValue())));
                add(new Pair<>("EmployeePvdPolicyIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_PVD_POLICY.getValue())));
                add(new Pair<>("EmployeePvdIntroduction",
                        List.of(IntroductionSection.EMPLOYEE_PVD.getValue())));
            }};

    public static final List<Pair<String, List<String>>> REMARK_EMPLOYEE_INFORMATION_SECTION =
            new ArrayList<>() {{
                add(new Pair<>("EmployeesInformationRemark",
                        List.of(RemarkSection.EMPLOYEES_INFORMATION.getValue())));
                add(new Pair<>("Emi01Remark",
                        List.of(RemarkSection.EMI01.getValue())));
                add(new Pair<>("NumberOfEmployeesRemark",
                        List.of(RemarkSection.NUMBER_OF_EMPLOYEES.getValue(), RemarkSection.EMPLOYEE_INFO.getValue())));
                add(new Pair<>("NumberOfEmployeesByPositionAndDepartmentRemark",
                        List.of(RemarkSection.NUMBER_OF_EMPLOYEES_BY_POSITION_AND_DEPARTMENT.getValue())));
                add(new Pair<>("NumberOfMaleEmployeesByPositionRemark",
                        List.of(RemarkSection.NUMBER_OF_MALE_EMPLOYEES_BY_POSITION.getValue())));
                add(new Pair<>("NumberOfFemaleEmployeesByPositionRemark",
                        List.of(RemarkSection.NUMBER_OF_FEMALE_EMPLOYEES_BY_POSITION.getValue())));
                add(new Pair<>("NumberOfEmployeesByDepartmentRemark",
                        List.of(RemarkSection.NUMBER_OF_EMPLOYEES_BY_DEPARTMENT.getValue())));
                add(new Pair<>("EmployeesSignificantChangesRemark",
                        List.of(RemarkSection.EMPLOYEES_SIGNIFICANT_CHANGES.getValue())));
                add(new Pair<>("EmployeesSignificantChangesRemark",
                        List.of(RemarkSection.EMPLOYEES_SIGNIFICANT_CHANGES.getValue())));
                add(new Pair<>("Emi02Remark",
                        List.of(RemarkSection.EMI02.getValue())));
                add(new Pair<>("EmployeesRemunerationByDepartmentRemark",
                        List.of(RemarkSection.EMPLOYEES_REMUNERATION_BY_DEPARTMENT.getValue())));
                add(new Pair<>("EmployeesRemunerationRemark",
                        List.of(RemarkSection.EMPLOYEE_REMUNERATION.getValue())));
                add(new Pair<>("EmployeePvdPolicyRemark",
                        List.of(RemarkSection.EMPLOYEE_PVD_POLICY.getValue())));
                add(new Pair<>("EmployeePvdRemark",
                        List.of(RemarkSection.EMPLOYEE_PVD.getValue())));
            }};

    public static final List<Pair<String, List<String>>> INTRODUCTION_DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION_SECTION = new ArrayList<>() {{
        add(new Pair<>("discussionOperationAndFinancialConditionIntroduction", List.of(IntroductionSection.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION.getValue())));
        add(new Pair<>("overallOperationIntroduction", List.of(IntroductionSection.OVERALL_OPERATION.getValue())));
        add(new Pair<>("operationalAndFinancialPerformanceIntroduction", List.of(IntroductionSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE.getValue())));
        add(new Pair<>("debtSecuritiesIssuanceIntroduction", List.of(IntroductionSection.DEBT_SECURITIES_ISSUANCE.getValue())));
    }};

    public static final List<IntroductionSection> INTRODUCTION_DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION_SECTION_LIST = List.of(
            IntroductionSection.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION,
            IntroductionSection.OVERALL_OPERATION,
            IntroductionSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE,
            IntroductionSection.DEBT_SECURITIES_ISSUANCE
    );

    public static List<RemarkSection> REMARK_SECTION_DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION = Arrays.asList(
            RemarkSection.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION,
            RemarkSection.OVERALL_OPERATION,
            RemarkSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE,
            RemarkSection.OPERATING_RESULTS_AND_PROFITS,
            RemarkSection.ASSETS_MGMT,
            RemarkSection.LIQUIDITY,
            RemarkSection.DEBT_BURDEN_AND_OFF_BALANCE_SHEET_MGMT,
            RemarkSection.MATERIAL_AND_RELATED_PARTY_TRANSACTION,
            RemarkSection.DEBT_SECURITIES_ISSUANCE
    );

    public static final List<Pair<String, List<String>>> REMARK_DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION_SECTION = new ArrayList<>() {{
        add(new Pair<>("discussionOperationAndFinancialConditionRemark", List.of(RemarkSection.DISCUSSION_OPERATION_AND_FINANCIAL_CONDITION.getValue())));
        add(new Pair<>("overallOperationRemark", List.of(RemarkSection.OVERALL_OPERATION.getValue())));
        add(new Pair<>("operationalAndFinancialPerformanceRemark", List.of(RemarkSection.OPERATIONAL_AND_FINANCIAL_PERFORMANCE.getValue())));
        add(new Pair<>("debtSecuritiesIssuanceRemark", List.of(RemarkSection.DEBT_SECURITIES_ISSUANCE.getValue())));
        add(new Pair<>("operatingResultsAndProfitsRemark", List.of(RemarkSection.OPERATING_RESULTS_AND_PROFITS.getValue())));
        add(new Pair<>("assetsMgmtRemark", List.of(RemarkSection.ASSETS_MGMT.getValue())));
        add(new Pair<>("liquidityRemark", List.of(RemarkSection.LIQUIDITY.getValue())));
        add(new Pair<>("debtBurdenAndOffBalanceSheetMgmtRemark", List.of(RemarkSection.DEBT_BURDEN_AND_OFF_BALANCE_SHEET_MGMT.getValue())));
        add(new Pair<>("materialAndRelatedPartyTransactionRemark", List.of(RemarkSection.MATERIAL_AND_RELATED_PARTY_TRANSACTION.getValue())));
    }};

    @Value("${translator.url.service.authen:https://dev.identity.central.setstage.or.th/realms/ENS-ENTAI-DEV/protocol/openid-connect/token}")
    public String authenTranslatorUrl;
    @Value("${translator.url.service.default:https://eng2thaitranslate.dev.test.set/api}")
    public String translatorDefaultUrl;
    @Value("${translator.data.path:/protect/v1/translate}")
    public String translatorDataPath;
    @Value("${translator.grant_type:client_credentials}")
    public String translatorGrantType;
    @Value("${translator.client_id:eonereportuser}")
    public String translatorClientId;
    @Value("${translator.client.secret:IHPPJAv6YXyRAF6n5u7dcrLmyyUaDRv4}")
    public String translatorClientSecret;
    @Value("${translator.call_back_url:https://one.setlinkdev.set/api/translate/call-back}")
    public String translatorCallBackUrl;
    public static final String EONE = "EONE";

}
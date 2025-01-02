package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetail extends AbstractReportDetail {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OverallBusinessCorporatePolicy overallBusinessCorporatePolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<IncomeStructure> incomeStructures = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OtherIncomeStructure> otherIncomeStructures = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ProductInfo productInfo;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CompanyRisk> companyRisks = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    @Valid
    private InvestmentRisk investmentRisk;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CgOverviewPolicy cgOverviewPolicy;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DirectorPolicy directorPolicy;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CodeOfConduct codeOfConduct;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BoardOfDirector boardOfDirector;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BoardProfile> boardProfiles = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BoardHistory> boardHistories = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Executive executive;

//    @OneToMany(mappedBy = "reportDetail",
//            cascade = {CascadeType.ALL},
//            fetch = FetchType.LAZY)
//    @OrderBy("id")
//    @JsonIgnore
//    private List<EmployeeInfoByDivision> employeeInfoByDivisions;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Employee employee;

//    @OneToMany(mappedBy = "reportDetail",
//            cascade = {CascadeType.ALL},
//            fetch = FetchType.LAZY)
//    @OrderBy("id")
//    @JsonIgnore
//    private List<ProvidentFund> providentFunds;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SubCommitteeRole subCommitteeRole;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SubCommittee> subCommittees = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Supervisor supervisor;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SupervisorProfile> supervisorProfiles = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AuditorInfo> auditorInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AuditorProfile> auditorProfiles = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DirectorPerformanceSummary directorPerformanceSummary;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SustainabilityPolicyAndGoal sustainabilityPolicyAndGoal;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EnvironmentalSustainabilityMgmt environmentalSustainabilityMgmt;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EnvironmentalIssue> environmentalIssues = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<Risk> risks = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EnvironmentalPerformance environmentalPerformance;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EnergyInfo> energyInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SocialSustainabilityMgmt socialSustainabilityMgmt;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<HumanRightsIssue> humanRightsIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SocialPerformance socialPerformance;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeInfo> employeeInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CsrActivity> csrActivities = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SustainabilityReport sustainabilityReport;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SustainabilityStandard> sustainabilityStandards = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<FinancialInfo> financialInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AttachFile> attachFiles = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<MaterialChangesAndDevelopment> materialChangesAndDevelopments = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OtherEnergyMgmtInfo> otherEnergyMgmtInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BusinessStrategiesOverview businessStrategiesOverview;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OtherBusinessStrategiesOverview> otherBusinessStrategiesOverviews = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SecuritiesOfferingObligation securitiesOfferingObligation;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CgIssue> cgIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ShareholderPolicy shareholderPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ShareholderIssue> shareholderIssues = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AgainstCorruptionProject> againstCorruptionProjects = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AgainstCorruption againstCorruption;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CocIssue> cocIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EncourageCompliance encourageCompliance;


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PaidUpCapital paidUpCapital;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OtherMarket> otherMarketList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherShareRightsDiffOrdinaryShare otherShareRightsDiffOrdinaryShare;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Nvdr nvdr;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DividendPolicy dividendPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DividendInfo> dividendInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private IssuanceOtherSecurities issuanceOtherSecurities;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ConvertibleSecurities> convertibleSecurities = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<Debenture> debentures = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BillOfExchange> billOfExchanges = new ArrayList<>();




    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SpentRaisedFund spentRaisedFund;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ChangesAndDevPolicy changesAndDevPolicy;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ImplLatestCg implLatestCg;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherResultAndOutcomesCg otherResultAndOutcomesCg;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private RiskFactor riskFactor;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private RiskMgmtPolicyAndPlan riskMgmtPolicyAndPlan;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SustainabilityGoal sustainabilityGoal;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SdgsGoal> sdgsGoals = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ReviewedSustainabilityPolicyAndGoal reviewedSustainabilityPolicyAndGoal;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BusinessValueChain businessValueChain;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BusinessValueChainImgInfo> businessValueChainImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BusinessValueChainStakeholder businessValueChainStakeholder;


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ReviewedEnvironmentalPolicy reviewedEnvironmentalPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ReviewedEnvironmentalIssue> reviewedEnvironmentalIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EnergyTarget energyTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EnergyTargetInfo> energyTargetInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private WaterTarget waterTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<WaterTargetInfo> waterTargetInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private EnergyMgmtPlan energyMgmtPlan;

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private WaterMgmtPlan waterMgmtPlan;

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private WasteMgmtPlan wasteMgmtPlan;

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private EnergyMgmtPerformance energyMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private WasteTarget wasteTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<WasteTargetInfo> wasteTargetInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private WasteMgmtPerformance wasteMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private WaterMgmtPerformance waterMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OperationalOrganizationPolicy operationalOrganizationPolicy;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private GhGasMgmtPlan ghGasMgmtPlan;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ShareholderStructureInfo> shareholderStructureInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<GhGasComplianceStandards> ghGasComplianceStandardsList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private GhGasTarget ghGasTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<TenPercentInfo> tenPercentInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private TenPercent tenPercent;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PotentialConflictShareholder potentialConflictShareholder;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private MajorShareholderRelationship majorShareholderRelationship;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<MajorShareholderRelationshipImgInfo> majorShareholderRelationshipImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private MajorShareholder majorShareholder;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ShareholderAgreement shareholderAgreement;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<MajorShareholderInfo> majorShareholderInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BusinessCharacteristics businessCharacteristics;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ProductServiceCharacteristicsInfo> productServiceCharacteristicsInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private MarketingPolicy marketingPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<MarketingPolicyInfo> marketingPolicyInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Procurement procurement;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ProcurementInfo> procurementInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Asset asset;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<PermanentAppraisalPrices> permanentAppraisalPrices = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<IntangibleAppraisalPrices> intangibleAppraisalPrices = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<IntangibleAppraisalPriceImgInfo> intangibleAppraisalPriceImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SubsidiariesInvestmentPolicy subsidiariesInvestmentPolicy;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private UnderConstructionProject underConstructionProject;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<UnderConstructionProjectInfo> underConstructionProjectInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<UnderConstructionProjectInfoImgInfo> underConstructionProjectInfoImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ShareholdingDiagram shareholdingDiagram;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<GhGasTargetIssue> ghGasTargetIssueList = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ShareholdingDiagramImgInfo> shareholdingDiagramImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PotentialFactorAffectFinancialCondition potentialFactorAffectFinancialCondition;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<NetZeroGhGasEmissions> netZeroGhGasEmissionsList = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<PotentialFactorAffectFinancialConditionInfo> potentialFactorAffectFinancialConditionInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CarbonNeutrality> carbonNeutralityList = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<HumanRightsComplianceStd> humanRightsComplianceStds = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OtherGhGasTarget> ghGasOtherList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ReviewedHumanRightsPolicy reviewedHumanRightsPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ReviewedHumanRightsIssue> reviewedHumanRightsIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private GhGasMgmtPerformance ghGasMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Hrdd hrdd;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<HrddImgInfo> hrddImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeeMgmtPlan employeeMgmtPlan;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeMgmtPlanIssue> employeeMgmtPlanIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeeMgmtTarget employeeMgmtTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeMgmtTargetInfo> employeeMgmtTargetInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeeMgmtPerformance employeeMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeeGroupingForm employeeGroupingForm;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeGroupingFormIssue> employeeGroupingFormIssues = new ArrayList<>();
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OperationalAndFinancialPerformance operationalAndFinancialPerformance;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OperationalAndFinancialPerformanceInfo> operationalAndFinancialPerformanceInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OperationalAndFinancialPerformanceImgInfo> operationalAndFinancialPerformanceImgInfos = new ArrayList<>();


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CustomerMgmtPlan customerMgmtPlan;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CustomerMgmtPlanIssue> customerMgmtPlanIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CustomerMgmtTarget customerMgmtTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CustomerMgmtTargetInfo> customerMgmtTargetInfos= new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CustomerMgmtPerformance customerMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DebtSecuritiesIssuance debtSecuritiesIssuance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SocialMgmtPlan socialMgmtPlan;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SocialMgmtPlanIssue> socialMgmtPlanIssues = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SocialMgmtTarget socialMgmtTarget;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SocialMgmtTargetInfo> socialMgmtTargetInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SocialMgmtPerformance socialMgmtPerformance;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherSocialMgmtPlan otherSocialMgmtPlan;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private LegalSocialIncident legalSocialIncident;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<LegalSocialIncidentInfo> legalSocialIncidentInfos = new ArrayList<>();
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherMaterialFacts otherMaterialFacts;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DecisionInfluencingInformationInfo> decisionInfluencingInformationInfos= new ArrayList<>();


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BodOpinionSummary bodOpinionSummary;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<InternalControlSystemsInfo> internalControlSystemsInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private InternalControlSystem internalControlSystem;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Deficiencies deficiencies;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DeficienciesInfo> deficienciesInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DeficienciesDetailInfo> deficienciesDetailInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AuditCommitteeOpinion auditCommitteeOpinion;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private HeadOfInternalAudit headOfInternalAudit;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AppointmentDischargeTransferHeadOfInternalAudit appointmentDischargeTransferHeadOfInternalAudit;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private GeneralInfo generalInfo;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SecuritiesRegistrarsInfo> securitiesRegistrarsInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BondHolderRepresentativesInfo> bondHolderRepresentativesInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BondRegistrarsInfo> bondRegistrarsInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<LegalAdvisorsOtherContacts> legalAdvisorsOtherContacts = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private LegalDisputes legalDisputes;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<LegalDisputesInfo> legalDisputesInfos = new ArrayList<>();


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private RelatedPartyTransactions relatedPartyTransactions;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ConflictOfInterestGroup conflictOfInterestGroup;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ConflictOfInterestGroupInfo> conflictOfInterestGroupInfos = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ConflictOfInterestGroupAndTransactionInfo> conflictOfInterestGroupAndTransactionInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PolicyAndFutureTrend policyAndFutureTrend;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AppraisalPrice appraisalPrice;
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ForeignStockExchanges foreignStockExchanges;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ForeignStockExchangesInfo> foreignStockExchangesInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private FinancialInstitutionWithRegularContact financialInstitutionWithRegularContact;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<FinancialInstitutionInfo> financialInstitutionInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherEnvironmentalMgmtPlan otherEnvironmentalMgmtPlan;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<LegalEnvironmentalIncident> legalGhGasIncident;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ChairmanMessageImgInfo> chairmanMessageImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CorporateGovernanceStructure corporateGovernanceStructure;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<CorporateGovernanceStructureImgInfo> corporateGovernanceStructureImgInfos = new ArrayList<>();
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private FinancialStatements financialStatements;


    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<FinancialRatioInfo> financialRatioInfos = new ArrayList<>();
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PowerMeasuresOfBoardAndManagement powerMeasuresOfBoardAndManagement;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<BalancingPowerMethodsInfo> balancingPowerMethodsInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BoardCharter boardCharter;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<SubCommitteeRoleInfo> subCommitteeRoleInfos = new ArrayList<>();


    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SubCommitteeTopic subCommitteeTopic;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private PresentExecutiveStructure presentExecutiveStructure;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<PresentExecutiveStructureImgInfo> presentExecutiveStructureImgInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ExecutiveRemunerationPolicy executiveRemunerationPolicy;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ExecutiveRemunerationInfo> executiveRemunerationInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ExecutiveRemuneration executiveRemuneration;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private BenefitsPendingPayment benefitsPendingPayment;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeByDepartmentInfo> employeeByDepartmentInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeesSignificantChanges employeesSignificantChanges;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeeRemuneration employeeRemuneration;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EmployeeRemunerationByDepartmentInfo> employeeRemunerationByDepartmentInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private EmployeePvdPolicy employeePvdPolicy;
    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private RepresentativesInThailand representativesInThailand;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<RepresentativesInThailandInfo> representativesInThailandInfoList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private CompanyAudit companyAudit;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private IndependentDirectorSelection independentDirectorSelection;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private IndependentDirectorBusinessRelationship independentDirectorBusinessRelationship;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ExecutiveSelection executiveSelection;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ExecutivesMinorShareholderAppointmentInfo> executivesMinorShareholderAppointmentInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DirectorsQualificationSpecification directorsQualificationSpecification;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DirectorsQualificationSpecificationInfo> directorsQualificationSpecificationInfoList =
            new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DirectorEvaluation directorEvaluation;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OversightMechanism oversightMechanism;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<OversightMechanismInfo> oversightMechanismInfoList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ShareholdersAgreement shareholdersAgreement;


    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<EgmMeetingDate> egmMeetingDates = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DirectorEvaluationInfo> directorEvaluationInfos = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private DirectorRemuneration directorRemuneration;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private ComplianceMonitoring complianceMonitoring;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<DirectorPerfInfo> directorPerfInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ComplianceMonitoringInfo> complianceMonitoringInfoList = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private SeekBenefitsInsideInfoOperations seekBenefitsInsideInfoOperations;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AntiCorruption antiCorruption;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AntiCorruptionOperationsForm> antiCorruptionOperationsForms = new ArrayList<>();

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private Whistleblowing whistleblowing;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private OtherComplianceMonitoring otherComplianceMonitoring;

    @OneToOne(mappedBy = "reportDetail", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "report_detail_id")
    @JsonIgnore
    private AuditCommitteesPerfSummary auditCommitteesPerfSummary;

    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<FinancialReport> financialReports = new ArrayList<>();


    @OneToMany(mappedBy = "reportDetail",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<AdditionalImgInfo> additionalImgInfos = new ArrayList<>();
}

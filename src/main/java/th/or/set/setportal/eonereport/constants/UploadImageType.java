package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum UploadImageType {

    BusinessValueChain("BusinessValueChain"),
    MajorShareholderRelationship("MajorShareholderRelationship"),
    ShareholdingDiagram("ShareholdingDiagram"),
    IntangibleAppraisalPrice("IntangibleAppraisalPrice"),
    UnderConstructionProjectInfo("UnderConstructionProjectInfo"),
    Hrdd("Hrdd"),
    OverallOperation("OverallOperation"),
    OperatingResultsAndProfits("OperatingResultsAndProfits"),
    AssetsMgmt("AssetsMgmt"),
    Liquidity("Liquidity"),
    DebtBurdenAndOffBalanceSheetMgmt("DebtBurdenAndOffBalanceSheetMgmt"),
    MaterialAndRelatedPartyTransaction("MaterialAndRelatedPartyTransaction"),
    OtherOperationalAndFinancialPerformance("OtherOperationalAndFinancialPerformance"),
    ChairmanMessage("ChairmanMessage"),
    CorporateGovernSceStructure("CorporateGovernSceStructure"),
    PresentExecutiveStructure("PresentExecutiveStructure"),
    IncomeStructure("IncomeStructure"),
    ProductOrService("ProductOrService"),
    MarketPolicy("MarketPolicy"),
    BoardOfDirector("BoardOfDirector"),
    DividendInfo("DividendInfo"),
    EmployeeMgmtPerformance("EmployeeMgmtPerformance"),
    SocialMgmtPerformance("SocialMgmtPerformance"),
    CustomerMgmtPerformance("CustomerMgmtPerformance"),
    EnergyMgmtPlan("EnergyMgmtPlan"),
    WaterMgmtPlan("WaterMgmtPlan"),
    WasteMgmtPlan("WasteMgmtPlan"),
    GgGasMgmtPlan("GgGasMgmtPlan"),
    AgainstFraudAndCorruption("AgainstFraudAndCorruption"),
    EncourageCompliance("EncourageCompliance"),
    BusinessValueChainStakeHolder("BusinessValueChainStakeHolder"),
    CompanyLogo("CompanyLogo");

    private final String value;

    UploadImageType(String value) {
        this.value = value;
    }
}

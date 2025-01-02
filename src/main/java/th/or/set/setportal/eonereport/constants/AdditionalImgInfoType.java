package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum AdditionalImgInfoType {
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

    AdditionalImgInfoType(String value) {
        this.value = value;
    }
}
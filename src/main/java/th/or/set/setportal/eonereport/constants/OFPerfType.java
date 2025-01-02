package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import th.or.set.setportal.eonereport.exception.NotFoundException;

@Getter
public enum OFPerfType {

    OVERALL("OVERALL"),
    PROFITS("PROFITS"),
    ASSETS("ASSETS"),
    LIQUIDITY("LIQUIDITY"),
    DEBT("DEBT"),
    MATERIAL("MATERIAL"),
    OTHER("OTHER");

    private final String value;

    OFPerfType(String value) {
        this.value = value;
    }

    public static RemarkSection getRemarkSection(OFPerfType value) {
        switch (value) {
            case PROFITS:
                return RemarkSection.OPERATING_RESULTS_AND_PROFITS;
            case ASSETS:
                return RemarkSection.ASSETS_MGMT;
            case LIQUIDITY:
                return RemarkSection.LIQUIDITY;
            case DEBT:
                return RemarkSection.DEBT_BURDEN_AND_OFF_BALANCE_SHEET_MGMT;
            case MATERIAL:
                return RemarkSection.MATERIAL_AND_RELATED_PARTY_TRANSACTION;
            case OTHER:
                return RemarkSection.OTHER_OPERATIONAL_AND_FINANCIAL_PERFORMANCE;
            default:
                throw new NotFoundException("Not found RemarkSection for OFPerfType: " + value.getValue());
        }
    }
}

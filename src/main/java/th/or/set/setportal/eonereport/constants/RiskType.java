package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum RiskType {

    COMPANY_RISK("COMPANY_RISK"),
    HOLDER_RISK("HOLDER_RISK"),
    FOREIGN_RISK("FOREIGN_RISK");

    private final String value;

    RiskType(String value) {
        this.value = value;
    }
}

package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum SupervisorProfileType {

    ACCOUNTANT("ACCOUNTANT"),
    SECRETARY("SECRETARY"),
    INTERNAL_AUDIT("INTERNAL_AUDIT"),
    COMPLIANCE("COMPLIANCE"),
    INVESTOR_RELATION("INVESTOR_RELATION");

    private final String value;

    SupervisorProfileType(String value) {
        this.value = value;
    }
}

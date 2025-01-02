package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ShareholderStructureType {

    SUBSIDIARIES("SUBSIDIARIES"),
    ASSOCIATED_COMPANIES("ASSOCIATED_COMPANIES"),
    JOINT_VENTURE_COMPANIES("JOINT_VENTURE_COMPANIES");
    private final String value;

    ShareholderStructureType(String value) {
        this.value = value;
    }
}

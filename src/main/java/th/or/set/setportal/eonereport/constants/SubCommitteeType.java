package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum SubCommitteeType {

    OTHER_SUB_COMMITTEE("OTHER_SUB_COMMITTEE"),
    AUDIT_COMMITTEE("AUDIT_COMMITTEE"),
    EXECUTIVE_COMMITTEE("EXECUTIVE_COMMITTEE"),
    BOARD_OF_DIRECTOR("BOARD_OF_DIRECTOR"),;

    private final String value;

    SubCommitteeType(String value) {
        this.value = value;
    }
}

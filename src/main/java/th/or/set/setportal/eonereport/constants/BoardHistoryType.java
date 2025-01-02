package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum BoardHistoryType {

    DIRECTOR("DIRECTOR"),
    SUB_COMMITTEE("SUB_COMMITTEE"),
    AUDIT_COMMITTEE("AUDIT_COMMITTEE"),
    EXECUTIVE("EXECUTIVE"),
    EXECUTIVE_COMMITTEE("EXECUTIVE_COMMITTEE");

    private final String value;

    BoardHistoryType(String value) {
        this.value = value;
    }
}

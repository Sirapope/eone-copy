package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ReportDetailStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    COMPLETE("COMPLETE"),
    INCOMPLETE("INCOMPLETE"),
    WAITING("WAITING"),
    CANCEL("CANCEL");

    private final String value;

    ReportDetailStatus(String value) {
        this.value = value;
    }
}

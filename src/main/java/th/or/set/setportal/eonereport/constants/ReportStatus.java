package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ReportStatus {

    APPROVE("APPROVE"),
    DRAFT("DRAFT"),
    WAIT_FOR_APPROVE("WAIT_FOR_APPROVE"),
    CANCEL("CANCEL");

    private final String value;

    ReportStatus(String value) {
        this.value = value;
    }
}

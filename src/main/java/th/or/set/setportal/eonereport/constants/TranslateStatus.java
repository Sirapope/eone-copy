package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum TranslateStatus {

    PROCESSING("PROCESSING"),
    COMPLETE("COMPLETE"),
    FAIL("FAIL");

    private final String value;

    TranslateStatus(String value) {
        this.value = value;
    }
}

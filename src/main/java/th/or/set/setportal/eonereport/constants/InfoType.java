package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum InfoType {

    GENERAL("GENERAL"),
    OTHER("OTHER"),
    SUMMARY("SUMMARY"),
    DEFAULT("DEFAULT");

    private final String value;

    InfoType(String value) {
        this.value = value;
    }
}

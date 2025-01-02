package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum StatusGenFile {

    PROCESSING("PROCESSING"),
    ERROR("ERROR"),
    COMPLETE("COMPLETE"),
    REGENERATE("REGENERATE"),
    NULL(null);

    private final String value;

    StatusGenFile(String value) {
        this.value = value;
    }
}

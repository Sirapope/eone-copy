package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ChangeResponseType {

    TITLE("TITLE"),
    FIX_FIELD("FIX_FIELD"),
    INFO_TYPE_CONFIG("INFO_TYPE_CONFIG");

    private final String value;

    ChangeResponseType(String value) {
        this.value = value;
    }
}

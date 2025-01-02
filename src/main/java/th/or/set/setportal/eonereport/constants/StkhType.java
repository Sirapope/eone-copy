package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum StkhType {

    INTERNAL("internal"),
    EXTERNAL("external");

    private final String value;

    StkhType(String value) {
        this.value = value;
    }
}

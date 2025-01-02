package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum HistoryType {

    PAST("PAST"),
    PRESENT("PRESENT"),
    REPRESENT("REPRESENT");

    private final String value;

    HistoryType(String value) {
        this.value = value;
    }
}

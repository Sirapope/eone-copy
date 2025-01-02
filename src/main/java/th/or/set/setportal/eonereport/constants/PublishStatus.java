package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum PublishStatus {

    PUBLISHED("PUBLISHED"),
    WAIT_FOR_CANCEL("WAIT_FOR_CANCEL"),
    CANCEL("CANCEL");

    private final String value;

    PublishStatus(String value) {
        this.value = value;
    }
}

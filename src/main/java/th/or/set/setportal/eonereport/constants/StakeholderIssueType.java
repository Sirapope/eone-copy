package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum StakeholderIssueType {

    STAKEHOLDER(InfoCategory.STAKEHOLDER.getValue()),
    COMMUNICATION_ACTIVITY(InfoCategory.COMMUNICATION_ACTIVITY.getValue());

    private final String value;

    StakeholderIssueType(String value) {
        this.value = value;
    }
}

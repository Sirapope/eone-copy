package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ConflictOfInterestGroupsInfoUnit {

    baht("baht"),
    million("million");

    private final String value;

    ConflictOfInterestGroupsInfoUnit(String value) {
        this.value = value;
    }
}

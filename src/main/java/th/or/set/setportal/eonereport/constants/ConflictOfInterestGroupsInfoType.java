package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ConflictOfInterestGroupsInfoType {

    JURISTIC_PERSON("JURISTIC_PERSON"),
    PERSON("PERSON");

    private final String value;

    ConflictOfInterestGroupsInfoType(String value) {
        this.value = value;
    }
}

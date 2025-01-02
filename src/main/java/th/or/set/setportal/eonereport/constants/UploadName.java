package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum UploadName {

    SustainabilityPolicyAndGoal("SustainabilityPolicyAndGoal"),
    EnvironmentalSustainabilityMgmt("EnvironmentalSustainabilityMgmt"),
    SocialSustainabilityMgmt("SocialSustainabilityMgmt"),
    OtherSustainabilityInfo("OtherSustainabilityInfo"),
    CgOverviewPolicy("CgOverviewPolicy");

    private final String value;

    UploadName(String value) {
        this.value = value;
    }
}

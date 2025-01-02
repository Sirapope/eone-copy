package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum ComplianceMonitoringInfoType {

    CONFLICTS_OF_INTEREST("CONFLICTS_OF_INTEREST"),
    SEEK_BENEFITS_INSIDE("SEEK_BENEFITS_INSIDE"),
    ANTI_CORRUPTION("ANTI_CORRUPTION"),
    WHISTLEBLOWING("WHISTLEBLOWING");

    private final String value;

    ComplianceMonitoringInfoType(String value) {
        this.value = value;
    }
}

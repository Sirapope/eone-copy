package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum AttachType {

    DirectorsExecutives("DirectorsExecutives"),
    SubsidiariesExecutives("SubsidiariesExecutives"),
    AuditAndCompliance("AuditAndCompliance"),
    AssetsInformation("AssetsInformation"),
    CG("CG"),
    AuditCommitteeReport("AuditCommitteeReport");

    private final String value;

    AttachType(String value) {
        this.value = value;
    }

    public static List<AttachType> getAllAttachType(){
        List<AttachType> requireAttachType = new ArrayList<>();
        requireAttachType.add(AttachType.DirectorsExecutives);
        requireAttachType.add(AttachType.SubsidiariesExecutives);
        requireAttachType.add(AttachType.AuditAndCompliance);
        requireAttachType.add(AttachType.AssetsInformation);
        requireAttachType.add(AttachType.CG);
        requireAttachType.add(AttachType.AuditCommitteeReport);
        return requireAttachType;
    }
}

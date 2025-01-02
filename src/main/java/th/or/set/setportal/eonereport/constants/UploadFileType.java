package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum UploadFileType {

    AttachFile("AttachFile");

    private final String value;

    UploadFileType(String value) {
        this.value = value;
    }
}

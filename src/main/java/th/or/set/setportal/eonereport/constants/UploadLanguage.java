package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UploadLanguage {

    T("T"),
    B("B"),
    S("S");

    private String value;

    UploadLanguage(String value) {
        this.value = value;
    }
}

package th.or.set.setportal.eonereport.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import th.or.set.setportal.eonereport.constants.RemarkSection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemarkData {
    @JsonIgnore
    private Long id;

    private String remarkTh;

    private boolean remarkThIsChanged;

    private String remarkEn;

    private boolean remarkEnIsChanged;

    @JsonIgnore
    private RemarkSection remarkSection;


    public RemarkData(String remarkTh, String remarkEn) {
        this.remarkTh = remarkTh;
        this.remarkEn = remarkEn;
    }

    public RemarkData(Long id, String remarkTh, String remarkEn) {
        this.id = id;
        this.remarkTh = remarkTh;
        this.remarkEn = remarkEn;
    }
    public RemarkData(String remarkTh, String remarkEn, String section) {
        this.remarkTh = remarkTh;
        this.remarkEn = remarkEn;
        this.remarkSection = RemarkSection.getByKey(section);
    }

    public RemarkData(Long id, String remarkTh, String remarkEn, String remarkSection) {
        this.id = id;
        this.remarkTh = remarkTh;
        this.remarkEn = remarkEn;
        this.remarkSection = RemarkSection.getKeyByValue(remarkSection);
    }

    public RemarkData(Long id, String remarkTh, String remarkEn, RemarkSection remarkSection) {
        this.id = id;
        this.remarkTh = remarkTh;
        this.remarkEn = remarkEn;
        this.remarkSection = remarkSection;
    }
}


package th.or.set.setportal.eonereport.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class ReportConfig {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    private String configKey;

    private String configValue;

    private String descriptionEn;

    private String descriptionTh;

    public ReportConfig(Long id, String configKey, String configValue, String descriptionEn, String descriptionTh) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.descriptionEn = descriptionEn;
        this.descriptionTh = descriptionTh;
    }
}

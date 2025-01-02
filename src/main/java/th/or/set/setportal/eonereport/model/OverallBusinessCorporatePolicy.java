package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static th.or.set.setportal.eonereport.service.UtilService.checkFieldIsNull;
import static th.or.set.setportal.eonereport.service.UtilService.validateEn;

@Slf4j
@Data
@Entity
@NoArgsConstructor
public class OverallBusinessCorporatePolicy {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "report_detail_id", referencedColumnName = "id")
    @JsonIgnore
    private ReportDetail reportDetail;

    private String nameTh;

    private String nameEn;

    private String symbol;

    private String addressTh;

    private String addressEn;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
//    @JoinColumn(name = "province_id", referencedColumnName = "id")
//    private Province province;

    private String zipCode;

    private String businessTypeTh;

    private String businessTypeEn;

    private String juristicRegistrationNo;

    private String telephone;

    private String fax;

    private String website;

    private String email;

    private Long commonPaidupShare;

    private Long preferredPaidupShare;

    private Long companyInfoRemarkId;

    private Long companyInfoIntroId;


    @JsonIgnore
    public boolean isCompleteByLanguage(String language) {
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> optionals = Arrays.asList("fax", "id", "companyInfoRemarkId", "companyInfoIntroId");
        String oppositeLanguage = "Th".equals(language) ? "En" : "Th";
        for (Field f : fields) {
            if (f.getName().contains(oppositeLanguage)) {
                continue;
            }
            if (validateRequire(f, optionals)) {
                return false;
            }
        }
        return "Th".equals(language) || validateEn(Arrays.asList(this.addressEn, this.businessTypeEn, this.nameEn));
    }

    private boolean validateRequire(Field f, List<String> optionals) {
        try {
            if (!optionals.contains(f.getName())) {
                Object value = f.get(this);
                if (checkFieldIsNull(value)) {
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return false;
    }

}

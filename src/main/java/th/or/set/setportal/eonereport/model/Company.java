package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    private Long companySmdId;

    private Long companyScpId;

    private String companyCode;

    private String companyType;

    private String companyEn;

    private String companyTh;

    private String fiscalEnd;

    private Long accountForm;

    @Transient
    private LocalDateTime fiscalEndDate;

    @Type(type = "yes_no")
    private Boolean active;

    @OneToMany(mappedBy = "company",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @OrderBy("id")
    @JsonIgnore
    private List<UserProfile> userProfiles = new ArrayList<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(
//            name = "contact_card_company",
//            joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "contact_card_id"))
//    @OrderBy("id")
//    @JsonIgnore
//    private List<ContactCard> contactCards = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JsonIgnore
//    @JoinColumn(name = "market_id", referencedColumnName = "id")
//    private Market market;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JsonIgnore
//    @JoinColumn(name = "industry_id", referencedColumnName = "id")
//    private Industry industry;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JsonIgnore
//    @JoinColumn(name = "sector_id", referencedColumnName = "id")
//    private Sector sector;

}

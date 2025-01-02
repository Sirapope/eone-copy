package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import th.or.set.setportal.eonereport.constants.ReportDetailStatus;
import th.or.set.setportal.eonereport.service.StringToBigDecimalDeserializer;
import th.or.set.setportal.eonereport.service.ToStringSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@MappedSuperclass
@Slf4j
public abstract class AbstractReportDetail {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    @Version
    private Integer version;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "node_config_id", referencedColumnName = "id")
    private NodeConfig nodeConfig;

    @Enumerated(EnumType.STRING)
    private ReportDetailStatus status;

    @Type(type = "yes_no")
    private Boolean enableNode;

    @Type(type = "yes_no")
    private Boolean confirm;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "confirmer_id", referencedColumnName = "id")
    private UserProfile confirmer;

    private LocalDateTime confirmDate;

    @Enumerated(EnumType.STRING)
    private ReportDetailStatus statusEn;

    @Type(type = "yes_no")
    private Boolean confirmEn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "confirmer_id_en", referencedColumnName = "id")
    private UserProfile confirmerEn;

    private LocalDateTime confirmDateEn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "current_user_id", referencedColumnName = "id")
    private UserProfile currentUser;

    private LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "update_by_user_id", referencedColumnName = "id")
    private UserProfile updateByUser;

    @JsonIgnore
    private LocalDateTime esgSyncUpdateDate;

    @Transient
    private boolean esgSync;

    @JsonDeserialize(using = StringToBigDecimalDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal successPercentage;

    @Type(type = "yes_no")
    private Boolean canConfirmTh;

    @Type(type = "yes_no")
    private Boolean canConfirmEn;

    private String translateStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "translate_by_user_id", referencedColumnName = "id")
    private UserProfile translateByUser;

    public void updateEsgSync() {
        this.esgSync = esgSyncUpdateDate == null ? false : true;
    }

}

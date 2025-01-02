package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import th.or.set.setportal.eonereport.constants.ReportDetailStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NodeConfig {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    @Version
    private Integer version;

    private BigDecimal phase;

    private Long nodeLevel;

    private String name;

    private String descriptionTh;

    private String descriptionEn;

    @Enumerated(EnumType.STRING)
    private ReportDetailStatus status;

    private Long nodeGroup;

    private Long sequence;

    private Long parentId;

    @Type(type = "yes_no")
    private Boolean active;

    @Type(type = "yes_no")
    private Boolean enableNode;

    @OneToMany(mappedBy = "nodeConfig",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<ReportDetail> reportDetails = new ArrayList<>();

    @OneToMany(mappedBy = "nodeConfig",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonIgnore
    private List<NodeDictionary> nodeDictionaries = new ArrayList<>();

    private BigDecimal reportPhase;

    private String subFlow;

    @Type(type = "yes_no")
    private Boolean canConfirm;
}

package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import th.or.set.setportal.eonereport.constants.PublishStatus;
import th.or.set.setportal.eonereport.constants.ReportStatus;
import th.or.set.setportal.eonereport.constants.UploadLanguage;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractReport {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(generator = "native")
    private Long id;

    @Version
    private Integer version;

    private BigDecimal phase;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private LocalDateTime submitDate;

    private LocalDateTime statusDate;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime statusDateEn;

    @Enumerated(EnumType.STRING)
    private ReportStatus statusEn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private UserProfile creator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "creator_id_en", referencedColumnName = "id")
    private UserProfile creatorEn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "approver_id", referencedColumnName = "id")
    private UserProfile approver;

    private LocalDateTime approveDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "approver_id_en", referencedColumnName = "id")
    private UserProfile approverEn;

    private LocalDateTime approveDateEn;

    @Enumerated(EnumType.STRING)
    private UploadLanguage uploadLanguage;

    private Long asOfYear;

    private String generateUploadFileNameTh;

    private String generateUploadFileNameEn;

    private String uploadFileNameTh;

    private String uploadFileNameEn;

    private LocalDateTime uploadDateTh;

    private LocalDateTime uploadDateEn;

    private String dataStructureFileNameEn;

    private String dataStructureFileNameTh;

    private String statusGenFilePdfEn;

    private String statusGenFilePdfTh;

    private String statusGenFileExcelEn;

    private String statusGenFileExcelTh;

    private String statusGenFileWordEn;

    private String statusGenFileWordTh;

    @Type(type = "json")
    private List<RejectReason> rejectReason;

    private Long financialAccountForm;

    @Transient
    @JsonProperty("copyFromReport")
    private Long copyFromReportId;

    @Transient
    private Boolean isApprove;

    @Transient
    private Boolean canDelete;

    private String attachFilePattern;

    private String changeBusiness;

    private String publishFileNameTh;

    private String publishFileNameEn;

    private LocalDateTime publishFileDateTh;

    private LocalDateTime publishFileDateEn;

    private LocalDateTime publishDate;

    private LocalDateTime publishDateEn;

    private LocalDateTime rollbackThDate;

    private LocalDateTime rollbackEnDate;

    @JsonIgnore
    private LocalDateTime accessDate;

    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus;

    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatusEn;

    @Type(type = "json")
    private List<RejectReason> cancelPublishReason;

    @Type(type = "json")
    private NotiPublish notiPublisher;

    private Long scpNewsIdTh;

    private Long scpNewsIdEn;

    private String reviewTh;

    private LocalDateTime reviewDateTh;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "review_th_by_user_id", referencedColumnName = "id")
    private UserProfile reviewerTh;

    private String reviewEn;

    private LocalDateTime reviewDateEn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "review_en_by_user_id", referencedColumnName = "id")
    private UserProfile reviewerEn;

    @Transient
    private boolean canSkipTh;

    @Transient
    private LocalDateTime originalApproveDate;

    @Transient
    private boolean enableGenerate;

    @Transient
    private boolean disableReport;

    @Transient
    private Boolean canApproveRollbackTh;

    @Transient
    private Boolean canApproveRollbackEn;

    @Transient
    private Boolean canEditReportTh;

    @Transient
    private Boolean canEditReportEn;

    @Transient
    private Boolean canPublishRollbackTh;

    @Transient
    private Boolean canPublishRollbackEn;

    @Transient
    private Boolean hasChild;

    @Transient
    private Long reportNumber;

    @Transient
    private String scpNewIdLanguage;

    private Long financialTemplateVersion;

    private String duplicateStatus;

    private LocalDateTime setddPublishDateTh;

    private LocalDateTime setddPublishDateEn;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "copy_from_report_id", referencedColumnName = "id")
    @JsonIgnore
    private Report copyFromReport;
}

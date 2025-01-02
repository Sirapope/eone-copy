package th.or.set.setportal.eonereport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
})
public class Report  extends AbstractReport {

//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
//    @JoinColumn(name = "copy_from_report_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Report copyFromReport;

    @OneToMany(mappedBy = "report",
            cascade = {CascadeType.ALL})
    @OrderBy("id")
    private List<ReportDetail> reportDetails = new ArrayList<>();

}

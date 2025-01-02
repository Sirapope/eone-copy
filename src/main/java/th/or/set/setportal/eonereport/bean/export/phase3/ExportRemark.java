package th.or.set.setportal.eonereport.bean.export.phase3;


import lombok.AllArgsConstructor;
import lombok.Data;
import th.or.set.setportal.eonereport.bean.RemarkData;

@AllArgsConstructor
@Data
public class ExportRemark {

    private RemarkData remark;
    private Integer sequence;
    private String label;
}

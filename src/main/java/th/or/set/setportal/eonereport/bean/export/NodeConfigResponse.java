package th.or.set.setportal.eonereport.bean.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeConfigResponse {
    private String name;
    private Long nodeGroup;
    private String descriptionTh;
    private String descriptionEn;
}

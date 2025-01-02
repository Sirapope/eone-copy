package th.or.set.setportal.eonereport.bean.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import th.or.set.setportal.eonereport.model.NodeConfig;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportPage {

    private NodeConfig nodeConfig;

    private String title;

    private Integer startPage;

    private Integer totalPage;

}

package th.or.set.setportal.eonereport.bean.export.phase3;

import lombok.Getter;
import lombok.Setter;
import th.or.set.setportal.eonereport.bean.export.NodeConfigResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RemarkSequence {

    private List<ExportRemark> exportRemark = new ArrayList<>();

    private Integer current = 1;

    private Long reportId;

    private Long reportDetailId;

    private Map<String, String> nodeGroupMap = new HashMap<>(); // for store parent node name

    private Map<Long, Boolean> nodeParentShowMap = new HashMap<>(); // for show/hide page title

    private List<NodeConfigResponse> nodeLevel1 = new ArrayList<>(); // for footer

    private String dataStructureName;
}

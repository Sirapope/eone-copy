package th.or.set.setportal.eonereport.bean.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportPageList {

    private Integer currentStartPage = 1;

    private List<ExportPage> exportPageList = new ArrayList<>();

}

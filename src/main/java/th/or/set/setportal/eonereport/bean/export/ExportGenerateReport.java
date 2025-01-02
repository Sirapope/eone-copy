package th.or.set.setportal.eonereport.bean.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportGenerateReport {

    private List<JasperPrint> jasperPrintList;

    private ExportPageList exportPageList;

}

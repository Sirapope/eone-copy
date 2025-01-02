package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import th.or.set.setportal.eonereport.bean.export.phase3.Content;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.config.Constants;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j()
@Service
public class ExportRemarkService {

    private final MessageSource messageSource;

    private final UtilService utilService;

    private final XlsUtilService xlsUtilService;

    public ExportRemarkService(MessageSource messageSource, UtilService utilService, XlsUtilService xlsUtilService) {

        this.messageSource = messageSource;
        this.utilService = utilService;
        this.xlsUtilService = xlsUtilService;
    }


    public JasperPrint exportReport(String language, Boolean changed, Integer currentStartPage, Boolean isExcel, double phase, RemarkSequence remarkSeq) {
        try {
            if (!remarkSeq.getExportRemark().isEmpty() && phase > 2) {
                Locale locale = Locale.forLanguageTag(language);
                try {
                    InputStream inputStream =  new ClassPathResource("template/Remark.jrxml").getInputStream();

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("Title", messageSource.getMessage("ldp.remark.title", null, locale));
                    parameters.put("Sequence", messageSource.getMessage("header.sequence", null, locale));
                    parameters.put("Topic", messageSource.getMessage("header.topic", null, locale));
                    parameters.put("Remark", messageSource.getMessage("header.remark", null, locale));
                    List<Content> contentList = remarkSeq.getExportRemark().stream()
                            .map(ep -> {
                                boolean isChange = locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                                        ep.getRemark().isRemarkThIsChanged() :
                                        ep.getRemark().isRemarkEnIsChanged();
                                return new Content(ep.getSequence(),
                                        utilService.checkChanged(ep.getLabel(), isChange, changed, locale),
                                        locale.equals(Locale.forLanguageTag(Constants.TH)) ?
                                                ep.getRemark().getRemarkTh() :
                                                ep.getRemark().getRemarkEn());
                            })
                            .collect(Collectors.toList());

                    JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(contentList);
                    parameters.put("ContentList", collectionDataSource);
                    parameters.put("FirstPage", currentStartPage);
                    parameters.put("IsExcel", isExcel);
                    return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            log.error("Error export remark", e);
            throw new ResourceAccessException(e.getMessage());
        }
        return null;
    }
}

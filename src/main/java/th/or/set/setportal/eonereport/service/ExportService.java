package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import th.or.set.setportal.eonereport.bean.export.*;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.config.Constants;
import th.or.set.setportal.eonereport.exception.BadRequestException;
import th.or.set.setportal.eonereport.exception.NotFoundException;
import th.or.set.setportal.eonereport.model.NodeConfig;
import th.or.set.setportal.eonereport.model.OverallBusinessCorporatePolicy;
import th.or.set.setportal.eonereport.model.Report;
import th.or.set.setportal.eonereport.model.ReportDetail;
import th.or.set.setportal.eonereport.repository.NodeConfigRepository;
import th.or.set.setportal.eonereport.repository.ReportDetailRepository;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static th.or.set.setportal.eonereport.config.Constants.*;
import static th.or.set.setportal.eonereport.service.SmdService.getEndDate;
import static th.or.set.setportal.eonereport.service.UtilService.convertToBuddhistYear;

@Slf4j()
@Service
public class ExportService {

    @Autowired
    private ExportRemarkService exportRemarkService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ReportConfigService reportConfigService;

    @Autowired
    private ReportDetailRepository reportDetailRepository;
    @Autowired
    private ExportShareholdingStructureService exportShareholdingStructureService;

    @Autowired
    private ExportBusinessAndCorporatePolicyService exportBusinessAndCorporatePolicyService;

    @Autowired
    private ExportBusinessCharacteristicsService businessCharacteristicsService;

    @Autowired
    private NodeConfigRepository nodeConfigRepository;

    @Autowired
    private XlsUtilService xlsUtilService;
    
    public ResponseEntity<ByteArrayResource> exportPreview(Report report, String name, String type, String language) throws Exception {
        String fileName;
        List<String> nodeName = Arrays.asList(name.split(","));
        if (nodeName.size() == 1) {
            fileName = nodeName.get(0) + "_" + UtilService.getDateTimeNow();
        } else {
            fileName = Constants.PREVIEW_FILE_NAME + UtilService.getDateTimeNow();
        }
        List<NodeConfig> nodeConfig = nodeConfigRepository.findByNameInAndReportPhase(nodeName, report.getPhase());
        List<NodeConfigResponse> nodeConfigResponses = getNodeLevel1Names(report, nodeConfig);
        //check node level
        List<Long> nodeIds = nodeConfig.stream().map(NodeConfig::getId).collect(Collectors.toList());
        if (nodeConfig.stream().allMatch(n -> n.getNodeLevel() == 1)) {
            List<Long> nodeLevel2Names = nodeConfigRepository.findByParentIdIn(nodeIds).stream().map(NodeConfig::getId).collect(Collectors.toList());
            //find node level 3
            nodeName = nodeConfigRepository.findByParentIdIn(nodeLevel2Names).stream().map(NodeConfig::getName).collect(Collectors.toList());
        }
        if (nodeConfig.stream().allMatch(n -> n.getNodeLevel() == 2)) {
            //find node level 3
            nodeName = nodeConfigRepository.findByParentIdIn(nodeIds).stream().map(NodeConfig::getName).collect(Collectors.toList());
        }
//        get report detail from node level 3
        List<ReportDetail> reportDetailList = reportDetailRepository.findReportDetailByReportIdAndInNodeConfigName(report.getId(), nodeName);

        if (reportDetailList.isEmpty()) {
            throw new BadRequestException("not found report detail with node name : " + name);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        RemarkSequence remarkSequence = new RemarkSequence();
        remarkSequence.setNodeLevel1(nodeConfigResponses);
        remarkSequence.setReportId(report.getId());
        switch (type) {
//            case Constants.WORD:
//                exportToDocx(generateReportPreview(reportDetailList, language, Boolean.FALSE, true, report.getPhase().doubleValue(), remarkSequence), baos);
//                return UtilService.prepareResponseFileDownload(new ByteArrayResource(baos.toByteArray()), fileName + Constants.DOCX_EXTENSION);
//            case Constants.EXCEL:
//                exportToXlsx(generateReportPreview(reportDetailList, language, Boolean.TRUE, true, report.getPhase().doubleValue(), remarkSequence), baos);
//                return UtilService.prepareResponseFileDownload(new ByteArrayResource(baos.toByteArray()), fileName + Constants.XLSX_EXTENSION);
            default:
                exportToPdf(generateReportPreview(reportDetailList, language, Boolean.FALSE, false, report.getPhase().doubleValue(), remarkSequence), baos);
                return UtilService.prepareResponseFileDownload(new ByteArrayResource(baos.toByteArray()), fileName + Constants.PDF_EXTENSION);
        }
    }

    public List<NodeConfigResponse> getNodeLevel1Names(Report report, List<NodeConfig> nodeConfig) {
        return nodeConfigRepository.findByNodeGroupInAndNodeLevel(
                nodeConfig.stream().map(NodeConfig::getNodeGroup).distinct().collect(Collectors.toList()), 1L, report.getPhase());
    }

    public List<JasperPrint> generateReport(List<ReportDetail> reportDetailList, String language, Boolean changed, boolean isExcel, Boolean isPreview, boolean isWord, double phase, RemarkSequence remarkSeq) {
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        ExportPageList exportPageList = new ExportPageList();
        Map<String, String> nodeGroupMap = new HashMap<>();
        Map<Long, Boolean> nodeTitleMap = new HashMap<>();
        setNodeGroupMap(reportDetailList, language, nodeGroupMap, nodeTitleMap);
        remarkSeq.setNodeGroupMap(nodeGroupMap);
        remarkSeq.setNodeParentShowMap(nodeTitleMap);

        for (ReportDetail r : reportDetailList) {
            JasperPrint j;
            if (!isExcel) {
                //หน้าปก
                if (r.getNodeConfig().getNodeLevel() == 1) {
                    j = xlsUtilService.generateBookmark(r, language, isPreview, reportDetailList, exportPageList.getCurrentStartPage(), true);
                    if (j != null) {
                        addTopic(exportPageList, r.getNodeConfig());
                        jasperPrintList.add(j);
                    }
                } else if (r.getNodeConfig().getNodeLevel() == 2 && !ATTACHMENT_TOPIC.equalsIgnoreCase(r.getNodeConfig().getName())) {
                    addTopic(exportPageList, r.getNodeConfig());
                }
            }
            switch (r.getNodeConfig().getName()) {
                case BUSINESS_AND_CORPORATE_POLICY:
                    j = exportBusinessAndCorporatePolicyService.exportReport(r, language, changed, exportPageList.getCurrentStartPage(), isExcel, isWord, phase, remarkSeq);
                    addExportPage(exportPageList, r.getNodeConfig(), j.getPages().size());
                    jasperPrintList.add(j);
                    break;
                case SHAREHOLDING_STRUCTURE:
                    j = exportShareholdingStructureService.exportReport(r, language, changed, exportPageList.getCurrentStartPage(), isExcel, isWord, phase, remarkSeq);
                    addExportPage(exportPageList, r.getNodeConfig(), j.getPages().size());
                    jasperPrintList.add(j);
                    break;
                case BUSINESS_CHARACTERISTICS:
                    j = businessCharacteristicsService.exportReport(r, language, changed, exportPageList.getCurrentStartPage(), isExcel, isWord, phase, remarkSeq);
                    addExportPage(exportPageList, r.getNodeConfig(), j.getPages().size());
                    jasperPrintList.add(j);
                    break;
            }
        }
        // remark
        if (phase > 2 && !remarkSeq.getExportRemark().isEmpty() && isExcel) {
            JasperPrint j = exportRemarkService.exportReport(language, changed, exportPageList.getCurrentStartPage(), isExcel, phase, remarkSeq);
            NodeConfig nodeConfig = new NodeConfig();
            nodeConfig.setDescriptionTh("remark");
            nodeConfig.setDescriptionEn("remark");
            addExportPage(exportPageList, nodeConfig, j.getPages().size());
            jasperPrintList.add(j);
        }

        //Table of Contents
        if (!isPreview) {
            jasperPrintList.add(0, generateTableContent(exportPageList, Locale.forLanguageTag(language)));
            jasperPrintList.add(0, generateCover(reportDetailList.get(0).getReport(), Locale.forLanguageTag(language)));
        }
        return jasperPrintList;
    }

    private void setNodeGroupMap(List<ReportDetail> reportDetailList, String language, Map<String, String> nodeGroupMap, Map<Long, Boolean> nodeTitleMap) {
        for (ReportDetail r : reportDetailList) {
            if (r.getNodeConfig().getNodeLevel() == 3L && r.getNodeConfig().getParentId() != null && nodeGroupMap.get(r.getNodeConfig().getName()) == null) {
                nodeConfigRepository.findById(r.getNodeConfig().getParentId())
                        .ifPresent(p -> {
                            if (p.getName().equals(ATTACHMENT_TOPIC)) {
                                nodeGroupMap.put(r.getNodeConfig().getName(), XlsUtilService.getNameByLanguage(p.getDescriptionTh(), p.getDescriptionEn(), Locale.forLanguageTag(language)));
                            } else {
                                String descriptionTh = p.getDescriptionTh();
                                if (p.getName().equals("cgsInfo") && Objects.equals(language, TH)) {
                                    descriptionTh = messageSource.getMessage("main_node2.node2.1_5", null, Locale.forLanguageTag(language));
                                }
                                nodeGroupMap.put(r.getNodeConfig().getName(),
                                        SEQUENCE_OF_NODE_LEVEL_2.get(p.getName()) + ". " + XlsUtilService.getNameByLanguage(descriptionTh, p.getDescriptionEn(), Locale.forLanguageTag(language)));
                            }
                            nodeTitleMap.put(r.getNodeConfig().getParentId(), false);
                        });
            }
        }
    }

    public JasperPrint generateCover(Report report, Locale locale) {

        try {
            InputStream inputStream = new ClassPathResource("template/Cover.jrxml").getInputStream();

            //calculate fiscalDate
            List<String> customFiscalEnd = Arrays.asList("31/01", "28/02");
            int fiscalYear = report.getAsOfYear().intValue();
            if (customFiscalEnd.contains(report.getCompany().getFiscalEnd())
                    || ("31/03".equalsIgnoreCase(report.getCompany().getFiscalEnd())
                    && reportConfigService.getCustomFiscalYearCompany().stream().anyMatch(report.getCompany().getCompanyCode()::equalsIgnoreCase))
            ) {
                fiscalYear = fiscalYear + 1;
            }
            LocalDateTime fiscalDate = getEndDate(fiscalYear, report.getCompany().getFiscalEnd());
            Long year = locale.equals(Locale.forLanguageTag(TH)) ? convertToBuddhistYear(report.getAsOfYear()) : report.getAsOfYear();

            Map<String, Object> parameters = new HashMap<String, Object>();
            UtilService.setResourceBundle(parameters, locale);

            ReportDetail businessAndCorporatePolicy = report.getReportDetails().stream()
                    .filter(r -> Constants.BUSINESS_AND_CORPORATE_POLICY.equalsIgnoreCase(r.getNodeConfig().getName()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("not found report detail name : " + Constants.BUSINESS_AND_CORPORATE_POLICY));
            OverallBusinessCorporatePolicy overallBusinessCorporatePolicy = businessAndCorporatePolicy.getOverallBusinessCorporatePolicy();

            parameters.put("Year", year.toString());
            String annualReport = locale.equals(Locale.forLanguageTag(TH)) ?
                    messageSource.getMessage("annual.report", null, locale).concat(" ").concat(year.toString()) :
                    messageSource.getMessage("annual.report", null, locale);
            parameters.put("AnnualReport", annualReport);
            parameters.put("CompanyName", locale.equals(Locale.forLanguageTag(TH)) ? overallBusinessCorporatePolicy.getNameTh().trim() : overallBusinessCorporatePolicy.getNameEn().trim());
            parameters.put("Fiscal", UtilService.getFullDate(fiscalDate, locale));

            return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
        } catch (Exception e) {
            log.error("Error export table of content", e);
            throw new ResourceAccessException(e.getMessage());
        }
    }

    private JasperPrint generateTableContent(ExportPageList exportPageList, Locale locale) {

        try {
            InputStream inputStream = new ClassPathResource("template/TableContent.jrxml").getInputStream();
            //prepare data
            List<Content> contentList = exportPageList.getExportPageList().stream()
                    .map(ep -> {
                        Content content = new Content();
                        if (ep.getTitle() != null) {
                            content.setTopic(ep.getTitle());
                        } else {
                            String topic = locale.equals(Locale.forLanguageTag(TH)) ? ep.getNodeConfig().getDescriptionTh() : ep.getNodeConfig().getDescriptionEn();
                            if (ep.getNodeConfig().getNodeLevel() == 2 && SEQUENCE_OF_NODE_LEVEL_2.get(ep.getNodeConfig().getName()) != null) {
                                content.setTopic(SEQUENCE_OF_NODE_LEVEL_2.get(ep.getNodeConfig().getName()) + ". " + topic);
                            } else {
                                content.setTopic(topic);
                            }
                        }
                        if (ep.getStartPage() != null) {
                            content.setPage(ep.getStartPage());
                        }
                        content.setIndent(ep.getNodeConfig().getNodeLevel().intValue() - 1);
                        return content;
                    })
                    .collect(Collectors.toList());

            JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(contentList);


            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("Title", messageSource.getMessage("table_content.title", null, locale));
            parameters.put("TitleContinued", messageSource.getMessage("table_content.title", null, locale) + " " + messageSource.getMessage("table_content.continued", null, locale));
            parameters.put("Page", messageSource.getMessage("table_content.page", null, locale));
            parameters.put("ContentList", collectionDataSource);

            return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
        } catch (Exception e) {
            log.error("Error export table of content", e);
            throw new ResourceAccessException(e.getMessage());
        }

    }

    private void addExportPage(ExportPageList exportPageList, NodeConfig nodeConfig, Integer totalPage) {
        Integer currentPage = exportPageList.getCurrentStartPage();
        ExportPage exportPage = new ExportPage();
        exportPage.setStartPage(exportPageList.getCurrentStartPage());
        exportPage.setTotalPage(totalPage);
        exportPage.setNodeConfig(nodeConfig);

        exportPageList.getExportPageList().add(exportPage);
        exportPageList.setCurrentStartPage(currentPage + totalPage);
    }

    private void addTopic(ExportPageList exportPageList, NodeConfig nodeConfig) {
        ExportPage exportPage = new ExportPage();
        exportPage.setNodeConfig(nodeConfig);
        exportPageList.getExportPageList().add(exportPage);
    }

    public List<JasperPrint> generateReportPreview(List<ReportDetail> reportDetailList, String language, boolean isExcel, boolean isWord, double phase, RemarkSequence remarkSeq) {
        return generateReport(reportDetailList, language, Boolean.TRUE, isExcel, Boolean.TRUE, isWord, phase, remarkSeq);
    }

    public void exportToPdf(List<JasperPrint> jasperPrintList, ByteArrayOutputStream baos) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCreatingBatchModeBookmarks(Boolean.TRUE);

        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

}

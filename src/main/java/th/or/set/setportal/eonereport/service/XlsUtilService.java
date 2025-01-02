package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import th.or.set.setportal.eonereport.bean.BODProfile;
import th.or.set.setportal.eonereport.bean.IntroductionData;
import th.or.set.setportal.eonereport.bean.RemarkData;
import th.or.set.setportal.eonereport.bean.export.CellStyleExcel;
import th.or.set.setportal.eonereport.bean.export.ExportDescriptive;
import th.or.set.setportal.eonereport.bean.export.ExportImage;
import th.or.set.setportal.eonereport.bean.export.ExportYearStructureUnit;
import th.or.set.setportal.eonereport.bean.export.phase3.ExportRemark;
import th.or.set.setportal.eonereport.bean.export.phase3.IntroductionTable;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkSequence;
import th.or.set.setportal.eonereport.bean.export.phase3.RemarkTable;
import th.or.set.setportal.eonereport.bean.response.ImageInfoResponse;
import th.or.set.setportal.eonereport.constants.IntroductionSection;
import th.or.set.setportal.eonereport.constants.RemarkSection;
import th.or.set.setportal.eonereport.exception.NotFoundException;
import th.or.set.setportal.eonereport.model.EmployeeInfo;
import th.or.set.setportal.eonereport.model.ExperienceExpertise;
import th.or.set.setportal.eonereport.model.InfoTypeConfig;
import th.or.set.setportal.eonereport.model.ReportDetail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static th.or.set.setportal.eonereport.config.Constants.*;
import static th.or.set.setportal.eonereport.service.BoardOfDirectorService.mapBoardStatus;

@Slf4j
@Service
public class XlsUtilService {

    @Autowired
    RemarkService remarkService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UtilService utilService;
    @Autowired
    private IntroductionService introductionService;

    public static XSSFFont setFontSize(Workbook workbook, Boolean isBold, short indexedColors, int fontSize) {
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setBold(isBold);
        font.setColor(indexedColors);
        return font;
    }


    public static void setFillPatterColor(XSSFFont font, CellStyle cellStyle, short color, FillPatternType fillPatternType) {
        cellStyle.setFillForegroundColor(color);
        cellStyle.setFillPattern(fillPatternType);
        cellStyle.setFont(font);
    }

    public String addRemarkSeq(String label, RemarkSequence remarkSeq, RemarkData remarkData, Locale locale) {
        label = label == null ? "" : label;
        Integer currentSeq = updateRemarkSeq(remarkSeq, label, remarkData, locale);
        if (currentSeq != null) {
            label = label.concat(" ").concat("<sup>(").concat(currentSeq.toString()).concat(")</sup>");
        }
        return label;
    }

    public JRBeanCollectionDataSource getRemarkTableByLanguage(RemarkData remarkData, Locale locale, boolean changed) {
        if (remarkData == null || isRemarkIsNullOrEmpty(remarkData, locale)) {
            return null;
        }
        RemarkTable remarkTable = new RemarkTable(getRemarkLabel(locale));
        if (TH.equals(locale.getLanguage())) {
            remarkTable.setRemark(utilService.checkChanged(Thaicut.wordwrap(remarkData.getRemarkTh(), MAX_LENGTH_REMARK), remarkData.isRemarkThIsChanged(), changed, locale));
        } else {
            remarkTable.setRemark(utilService.checkChanged(remarkData.getRemarkEn(), remarkData.isRemarkEnIsChanged(), changed, locale));
        }
        remarkTable.setLengthThaiCut(MAX_LENGTH_REMARK);
        return new JRBeanCollectionDataSource(List.of(remarkTable));
    }

    public String getRemarkLabel(Locale locale) {
        return messageSource.getMessage("ldp.remark", null, locale).concat(" :");
    }

    public String addEmployeeInfoRemarkSeq(String label, RemarkSequence remarkSeq, RemarkData remarkData, Locale locale, EmployeeInfo employeeInfo, boolean isExcel) {
        List<String> employeeInfoOptional = Arrays.asList("trainingHours", "occupationalAccidents");
        if (!isExcel || employeeInfoOptional.stream().anyMatch(e -> e.equals(employeeInfo.getInfoTypeConfig().getName())) && employeeInfo.getAsOfYear() == null && employeeInfo.getAsOfYesteryear() == null && employeeInfo.getAsOfYearBeforeYesteryear() == null) {
            return label;
        }
        return addRemarkSeq(label, remarkSeq, remarkData, locale);
    }

    public Integer updateRemarkSeq(RemarkSequence remarkSeq, String label, RemarkData remarkData, Locale locale) {
        Integer currentSeq = null;
        if (remarkData == null || isRemarkIsNullOrEmpty(remarkData, locale)) {
            return currentSeq;
        }
        currentSeq = remarkSeq.getCurrent();
        remarkSeq.getExportRemark().add(new ExportRemark(remarkData, currentSeq, label));
        remarkSeq.setCurrent(currentSeq + 1);

        return currentSeq;
    }

    public static boolean isRemarkIsNullOrEmpty(RemarkData remarkData, Locale locale) {
        if (TH.equals(locale.getLanguage())) {
            return UtilService.checkFieldIsNull(remarkData.getRemarkTh());
        }
        return UtilService.checkFieldIsNull(remarkData.getRemarkEn());
    }

    public Map<RemarkSection, RemarkData> getRemarkSectionRemarkDataMap(ReportDetail reportDetail, double phase, List<RemarkSection> remarkSections) {
        Map<RemarkSection, RemarkData> remarkSectionRemarkMap = new HashMap<>();
        if (remarkSections.isEmpty()) {
            return remarkSectionRemarkMap;
        }
        if (phase > 2) {
            remarkSectionRemarkMap = remarkService.getRemarksBySections(reportDetail, remarkSections.stream()
                            .map(RemarkSection::getValue)
                            .collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(str -> RemarkSection.getByKey(str.getSection()), str -> remarkService.getRemarkDataAndSetIsChange(reportDetail, str)));
        }
        return remarkSectionRemarkMap;
    }

    public String getRemarkByLanguageEachRow(RemarkData remarkData, Locale locale, Boolean changed) {
        if (remarkData == null || isRemarkIsNullOrEmpty(remarkData, locale)) {
            return null;
        }
        if (TH.equals(locale.getLanguage())) {
            return utilService.checkChanged(Thaicut.wordwrap(remarkData.getRemarkTh(), MAX_LENGTH_REMARK), remarkData.isRemarkThIsChanged(), changed, locale);
        } else {
            return utilService.checkChanged(remarkData.getRemarkEn(), remarkData.isRemarkEnIsChanged(), changed, locale);
        }
    }

    public String addRemarkSeq(String label, RemarkSequence remarkSeq, RemarkData remarkData, Locale locale, boolean isExcel) {
        return addRemarkSeq(label, remarkSeq, remarkData, locale);
    }


    public void prepareRemarkPdf(Locale locale, Map<String, Object> parameters,
                                 Map<RemarkSection, RemarkData> remarkSectionRemarkDataMap,
                                 Map<RemarkSection, String> parameterRemarkSection,
                                 boolean changed, boolean isWord) {
        for (Map.Entry<RemarkSection, RemarkData> remarkDataEntry : remarkSectionRemarkDataMap.entrySet()) {
            if (parameterRemarkSection.get(remarkDataEntry.getKey()) != null) {
                parameters.put(parameterRemarkSection.get(remarkDataEntry.getKey()),
                        getRemarkTableByLanguage(remarkDataEntry.getValue(), locale, changed)
                );
            }
        }
    }

    public void prepareRemarkSequencePdf(Locale locale, Map<String, Object> parameters,
                                         List<Pair<String, List<String>>> parameterRemarkSection,
                                         boolean changed, RemarkSequence remarkSequence, Boolean... isExcelWord) {
        Boolean isExcel = isExcelWord.length > 0 ? isExcelWord[0] : false;
        Boolean isWord = isExcelWord.length > 1 ? isExcelWord[1] : false;
        if (isExcel) return;
        for (Pair<String, List<String>> p : parameterRemarkSection) {
            List<RemarkTable> remarkTables = remarkSequence.getExportRemark()
                    .stream()
                    .filter(r -> {
                        return p.getValue().stream().anyMatch(i -> {
                            if (r.getRemark() != null && r.getRemark().getRemarkSection() != null) {
                                return i.equals(r.getRemark().getRemarkSection().getValue());
                            }
                            return false;
                        });
                    })
                    .map(z -> {
                        return getRemarkTable(locale, changed, z.getRemark(), z.getSequence(), isWord);
                    })
                    .collect(Collectors.toList());
            if (!remarkTables.isEmpty()) {
                remarkTables.get(0).setTitle(getRemarkLabel(locale));
                parameters.put(p.getKey(), new JRBeanCollectionDataSource(remarkTables));
            }
        }
    }

    public RemarkTable getRemarkTable(Locale locale, boolean changed, RemarkData remarkData, Integer seq,
                                      Boolean... isExcelWord) {
        String value = "";
        Boolean isWord = isExcelWord.length > 0 ? isExcelWord[0] : false;
        if (TH.equals(locale.getLanguage())) {
            value = utilService.checkChanged(Thaicut.wordwrap(remarkData.getRemarkTh(), MAX_LENGTH_REMARK, isWord),
                    remarkData.isRemarkThIsChanged(), changed, locale);
        } else {
            value = utilService.checkChanged(remarkData.getRemarkEn(), remarkData.isRemarkEnIsChanged(), changed, locale);
        }
        return new RemarkTable("<sup>(" + seq + ")</sup>", value.replaceAll("\n", "<br>"));
    }

    public String addRiskRemarkSeq(String title, RemarkData remarkData, RemarkSequence remarkSeq, Locale locale, boolean isExcel) {
        if (!isExcel) {
            return title;
        }
        Integer currentSeq = updateRiskRemarkSeq(remarkSeq, title, remarkData, locale);
        if (currentSeq != null) {
            title = title.concat("<sup>").concat(currentSeq.toString()).concat("</sup>");
        }
        return title;
    }

    private Integer updateRiskRemarkSeq(RemarkSequence remarkSeq, String title, RemarkData remarkData, Locale locale) {
        Integer currentSeq = null;
        if (remarkData == null) {
            return currentSeq;
        }
        if (!isRemarkIsNullOrEmpty(remarkData, locale)) {
            currentSeq = remarkSeq.getCurrent();
            remarkSeq.getExportRemark().add(new ExportRemark(remarkData, currentSeq, title.trim()));
            remarkSeq.setCurrent(currentSeq + 1);
        }
        return currentSeq;
    }

    public void prepareIntroductionPdf(Locale locale, Map<String, Object> parameters,
                                       Map<IntroductionSection, IntroductionData> remarkSectionRemarkDataMap,
                                       List<Pair<String, List<String>>> parameterIntroductionSection,
                                       boolean changed, Boolean... isExcelWord) {
        for (Pair<String, List<String>> p : parameterIntroductionSection) {
            List<IntroductionTable> introductionTables = remarkSectionRemarkDataMap.entrySet()
                    .stream()
                    .filter(r -> {return p.getValue().stream().anyMatch(i -> i.equals(r.getKey().getValue()));})
                    .map(z -> {
                        return getIntroductionTable(locale, changed, z.getValue(), isExcelWord);
                    })
                    .collect(Collectors.toList());
            if (!introductionTables.isEmpty()) {
                parameters.put(p.getKey(), new JRBeanCollectionDataSource(introductionTables));
            }
        }
    }

    public IntroductionTable getIntroductionTable(Locale locale, boolean changed, IntroductionData introductionData,
                                                  Boolean... isExcelWord) {
        String value = "";
        if (TH.equals(locale.getLanguage())) {
            value = utilService.checkChanged(replaceJasperHtml(introductionData.getIntroductionTh()), introductionData.isIntroductionThIsChanged(), changed, locale);
        } else {
            value = utilService.checkChanged(replaceJasperHtml(introductionData.getIntroductionEn()), introductionData.isIntroductionEnIsChanged(), changed, locale);
        }
        if (isExcelWord.length > 1 && isExcelWord[1] != null && TH.equals(locale.getLanguage())) {
            return new IntroductionTable(null, Thaicut.wordwrap(value, MAX_LENGTH_INTRODUCTION, isExcelWord[1]));
        } else {
            return new IntroductionTable(null, value);
        }
    }

    public Map<IntroductionSection, IntroductionData> getIntroductionSectionIntroductionDataMap(ReportDetail reportDetail, double phase,
                                                                                                List<IntroductionSection> introductionSections) {
        Map<IntroductionSection, IntroductionData> introductionSectionIntroductionDataMap = new HashMap<>();
        if (introductionSections.isEmpty()) {
            return introductionSectionIntroductionDataMap;
        }
        if (phase > 2) {
            introductionSectionIntroductionDataMap = introductionService.getIntroductionBySections(reportDetail, introductionSections.stream()
                            .map(IntroductionSection::getValue)
                            .collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(str -> IntroductionSection.getByKey(str.getSection()), str -> introductionService.getIntroductionDataAndSetIsChange(reportDetail, str)));
        }
        return introductionSectionIntroductionDataMap;
    }

    public static <T extends ExportYearStructureUnit> void setFirstAndLast(List<T> exportYearStructures) {
        if (exportYearStructures != null && !exportYearStructures.isEmpty()) {
            exportYearStructures.get(0).setFirst(true);
            exportYearStructures.get(exportYearStructures.size() - 1).setLast(true);
        }
    }
    public static void setFirstAndLastOfExportDescriptive(List<ExportDescriptive> exportDescriptives) {
        if (exportDescriptives != null && !exportDescriptives.isEmpty()) {
            exportDescriptives.get(0).setFirst(true);
            exportDescriptives.get(exportDescriptives.size() - 1).setLast(true);
        }
    }

    public static String replaceJasperHtml(String html) {
        if (UtilService.checkFieldIsNull(html)) {
            return null;
        }
        String indent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        for (int i = 1; i <= 8; i++) {
            String cssClass = "ql-indent-" + i;
            html = html.replaceAll("(\"" + cssClass + "\">)", getIndent(indent, i));
        }
        //
        Source htmlSource = new Source(html);
        Segment segment = new Segment(htmlSource, 0, htmlSource.length());
        List<Element> aList = segment.getAllElements(HTMLElementName.A);
        for (Element e : aList) {
            html = html.replace(e.toString(), "<span style=\"color: rgb(0 102 204);\"><u>".concat(e.toString()).concat("</u></span>"));
        }

        return html.replace("<strong", "<b")
                .replace("</strong>", "</b>")
                .replace("<em", "<i")
                .replace("</em>", "</i>")
                .replace("<p><br></p>", "<p></p>")
                .replace("<p><br/></p>", "<p></p>")
                .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    }

    private static String getIndent(String indent, int count) {
        String result = "\"\">";
        for (int i = 0; i < count; i++) {
            result = result.concat(indent);
        }
        return result;
    }

    public String checkPolicyNull(String input, Locale locale) {
        String flag = null;

        if (input != null) {
            if (Y.equalsIgnoreCase(input)) {
                flag = messageSource.getMessage("answer_have", null, locale);
            } else {
                flag = messageSource.getMessage("answer_not_have", null, locale);
            }
        }
        return flag;
    }

    public String checkPolicyNull(Boolean input, Locale locale) {
        String flag = null;

        if (input != null) {
            if (Boolean.TRUE.equals(input)) {
                flag = messageSource.getMessage("answer_have", null, locale);
            } else {
                flag = messageSource.getMessage("answer_not_have", null, locale);
            }
        }
        return flag;
    }

    public String checkPolicyNullWithAnswer(Boolean input, Locale locale, String messageY, String messageN) {
        String flag = null;

        if (input != null) {
            if (Boolean.TRUE.equals(input)) {
                flag = messageSource.getMessage(messageY, null, locale);
            } else {
                flag = messageSource.getMessage(messageN, null, locale);
            }
        }
        return flag;
    }

    public static String checkNullSetDefault(String input) {
        return input != null && !input.isEmpty() ? input : DASH;
    }

    public static String getNameByLanguage(String nameTh, String nameEn, Locale locale) {
        if (TH.equals(locale.getLanguage())) {
            return UtilService.checkFieldIsNull(nameTh) ? null : nameTh;
        }
        return UtilService.checkFieldIsNull(nameEn) ? null : nameEn;
    }

    public static String removeHtmlNewLineInTagA(String html) {
        if (html != null) {
            // find a href and replace
            Source htmlSource = new Source(html);
            Segment segment = new Segment(htmlSource, 0, htmlSource.length());
            List<Element> aList = segment.getAllElements(HTMLElementName.A);
            for (Element e : aList) {
                html = html.replace(e.toString(),
                        e.getStartTag().toString() +
                                e.getContent().toString().replace("\n", "") +
                                e.getEndTag().toString());
            }
            return  html;
        }
        return html;
    }

    public static String getHtmlByLanguage(String nameTh, String nameEn, Locale locale) {
        if (TH.equals(locale.getLanguage())) {
            return Thaicut.wordwrap(replaceJasperHtml(nameTh), MAX_LENGTH_INTRODUCTION);
        }
        return removeHtmlNewLineInTagA(replaceJasperHtml(nameEn));
    }

    public static String getHtmlByLanguage(String nameTh, String nameEn, Locale locale, Boolean isWord) {
        if (TH.equals(locale.getLanguage())) {
            return Thaicut.wordwrap(replaceJasperHtml(nameTh), MAX_LENGTH_INTRODUCTION, isWord);
        }
        return removeHtmlNewLineInTagA(replaceJasperHtml(nameEn));
    }

    public static String getHtmlByLanguage(String nameTh, String nameEn, Locale locale, int thaiCutLength) {
        if (TH.equals(locale.getLanguage())) {
            return Thaicut.wordwrap(replaceJasperHtml(nameTh), thaiCutLength);
        }
        return removeHtmlNewLineInTagA(replaceJasperHtml(nameEn));
    }

    public static String getHtmlByLanguage(String nameTh, String nameEn, Locale locale, int thaiCutLength, Boolean isWord) {
        if (TH.equals(locale.getLanguage())) {
            return Thaicut.wordwrap(replaceJasperHtml(nameTh), thaiCutLength, isWord);
        }
        return removeHtmlNewLineInTagA(replaceJasperHtml(nameEn));
    }

    public static String getHtmlByLanguage(String nameTh, String nameEn, Locale locale, int fieldWidth, int borderLeft, int borderRight, int fontSize) {
        if (TH.equals(locale.getLanguage())) {
            return Thaicut.wordwrapTextWidth(replaceJasperHtml(nameTh), fieldWidth, borderLeft, borderRight, fontSize);
        }
        return removeHtmlNewLineInTagA(replaceJasperHtml(nameEn));
    }

    public static String getUrlByLanguage(String nameTh, String nameEn, Locale locale) {
        return getUrlByLanguage(nameTh, nameEn, locale, MAX_LENGTH_URL_QUESTION);
    }

    public static String getUrlByLanguage(String nameTh, String nameEn, Locale locale, int thaiCutLength) {
        String name = getNameByLanguage(nameTh, nameEn, locale);
        if (UtilService.checkFieldIsNull(name)) {
            return null;
        }
        String wrappedName = Thaicut.wordwrap(name, thaiCutLength);
        return UtilService.validateURLFormat(name) ? "<a href='" + UtilService.addHttpsAndEncodeForHyperlink(name) + "'>" + name + "</a>" : wrappedName;
    }

    public static ExportImage getExportImage(Locale locale, ImageInfoResponse imageInfoResponse, String reportPath) {
        ExportImage exportImage = new ExportImage();
        if (imageInfoResponse == null) {
            return exportImage;
        }

        String fileName = getNameByLanguage(imageInfoResponse.getGenerateFileNameTh(), imageInfoResponse.getGenerateFileNameEn(), locale);

        if (UtilService.checkFieldIsNull(fileName)) {
            return exportImage;
        }

        exportImage.setImage(reportPath.concat(fileName));
        exportImage.setDescription(getNameByLanguage(imageInfoResponse.getDescriptionTh(), imageInfoResponse.getDescriptionEn(), locale));
        return exportImage;
    }

    public void generatePageTitle(ReportDetail reportDetail, Locale locale, Map<String, Object> parameters,
                                  RemarkSequence remarkSeq, Boolean... isExcelWord) {
        if (remarkSeq.getNodeGroupMap().get(reportDetail.getNodeConfig().getName()) != null
                && reportDetail.getNodeConfig().getSequence() == 1L) {
            remarkSeq.getNodeParentShowMap().put(reportDetail.getNodeConfig().getParentId(), true); // mark as title already set
            String title = remarkSeq.getNodeGroupMap().get(reportDetail.getNodeConfig().getName());
            if (TH.equalsIgnoreCase(locale.getLanguage())) {
                boolean isWord = isExcelWord.length > 1 ? isExcelWord[1] : false;
                title = Thaicut.wordwrap(title, 125, isWord, "&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            parameters.put("Title", new JRBeanCollectionDataSource(List.of(new ExportDescriptive(title, null))));
        }
        // for footer
        remarkSeq.getNodeLevel1().stream()
                .filter(n -> n.getNodeGroup() == reportDetail.getNodeConfig().getNodeGroup())
                .findFirst().ifPresent(n -> {
                    String title = getNameByLanguage(n.getDescriptionTh(), n.getDescriptionEn(), locale);
                    parameters.put("ParentTitle", title);
                });
    }

    public static String formatDateByPeriod(String periodType, LocalDateTime startPeriod, LocalDateTime endPeriod, LocalDateTime period, Locale locale) {
        if (TH.equals(locale.getLanguage())) {
            if (periodType.equals("mm")) {
                return period != null ? period.format(DateTimeFormatter.ofPattern("MMM ", locale)) + UtilService.convertToBuddhistYear(period.getYear()) : DASH;
            } else if (periodType.equals("mm/yyyy")) {
                return (startPeriod != null ? startPeriod.format(DateTimeFormatter.ofPattern("MMM", locale)) + " " + UtilService.convertToBuddhistYear(startPeriod.getYear()) : DASH) + " - " +
                        (endPeriod != null ? endPeriod.format(DateTimeFormatter.ofPattern("MMM", locale)) + " " + UtilService.convertToBuddhistYear(endPeriod.getYear()) : DASH);
            } else {
                return (startPeriod != null ? startPeriod.format(DateTimeFormatter.ofPattern("dd MMM", locale)) + " " + UtilService.convertToBuddhistYear(startPeriod.getYear()) : DASH) + " - " +
                        (endPeriod != null ? endPeriod.format(DateTimeFormatter.ofPattern("dd MMM", locale)) + " " + UtilService.convertToBuddhistYear(endPeriod.getYear()) : DASH);
            }
        }
        if (periodType.equals("mm")) {
            return period != null ? period.format(DateTimeFormatter.ofPattern("MMM yyyy")) : DASH;
        } else if (periodType.equals("mm/yyyy")) {
            return (startPeriod != null ? startPeriod.format(DateTimeFormatter.ofPattern("MMM yyyy")) : DASH) + " - " +
                    (endPeriod != null ? endPeriod.format(DateTimeFormatter.ofPattern("MMM yyyy")) : DASH);
        } else {
            return (startPeriod != null ? startPeriod.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) : DASH) + " - " +
                    (endPeriod != null ? endPeriod.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) : DASH);
        }
    }

    public static String checkNullSetDefaultDash(String input) {
        return input != null ? input : DASH;
    }
    public static String checkNullSetDefaultBlank(String input) {
        return input != null ? input : "";
    }
    public static String checkNullSetDefaultNA(BigDecimal input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : N_A;
    }

    public static String checkNullSetDefaultBlank(BigDecimal input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : "";
    }

    public static String checkNullSetDefaultNA(Long input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : N_A;
    }

    public static String checkNullSetDefaultDash(Long input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : DASH;
    }

    public static String checkNullSetDefaultBlank(Long input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : "";
    }

    public static String checkNullSetDefaultNull(Long input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : null;
    }

    public static String checkNullSetDefaultNull(BigDecimal input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : null;
    }

    public static String checkNullSetDefaultNull(Long input) {
        return input != null ? input.toString() : null;
    }

    public static String checkNullEmplySetDefaultNull(String input) {
        return (input != null && !input.isEmpty()) ? input : null;
    }

    public static String checkNullSetDefaultDash(Long input) {
        return input != null ? input.toString() : DASH;
    }

    public static String checkNullSetDefault(BigDecimal input, DecimalFormat df) {
        return input != null ? df.format(input) : N_A;
    }

    public static String checkNullSetEmptyString(Object input) {
        return input != null ? String.valueOf(input) : "";
    }

    public static String formatDate(String periodType, LocalDateTime dateTime, Locale locale) {
        if (dateTime == null) {
            return DASH;
        }
        if (TH.equals(locale.getLanguage())) {
            if (periodType.equals("mm")) {
                return dateTime.format(DateTimeFormatter.ofPattern("MMM ", locale)) + UtilService.convertToBuddhistYear(dateTime.getYear());
            } else if (periodType.equals("mm/yyyy")) {
                return dateTime.format(DateTimeFormatter.ofPattern("MMM", locale)) + " " + UtilService.convertToBuddhistYear(dateTime.getYear());
            } else {
                return dateTime.format(DateTimeFormatter.ofPattern("dd MMM", locale)) + " " + UtilService.convertToBuddhistYear(dateTime.getYear());
            }
        }
        if (periodType.equals("mm")) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        } else if (periodType.equals("mm/yyyy")) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    public static String getTitleTh(String otherTitle, InfoTypeConfig title) {
        if (title != null) {
            if (TITLE_OTHER.equalsIgnoreCase(title.getName())) {
                return checkNullSetDefaultDash(otherTitle);
            } else {
                return title.getNameTh();
            }
        } else {
            return DASH;
        }
    }

    public static String getTitleEn(String otherTitle, InfoTypeConfig title) {
        if (title != null) {
            if (TITLE_OTHER.equalsIgnoreCase(title.getName())) {
                return checkNullSetDefaultDash(otherTitle);
            } else {
                return title.getNameEn();
            }
        } else {
            return DASH;
        }
    }


    public static String getBoardHistoryName(BODProfile bodProfile, Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append(XlsUtilService.checkNullSetDefaultDash(locale.equals(Locale.forLanguageTag(TH))
                        ? getTitleTh(bodProfile.getOtherTitleTh(), bodProfile.getTitleIdTh())
                        : getTitleEn(bodProfile.getOtherTitleEn(), bodProfile.getTitleIdEn())))
                .append(' ')
                .append(XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(bodProfile.getFirstNameTh(), bodProfile.getFirstNameEn(), locale)))
                .append(' ')
                .append(XlsUtilService.checkNullSetDefaultDash(getNameByLanguage(bodProfile.getLastNameTh(), bodProfile.getLastNameEn(), locale)));

        return sb.toString();
    }

    public static String getExperienceExpertise(List<ExperienceExpertise> experienceExpertiseList, Locale locale) {
        StringBuilder sb = new StringBuilder();
        experienceExpertiseList.forEach(e -> {
            if (locale.equals(Locale.forLanguageTag(TH))) {
                sb.append(e.getInfoTypeConfig().getNameTh());
            } else {
                sb.append(e.getInfoTypeConfig().getNameEn());

            }
            sb.append(", ");
        });
        return StringUtils.substring(sb.toString(), 0, sb.toString().length() - 2);
    }

    public static String getInfoTypeConfig(InfoTypeConfig infoTypeConfig, Locale locale) {
        return infoTypeConfig == null ? DASH : (locale.equals(Locale.forLanguageTag(TH)) ? infoTypeConfig.getNameTh() : infoTypeConfig.getNameEn());
    }

    public String getPositionDirector(InfoTypeConfig position, InfoTypeConfig boardStatus, String independent, Locale locale) {
        StringBuilder sb = new StringBuilder();
        String positionName = DASH;
        if (position != null) {
                positionName = XlsUtilService.getNameByLanguage(position.getNameTh(), position.getNameEn(), locale);
        }
        sb.append("(").append(positionName);

        if (boardStatus != null) {
            if (Y.equalsIgnoreCase(independent)) {
                sb.append(", " + messageSource.getMessage("independent", null, locale));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public String getBoardStatus(InfoTypeConfig boardStatus, String independent, Locale locale) {
        StringBuilder sb = new StringBuilder();
        if (boardStatus != null) {
            sb.append("(");
            sb.append(Y.equals(mapBoardStatus(boardStatus)) ?
                        messageSource.getMessage("executive_directors", null, locale)
                       : messageSource.getMessage("non_executive_directors", null, locale));
            if (Y.equalsIgnoreCase(independent)) {
                sb.append(", ").append(messageSource.getMessage("independent", null, locale));
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public String getGender(String gender, Locale locale) {
        if (gender == null) {
            return messageSource.getMessage("gender", null, locale) + ": - ";
        }
        if (MALE.equalsIgnoreCase(gender)) {
            return messageSource.getMessage("gender", null, locale) + ": " + messageSource.getMessage("male", null, locale);
        } else {
            return messageSource.getMessage("gender", null, locale) + ": " + messageSource.getMessage("female", null, locale);
        }
    }

    public JasperPrint generateBookmark(ReportDetail reportDetail, String language, Boolean isPreview,
                                        List<ReportDetail> reportDetailList, Integer currentStartPage, boolean showParentTitle) {
        if (!isPreview) {
            try {
                ReportDetail parentReportDetail = reportDetail;
                if (reportDetail.getNodeConfig().getParentId() != null ) {
                    parentReportDetail = reportDetailList.stream()
                            .filter(r -> r.getNodeConfig().getId().equals(reportDetail.getNodeConfig().getParentId()))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException("not found parent report detail"));
                }
                InputStream inputStream = new ClassPathResource("template/Bookmark.jrxml").getInputStream();
                Map<String, Object> parameters = new HashMap<String, Object>();

                parameters.put("Title", XlsUtilService.getNameByLanguage(reportDetail.getNodeConfig().getDescriptionTh(), reportDetail.getNodeConfig().getDescriptionEn(), Locale.forLanguageTag(language)));
                parameters.put("FirstPage", currentStartPage);
                parameters.put("IsExcel", false);
                parameters.put("ParentTitle", showParentTitle ?
                        XlsUtilService.getNameByLanguage(parentReportDetail.getNodeConfig().getDescriptionTh(),
                                parentReportDetail.getNodeConfig().getDescriptionEn(), Locale.forLanguageTag(language))
                        : null);

                return JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), parameters, new JREmptyDataSource());
            } catch (Exception e) {
                log.error("Error export table of content", e);
                throw new ResourceAccessException(e.getMessage());
            }
        }
        return null;
    }
}

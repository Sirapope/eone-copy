package th.or.set.setportal.eonereport.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import net.htmlparser.jericho.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.util.Pair;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.imgscalr.Scalr;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import th.or.set.setportal.eonereport.bean.ChangeResponse;
import th.or.set.setportal.eonereport.bean.InfoLabel;
import th.or.set.setportal.eonereport.bean.esg.ESGCorporateGovernanceStructureResponse;
import th.or.set.setportal.eonereport.bean.esg.ESGInfoResponse;
import th.or.set.setportal.eonereport.bean.request.IssueDetailRequest;
import th.or.set.setportal.eonereport.bean.response.*;
import th.or.set.setportal.eonereport.bean.setlink.DocFileName;
import th.or.set.setportal.eonereport.config.Constants;
import th.or.set.setportal.eonereport.constants.ChangeResponseType;
import th.or.set.setportal.eonereport.constants.InfoCategory;
import th.or.set.setportal.eonereport.constants.StatusGenFile;
import th.or.set.setportal.eonereport.constants.UploadLanguage;
import th.or.set.setportal.eonereport.exception.BadRequestException;
import th.or.set.setportal.eonereport.exception.NotFoundException;
import th.or.set.setportal.eonereport.exception.ResourceLockedException;
import th.or.set.setportal.eonereport.model.Report;
import th.or.set.setportal.eonereport.model.ReportDetail;
import th.or.set.setportal.eonereport.repository.InfoTypeConfigRepository;
import th.or.set.setportal.eonereport.repository.ReportDetailRepository;
import th.or.set.setportal.eonereport.repository.UserProfileRepository;
import th.or.set.setportal.eonereport.service.esg.dto.InfoTypeConfigDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static th.or.set.setportal.eonereport.config.Constants.*;

@Slf4j
@Service
public class UtilService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ReportDetailRepository reportDetailRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private InfoTypeConfigRepository infoTypeConfigRepository;

    @Autowired
    @Lazy
    private ReportService reportService;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    public static final String HTTPS_PROTOCOL = "https://";
    public static final String HTTP_PROTOCOL = "http://";
    public static final String IS_CHANGED_SUFFIX = "IsChanged";
    public static final String CHANGED_FIELD = "changed";

    public static ResponseEntity<ByteArrayResource> prepareResponseFileDownload(ByteArrayResource resource, String fileName) {
        if (!ObjectUtils.isEmpty(resource)) {
            return ResponseEntity.ok()
                    .headers(createAttachmentHeader(fileName))
                    .contentLength(resource.contentLength())
                    .body(resource);
        }
        return ResponseEntity.noContent().build();
    }

    public static HttpHeaders createAttachmentHeader(String savedFileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + savedFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }

    public static String getDateTimeNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return localDateTime.format(formatter);
    }

    public static Long getUserId(Authentication authentication) {
        return (Long) authentication.getPrincipal();
    }
    public static boolean checkStringIsBlank(Object value) {
        if (value instanceof String) {
            String str = (String) value;
            return str.isBlank() || removeHtmlTag(str).isBlank();
        }
        return false;
    }

    public static boolean checkFieldIsNull(Object obj) {
        if (obj == null) {
            return true;
        } else {
            return checkStringIsBlank(obj);
        }
    }

    public UserProfile checkCurrentUserByNodeLevel4(ReportDetail reportDetail) {
        UserProfile userProfile = userProfileRepository.findByUserScpId(Long.valueOf(MDC.get("UserId")));
        ReportDetail parentReportDetail = reportDetailRepository.findByReportAndParentId(reportDetail.getReport().getId(),
                reportDetail.getNodeConfig().getParentId());

        if (parentReportDetail.getCurrentUser() == null
                || parentReportDetail.getCurrentUser().getId().longValue() != userProfile.getId().longValue()) {
            throw new ResourceLockedException("current user not match");
        }
        return userProfile;
    }

    public UserProfile checkCurrentUserByNodeLevel3(ReportDetail reportDetail) {
        UserProfile userProfile = userProfileRepository.findByUserScpId(Long.valueOf(MDC.get("UserId")));

        if (reportDetail.getCurrentUser() == null
                || reportDetail.getCurrentUser().getId().longValue() != userProfile.getId().longValue()) {
            throw new ResourceLockedException("current user not match");
        }
        return userProfile;
    }


    public static Long convertToBuddhistYear(Long year) {
        return year == null ? null :  year + 543;
    }

    public static int convertToBuddhistYear(int year) {
        return year + 543;
    }

    public static Long convertToYesteryear(Long year) {
        return year == null ? null : year - ONE;
    }

    public static Long convertToYearBeforeYesteryear(Long year) {
        return year == null ? null : year - TWO;
    }
    public static Long convertToTwoYearBeforeYesteryear(Long year) {
        return year - THREE;
    }

    public static Long convertToThreeYearBeforeYesteryear(Long year) {
        return year - FOUR;
    }

    public static String addHttpsAndEncodeForHyperlink(String url) {
        if (url != null) {
            if (Pattern.compile("[\\u0E00-\\u0E7F\\s]+").matcher(url).find()) {
                url = url.replace("%20", " ");
                url = UriUtils.encodePath(url, "UTF-8");
                url = url.replace("%3F", "?");
            }
            return url.startsWith(HTTPS_PROTOCOL) || url.startsWith(HTTP_PROTOCOL) || url.isBlank() ? url : HTTPS_PROTOCOL.concat(url);
        }
        return null;
    }

    public static <T> void checkIsChanged(Class<T> type, T approvedObject, T object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                Method m = pd.getReadMethod();
                Object o1 = m.invoke(approvedObject);
                Object o2 = m.invoke(object);
                checkIsChangedField(pd, type, o1, o2, object);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    private static <T> void checkIsChangedField(PropertyDescriptor pd, Class<T> type, Object o1, Object o2, T object) throws NoSuchFieldException, IllegalAccessException {
        if (!pd.getName().contains(IS_CHANGED_SUFFIX) && !checkEqual(pd, o1, o2)) {
            Field field = type.getDeclaredField(pd.getName().concat(IS_CHANGED_SUFFIX));
            field.setAccessible(true);
            field.set(object, true);
        }
    }

    public static <T> boolean checkChangeWithSelectedField(Class<T> type, List<String> selectedField, T approvedObject, T object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            for (PropertyDescriptor pd : Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(x -> selectedField.contains(x.getName()))
                    .collect(Collectors.toList())) {
                Method m = pd.getReadMethod();
                Object o1 = m.invoke(approvedObject);
                Object o2 = m.invoke(object);
                if (!checkEqual(pd, o1, o2)) {
                    return true;
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public static Boolean checkEqual(PropertyDescriptor pd, Object o1, Object o2) {
        if (pd.getPropertyType().equals(String.class)) {
            if ((o1 == null && o2 == null) || (String.valueOf(o1).isEmpty() && o2 == null) || (String.valueOf(o2).isEmpty() && o1 == null)) {
                return true;
            }
            return String.valueOf(o1).trim().equals(String.valueOf(o2).trim());
        }

        if (o1 == null || o2 == null) {
            return Objects.equals(o1, o2);
        }

        if (pd.getPropertyType().equals(LocalDateTime.class)) {
            LocalDateTime l1 = (LocalDateTime) o1;
            LocalDateTime l2 = (LocalDateTime) o2;
            return l1.toLocalDate().equals(l2.toLocalDate());
        } else if (pd.getPropertyType().equals(BigDecimal.class)) {
            BigDecimal b1 = (BigDecimal) o1;
            BigDecimal b2 = (BigDecimal) o2;
            return b1.setScale(10, RoundingMode.DOWN).equals(b2.setScale(10, RoundingMode.DOWN));
        } else {
            return Objects.equals(o1, o2);
        }
    }

    public String checkChanged(String question, Boolean isChanged, Boolean changed, Locale locale) {
        if (changed && question != null) {
            if (isChanged) {
                return question.concat(" ").concat(messageSource.getMessage("change", null, locale));
            } else {
                return question;
            }
        } else {
            return question;
        }
    }

    public static void setResourceBundle(Map<String, Object> parameters, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/messages", locale);
        parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
    }

    public File downloadFile(Report report, String year, String fileName) {
        String reportPath = getReportPath(report, year);
        File file = new File(reportPath, FilenameUtils.getName(fileName));
        if (file.length() == 0) {
            log.error("error download file path : {} {}", reportPath, fileName);
            throw new NotFoundException("file not found");
        }
        return file;
    }

    public static String getReportPath(ReportDetail reportDetail, String year) {
        String company = String.format("%04d", reportDetail.getReport().getCompany().getCompanySmdId());
        // if report phase less than 4, download file from ldp report path
        if (reportDetail.getReport().getPhase().doubleValue() < 4) {
            return LDP_BASE_PATH + company + SLASH + year + SLASH;
        }
        return BASE_PATH + company + SLASH + year + SLASH;
    }

    public static String getReportPath(ReportDetail reportDetail) {
        String year = reportDetail.getReport().getAsOfYear().toString();
        return getReportPath(reportDetail, year);
    }

    public static String getReportPath(Report report, String year) {
        String company = String.format("%04d", report.getCompany().getCompanySmdId());
        // if report phase less than 4, download file from ldp report path
        if (report.getPhase().doubleValue() < 4) {
            return LDP_BASE_PATH + company + SLASH + year + SLASH;
        }
        return BASE_PATH + company + SLASH + year + SLASH;
    }

    public static String getReportPath(Report report) {
        String year = report.getAsOfYear().toString();
        return getReportPath(report, year);
    }


    private static void addFileToZipFile(ZipOutputStream zipOut, File fileToZip, String fileName) {
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static String getDate(String[] thaiMonths, String[] engMonths, LocalDateTime startDate, Locale locale) {
        if (startDate != null) {
            int month = startDate.getMonthValue() - 1;
            return String.format("%s %s %s", startDate.getDayOfMonth()
                    , locale.equals(Locale.forLanguageTag(TH)) ? thaiMonths[month] : engMonths[month]
                    , locale.equals(Locale.forLanguageTag(TH)) ? convertToBuddhistYear(startDate.getYear()) : startDate.getYear());
        }
        return DASH;
    }

    public static String getMonthYear(LocalDate startDate, Locale locale) {
        if (startDate != null) {
            int month = startDate.getMonthValue() - 1;
            return String.format("%s %s", locale.equals(Locale.forLanguageTag(TH)) ? THAI_MONTHS[month] : ENG_MONTHS[month]
                    , locale.equals(Locale.forLanguageTag(TH)) ? convertToBuddhistYear(startDate.getYear()) : startDate.getYear());
        }
        return DASH;
    }

    public static String getFullDate(LocalDateTime startDate, Locale locale) {
        return getDate(FULL_THAI_MONTHS, FULL_ENG_MONTHS, startDate, locale);
    }
    public static void addYearStructure(Map<String, Object> parameters, Long asOfYear, Locale locale) {
        parameters.put("Year1", String.valueOf(locale.equals(Locale.forLanguageTag(TH)) ? convertToBuddhistYear(asOfYear) : asOfYear));
        parameters.put("Year2", String.valueOf(locale.equals(Locale.forLanguageTag(TH)) ? convertToYesteryear(convertToBuddhistYear(asOfYear)) : convertToYesteryear(asOfYear)));
        parameters.put("Year3", String.valueOf(locale.equals(Locale.forLanguageTag(TH)) ? convertToYearBeforeYesteryear(convertToBuddhistYear(asOfYear)) : convertToYearBeforeYesteryear(asOfYear)));
        parameters.put("Year4", String.valueOf(locale.equals(Locale.forLanguageTag(TH)) ?
                convertToTwoYearBeforeYesteryear(convertToBuddhistYear(asOfYear)) :
                convertToTwoYearBeforeYesteryear(asOfYear)));
        parameters.put("Year5", String.valueOf(locale.equals(Locale.forLanguageTag(TH)) ?
                convertToThreeYearBeforeYesteryear(convertToBuddhistYear(asOfYear)) : convertToThreeYearBeforeYesteryear(asOfYear)));
    }

    public static boolean checkInputNotNull(List<Object> inputs) {
        for (Object input : inputs) {
            if (input == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateURLFormat(String url) {
        if (url != null && !url.isBlank()) {
            url = url.trim();
            String domainName = url.replaceAll("http(s)?://|/.*", "");
            return domainName.matches(URL_REGEX);
        } else {
            return true;
        }
    }
    public static InputStream getTemplate(Boolean isExcel, Boolean isWord, double phase, String fileName) throws IOException {
        String phaseTemplate = Double.toString(phase).replace(".", "_");
        switch (Double.toString(phase)) {
            case "1.0":
                return isWord || isExcel ? new ClassPathResource(String.format("template/%s.jrxml", fileName)).getInputStream()
                        : new ClassPathResource(String.format("template/%sPDF.jrxml", fileName)).getInputStream();
            case "1.5":
                return isWord || isExcel ? new ClassPathResource(String.format("template/%s/%s%s.jrxml", phaseTemplate, fileName, phaseTemplate)).getInputStream()
                        : new ClassPathResource(String.format("template/%s/%sPDF%s.jrxml", phaseTemplate, fileName, phaseTemplate)).getInputStream();
            default:
                //use template for word and excel
                return isExcel ? new ClassPathResource(String.format("template/%s/%sPDF%s.jrxml", phaseTemplate, fileName, phaseTemplate)).getInputStream()
                        : new ClassPathResource(String.format("template/%s/%sPDF%s.jrxml", phaseTemplate, fileName, phaseTemplate)).getInputStream();
        }
////        FOR RUN LOCAL
//        switch (Double.toString(phase)) {
//            case "1.0":
//                return isWord || isExcel ? new FileInputStream(String.format("src/main/resources/template/%s" +
//                        ".jrxml", fileName))
//                        : new FileInputStream(String.format("src/main/resources/template/%sPDF.jrxml", fileName));
//            case "1.5":
//                return isWord || isExcel ? new FileInputStream(String.format("src/main/resources/template/%s/%s%s.jrxml", phaseTemplate, fileName, phaseTemplate))
//                        : new FileInputStream(String.format("src/main/resources/template/%s/%sPDF%s.jrxml", phaseTemplate, fileName, phaseTemplate));
//            default:
//                return isExcel ? new FileInputStream(String.format("src/main/resources/template/%s/%s%s.jrxml", phaseTemplate, fileName, phaseTemplate))
//                        : new FileInputStream(String.format("src/main/resources/template/%s/%sPDF%s.jrxml", phaseTemplate, fileName, phaseTemplate));
//        }
    }

    public static String getTemplatePath(double phase) {
        String phaseTemplate = Double.toString(phase).replace(".", "_");
        File reportDir = new File("template/".concat(phaseTemplate)); //relative path
        return reportDir.getAbsolutePath() + File.separator; //Convert to absolute

////      FOR RUN LOCAL
//        String phaseTemplate = Double.toString(phase).replace(".", "_");
//        File reportDir = new File("src/main/resources/template/".concat(phaseTemplate)); //relative path
//        return reportDir.getAbsolutePath() + File.separator; //Convert to absolute
    }

    public static boolean checkMatchRemarkThRegex(String input) {
        return Pattern.compile(REMARK_TH_REGEX).matcher(input).find();
    }

    public static boolean checkMatchRemarkEnRegex(String input) {
        if (foundAnyThaiCharacter(input)) {
            return false;
        }
        return Pattern.compile(REMARK_EN_REGEX).matcher(input).find();
    }

    private static boolean foundAnyThaiCharacter(String input) {
        // if any TH found
        if (Pattern.compile("[\u0E01-\u0E5B]+").matcher(input.replace("฿", "")).find()) {
            return true;
        }
        return false;
    }

    public static boolean checkMatchRemarkThEmojiRegex(String input) {
        return Pattern.compile(COMBINED_EMOJIS_AND_REMARK_TH_REGEX).matcher(input).find();
    }

    public static boolean checkMatchRemarkEnEmojiRegex(String input) {
        // if any TH found
        if (foundAnyThaiCharacter(input)) {
            return false;
        }
        return Pattern.compile(COMBINED_EMOJIS_AND_REMARK_EN_REGEX).matcher(input).find();
    }

    public static String removeHtmlTag(String html) {
        if (html != null) {
            // find a href and replace
            Source htmlSource = new Source(html);
            Segment segment = new Segment(htmlSource, 0, htmlSource.length());
            List<Element> aList = segment.getAllElements(HTMLElementName.A);
            for (Element e : aList) {
                html = html.replace(e.toString(), "<a>" + e.getAttributeValue("href") + "</a>");
            }

            htmlSource = new Source(html);
            segment = new Segment(htmlSource, 0, htmlSource.length());
            Renderer htmlRender = new Renderer(segment).setIncludeHyperlinkURLs(false);
            htmlRender.setMaxLineLength(0); // Disable line wrapping
            return htmlRender.toString();
        }
        return null;
    }

    public static boolean validateEn(List<String> fields) {
        for (String field : fields) {
            if (field != null && (field.isBlank() || field.isEmpty())){
                continue;
            }
            if (field != null && !UtilService.checkMatchRemarkEnEmojiRegex(field)) {
                return false;
            }
        }
        return true;
    }

    public static String checkNullSetDefaultDash(BigDecimal input, DecimalFormat decimalFormat) {
        return input != null ? decimalFormat.format(input) : DASH;
    }

    public static String checkNullSetDefaultDash(String input) {
        return !checkFieldIsNull(input) ? input : DASH;
    }


}

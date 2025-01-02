package th.or.set.setportal.eonereport.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import th.or.set.setportal.eonereport.model.Report;
import th.or.set.setportal.eonereport.service.ExportService;
import th.or.set.setportal.eonereport.service.UserProfileService;
@Slf4j
@RestController
@RequestMapping("download")
public class DownloadController {

    private final UserProfileService userProfileService;

    private final ExportService exportService;

    public DownloadController(UserProfileService userProfileService, ExportService exportService) {
        this.userProfileService = userProfileService;
        this.exportService = exportService;
    }

    @ApiOperation(value = "Download Preview By Node Name")
    @GetMapping(path = "{reportId}/preview")
    public ResponseEntity<ByteArrayResource> downloadPreview(@PathVariable(name = "reportId") Long reportId,
                                                             @ApiParam(value = "list of nodeName separate by ,") @RequestParam(name = "name") String name,
                                                             @ApiParam(value = "value pdf / word / excel") @RequestParam(name = "type", defaultValue = "pdf") String type,
                                                             @ApiParam(value = "value th / en") @RequestParam(name = "language", defaultValue = "th") String language) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Report report = userProfileService.getReportByCompany(auth, reportId);
        return exportService.exportPreview(report, name, type, language);
    }
}

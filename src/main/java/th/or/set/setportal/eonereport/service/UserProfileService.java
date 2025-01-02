package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import th.or.set.setportal.eonereport.exception.NotFoundException;
import th.or.set.setportal.eonereport.exception.UnAuthorizedException;
import th.or.set.setportal.eonereport.exception.UnAuthorizedToReportException;
import th.or.set.setportal.eonereport.model.*;
import th.or.set.setportal.eonereport.repository.*;

@Slf4j()
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ReportRepository reportRepository;

    public Report getReportByCompany(Authentication auth, Long reportId) {
        UserProfile userProfile = checkAuthUserInSystem(auth);
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report != null) {
            if (userProfile.getCompany().getId() != null && !userProfile.getCompany().getId().equals(report.getCompany().getId())
            ) {
                log.error("unauthorized to report : {}", reportId);
                throw new UnAuthorizedToReportException();
            }
            return report;
        } else {
            log.error("not found report");
            throw new NotFoundException("not found report");
        }
    }

    public UserProfile checkAuthUserInSystem(Authentication auth) {
        UserProfile userProfile = userProfileRepository.findByUserScpId(UtilService.getUserId(auth));
        if (userProfile == null) {
            throw new UnAuthorizedException("user profile not found");
        }
        return userProfile;
    }

}

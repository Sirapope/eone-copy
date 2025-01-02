package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import th.or.set.setportal.eonereport.repository.ReportConfigRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static th.or.set.setportal.eonereport.config.Constants.*;


@Slf4j()
@Service
public class ReportConfigService {

    private final ReportConfigRepository reportConfigRepository;

    public ReportConfigService(ReportConfigRepository reportConfigRepository) {
        this.reportConfigRepository = reportConfigRepository;
    }

    public List<String> getCustomFiscalYearCompany() {
        return Arrays.asList(reportConfigRepository.findByConfigKey(CUSTOM_FISCAL_YEAR_COMPANY_KEY).getConfigValue().split(","));
    }

}

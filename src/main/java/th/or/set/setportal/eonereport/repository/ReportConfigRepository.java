package th.or.set.setportal.eonereport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.or.set.setportal.eonereport.model.ReportConfig;

@Repository
public interface ReportConfigRepository extends JpaRepository<ReportConfig, Long> {

    ReportConfig findByConfigKey(String configKey);

}

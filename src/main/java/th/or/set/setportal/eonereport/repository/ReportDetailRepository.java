package th.or.set.setportal.eonereport.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import th.or.set.setportal.eonereport.model.Report;
import th.or.set.setportal.eonereport.model.ReportDetail;
import th.or.set.setportal.eonereport.model.ReportDetailNoRelation;
import th.or.set.setportal.eonereport.model.UserProfile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, Long> {

    List<ReportDetail> findByReport(Report report);

    @Query(value = "SELECT * FROM report_detail rd where rd.report_id = ?1", nativeQuery = true)
    List<ReportDetailNoRelation> findByReportId(Long reportId);

    List<ReportDetail> findByCurrentUser(UserProfile userProfile);

    List<ReportDetail> findByCurrentUserNotNull();

    ReportDetail findByCurrentUserAndReport(UserProfile userProfile, Report report);

    @Query(value = "SELECT * FROM report_detail rd where rd.report_id = ?1 and rd.node_config_id = ?2", nativeQuery = true)
    ReportDetail findByReportAndParentId(Long reportId, Long parentId);

    @Query(value = "SELECT rd.report_id FROM report_detail rd where rd.id = ?1", nativeQuery = true)
    Long findReportIdByReportDetailId(Long reportDetailId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE report_detail rd SET rd.current_user_id = null WHERE rd.current_user_id is not null", nativeQuery = true)
    void updateCurrentUserNull();

    @Query(value = "SELECT r FROM ReportDetail r" +
            "    WHERE r.report.id = ?1")
    @EntityGraph(attributePaths = {"nodeConfig"
            ,"overallBusinessCorporatePolicy"
            ,"productInfo"
            ,"investmentRisk"
            ,"cgOverviewPolicy"
            ,"directorPolicy"
            ,"codeOfConduct"
            ,"boardOfDirector"
            ,"executive"
            ,"employee"
            ,"subCommitteeRole"
            ,"supervisor"
            ,"directorPerformanceSummary"
            ,"environmentalSustainabilityMgmt"
            ,"environmentalPerformance"
            ,"socialSustainabilityMgmt"
            ,"sustainabilityPolicyAndGoal"
            ,"socialPerformance"
            ,"sustainabilityReport"},type = EntityGraph.EntityGraphType.LOAD)
    List<ReportDetail> findByReportIdForGenDataStructure(Long reportId);

    @Query(value = "SELECT r FROM ReportDetail r\n" +
            "INNER JOIN NodeConfig nc on r.nodeConfig.id = nc.id and nc.nodeLevel = 1 \n"+
            "WHERE r.report.id = ?1"
    )
    List<ReportDetail> findByNodeConfigLevel1(Long reportId);

    @Query(value = "SELECT r.* FROM report_detail r " +
            "INNER JOIN node_config nc ON r.node_config_id = nc.id " +
            "WHERE r.report_id = :report_id AND nc.name = :name",
            nativeQuery = true)
    ReportDetail findByReportIdAndNodeName(@Param("report_id") Long reportId, @Param("name") String name);

    @Query(value = "SELECT r.* FROM report_detail r " +
            "INNER JOIN node_config nc ON r.node_config_id = nc.id " +
            "WHERE r.id = :id AND nc.name = :name",
            nativeQuery = true)
    ReportDetail findByIdAndNodeName(@Param("id") Long id, @Param("name") String name);


    @Query(value = "SELECT r.* FROM report_detail r " +
            "INNER JOIN node_config nc ON r.node_config_id = nc.id " +
            "WHERE r.report_id = :report_id AND nc.name in :name " +
            "ORDER BY nc.parent_id ASC, nc.sequence ASC",
            nativeQuery = true)
    List<ReportDetail> findReportDetailByReportIdAndInNodeConfigName(@Param("report_id") Long reportId, @Param("name") List<String> nodeName);


    @Query(value = "SELECT * FROM report_detail r\n" +
            "INNER JOIN node_config nc on r.node_config_id = nc.id  \n"+
            "WHERE r.report_id = ?1 AND nc.name = ?2", nativeQuery = true
    )
    Optional<ReportDetail> findByReportIdAndNodeConfigName(Long reportId, String nodeName);
}

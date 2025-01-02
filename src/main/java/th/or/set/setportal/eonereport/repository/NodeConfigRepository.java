package th.or.set.setportal.eonereport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.or.set.setportal.eonereport.bean.export.NodeConfigResponse;
import th.or.set.setportal.eonereport.model.NodeConfig;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NodeConfigRepository extends JpaRepository<NodeConfig, Long> {

    List<NodeConfig> findByReportPhaseAndActiveOrderById(BigDecimal phase, Boolean active);

    NodeConfig findByNodeGroupAndNodeLevel(Long nodeGroup, Long nodeLevel);

    @Query(value = "select max(n.phase) from node_config n", nativeQuery = true)
    Long findNewestPhase();

    NodeConfig findByNameAndReportPhase(String nodeName, BigDecimal phase);

    List<NodeConfig> findByNameInAndReportPhase(List<String> nodeName, BigDecimal phase);

    List<NodeConfig>  findByParentId(Long parentId);

    List<NodeConfig> findByNameIn(List<String> nodeName);

    List<NodeConfig> findByParentIdIn(List<Long> parentId);

    @Query(value = "select new th.or.set.setportal.eonereport.bean.export.NodeConfigResponse(n.name, n.nodeGroup, n.descriptionTh, n.descriptionEn) " +
            "from NodeConfig n where n.nodeGroup in ?1 and n.nodeLevel = ?2 and n.phase = ?3")
    List<NodeConfigResponse> findByNodeGroupInAndNodeLevel(List<Long> nodeGroup, Long nodeLevel, BigDecimal phase);
}

package xyz.guqing.travelpath.model.dos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.guqing.travelpath.model.entity.DeployPlan;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.model.entity.PresetPlan;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 布设卡口方案do
 * @author guqing
 * @date 2020-10-19
 */
@Data
public class DeployPlanDO {
    private Long id;
    private Long presetId;
    private String name;
    private String description;
    private Integer count;
    private LocalDateTime createTime;
    private List<DeployPlanNode> deployPlanNodes;
}

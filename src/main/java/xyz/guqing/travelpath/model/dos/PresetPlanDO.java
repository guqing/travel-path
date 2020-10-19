package xyz.guqing.travelpath.model.dos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.model.entity.PresetPlan;

import java.util.List;

/**
 * 预设卡口方案do
 * @author guqing
 * @date 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PresetPlanDO extends PresetPlan {
    private List<PresetNode> presetNodes;
}

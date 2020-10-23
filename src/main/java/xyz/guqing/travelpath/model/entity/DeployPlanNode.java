package xyz.guqing.travelpath.model.entity;

import lombok.Data;

/**
 * 布设方案和卡口关系entity
 * @author guqing
 * @date 2020-10-22
 */
@Data
public class DeployPlanNode {
    private Long deployId;
    private Long presetNodeId;
}

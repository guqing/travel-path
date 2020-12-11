package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 布设方案和卡口关系entity
 * @author guqing
 * @date 2020-10-22
 */
@Data
@Accessors(chain = true)
@TableName("deploy_node")
public class DeployPlanNode {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long deployId;

    private Double lat;

    private Double lng;
}

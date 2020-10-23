package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guqing
 * @date 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeployPlan extends BaseEntity{
    private Long userId;
    private String name;
    private String description;
    private Integer count;
    @TableLogic
    private Integer deleted;
}

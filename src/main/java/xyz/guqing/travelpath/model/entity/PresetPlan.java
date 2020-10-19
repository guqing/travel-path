package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 预设卡口方案数据库实体
 * @author guqing
 * @date 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("preset_plan")
public class PresetPlan extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * 方案包含的卡口数量
     */
    private Integer count;
    /**
     * 方案名称
     */
    private String name;
    /**
     * 方案描述
     */
    private String description;

    @TableLogic
    private Integer deleted;
}

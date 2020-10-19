package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 卡口实体
 * @author guqing
 * @date 2020-10-19
 */
@Data
@Accessors(chain = true)
@TableName("preset_node")
public class PresetNode {
    private Long id;
    private Long presetId;
    private Double lat;
    private Double lng;
}

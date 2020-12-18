package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 出行轨迹卡口序列
 *
 * @author guqing
 * @date 2020-12-18
 */
@Data
@Accessors(chain = true)
@TableName("route_check_point_sequence")
@EqualsAndHashCode(callSuper = true)
public class RouteCheckPointSequence extends BaseEntity{
    private Long routeId;
    /**
     * 序列排序
     */
    private Integer index;
    private Double lat;
    private Double lng;
}

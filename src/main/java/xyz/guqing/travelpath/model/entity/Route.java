package xyz.guqing.travelpath.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 车辆出现轨迹
 *
 * @author guqing
 * @date 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("route")
public class Route extends BaseEntity {
    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车牌途径卡口序列
     */
    private Long checkpointSequenceId;

    /**
     * 轨迹坐标点json字符串
     */
    private String points;

    /**
     * 轨迹距离单位毫秒
     */
    private Double distance;

    /**
     * 轨迹耗时单位毫秒
     */
    private Double time;

    /**
     * 平均速度 km/h
     */
    private Double averageSpeed;

    /**
     * 正常转弯次数
     */
    private Integer regularTurnCount;

    /**
     * 急转弯次数
     */
    private Integer sharpTurnCount;

    /**
     * 掉头次数
     */
    private Integer uTurnCount;
}

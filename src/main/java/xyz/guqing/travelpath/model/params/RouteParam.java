package xyz.guqing.travelpath.model.params;

import lombok.Data;
import xyz.guqing.travelpath.model.entity.Route;
import xyz.guqing.travelpath.model.support.InputConverter;
import xyz.guqing.travelpath.route.Point;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 车辆出现轨迹参数
 *
 * @author guqing
 * @date 2020-12-18
 */
@Data
public class RouteParam implements InputConverter<Route> {
    private Long id;

    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

    @NotNull(message = "车辆卡口序列不能为空")
    private List<Point> checkpoints;

    @NotNull(message = "轨迹坐标点序列不能为空")
    private List<Point> points;

    @NotNull(message = "距离不能为空")
    private Double distance;

    @NotNull(message = "时间不能为空")
    private Double time;

    @NotNull(message = "平均速度不能为空")
    private Double averageSpeed;

    @NotNull(message = "转弯次数不能为空")
    private Integer regularTurnCount;

    @NotNull(message = "急次数不能为空")
    private Integer sharpTurnCount;

    @NotNull(message = "掉头数不能为空")
    private Integer uTurnCount;
}

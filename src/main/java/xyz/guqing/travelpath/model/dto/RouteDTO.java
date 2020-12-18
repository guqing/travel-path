package xyz.guqing.travelpath.model.dto;

import lombok.Data;
import xyz.guqing.travelpath.model.entity.Route;
import xyz.guqing.travelpath.model.support.OutputConverter;
import xyz.guqing.travelpath.route.Point;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guqing
 * @date 2020-12-18
 */
@Data
public class RouteDTO implements OutputConverter<RouteDTO, Route> {
    private Long id;

    private String carNumber;

    private Double distance;

    private Double time;

    private Double averageSpeed;

    private Integer regularTurnCount;

    private Integer sharpTurnCount;

    private Integer uTurnCount;

    private List<Point> checkpoints;

    private List<Point> points;

    private LocalDateTime createTime;
}

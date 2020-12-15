package xyz.guqing.travelpath.model.params;

import com.graphhopper.util.shapes.GHPoint;
import lombok.Data;
import xyz.guqing.travelpath.route.Point;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * @author guqing
 * @date 2020-10-08
 */
@Data
public class RouteQuery {
    @NotNull(message = "起点不能为空")
    private Point start;

    @NotNull(message = "起点不能为空")
    private Point end;

    private List<Point> waypoints;

    public List<GHPoint> convertTo() {
        List<GHPoint> points = new LinkedList<>();
        GHPoint start = new GHPoint(this.start.getLat(), this.start.getLng());
        points.add(start);
        waypoints.forEach(waypoint -> {
            GHPoint point = new GHPoint(waypoint.getLat(), waypoint.getLng());
            points.add(point);
        });
        GHPoint end = new GHPoint(this.end.getLat(), this.end.getLng());
        points.add(end);
        return points;
    }
}

package xyz.guqing.travelpath.controller;

import com.graphhopper.util.shapes.GHPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.model.params.RouteQuery;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.route.RoutePath;
import xyz.guqing.travelpath.service.RouteService;

import javax.validation.Valid;
import java.util.List;

/**
 * 轨迹生成
 *
 * @author guqing
 * @date 2020-12-15
 */
@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public ResultEntity<List<RoutePath>> route(@RequestBody @Valid RouteQuery routeQuery) {
        List<GHPoint> ghPoints = routeQuery.convertTo();
        List<RoutePath> routes = routeService.route(ghPoints);
        return ResultEntity.ok(routes);
    }
}

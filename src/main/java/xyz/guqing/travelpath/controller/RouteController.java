package xyz.guqing.travelpath.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graphhopper.util.shapes.GHPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.model.dto.RouteDTO;
import xyz.guqing.travelpath.model.entity.Route;
import xyz.guqing.travelpath.model.params.RouteParam;
import xyz.guqing.travelpath.model.params.RouteQuery;
import xyz.guqing.travelpath.model.support.PageInfo;
import xyz.guqing.travelpath.model.support.PageQuery;
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

    @PostMapping("/save")
    public ResultEntity<String> create(@RequestBody @Valid RouteParam routeParam) {
        routeService.createBy(routeParam);
        return ResultEntity.ok();
    }

    @GetMapping
    public ResultEntity<PageInfo<RouteDTO>> list(PageQuery pageQuery) {
        Page<Route> routes = routeService.listBy(pageQuery);
        return ResultEntity.okList(routes, route -> new RouteDTO().convertFrom(route));
    }

    @GetMapping("/{id:\\d+}")
    public ResultEntity<RouteDTO> getBy(@PathVariable Long id) {
        RouteDTO routeDTO = routeService.getDetailById(id);
        return ResultEntity.ok(routeDTO);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResultEntity<String> deletePermanentlyById(@PathVariable Long id) {
        routeService.removeById(id);
        return ResultEntity.ok();
    }
}

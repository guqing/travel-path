package xyz.guqing.travelpath.service;

import com.graphhopper.util.shapes.GHPoint;
import xyz.guqing.travelpath.route.RoutePath;

import java.util.List;

/**
 * 路径规划服务
 *
 * @author guqing
 * @date 2020-12-13
 */
public interface RouteService {
    /**
     * 根据车辆卡口序列生成多条轨迹
     *
     * @param points 坐标点
     * @return 返回车辆出行轨迹
     */
    List<RoutePath> route(List<GHPoint> points);
}

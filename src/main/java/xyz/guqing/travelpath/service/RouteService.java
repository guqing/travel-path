package xyz.guqing.travelpath.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphhopper.util.shapes.GHPoint;
import xyz.guqing.travelpath.model.dto.RouteDTO;
import xyz.guqing.travelpath.model.entity.Route;
import xyz.guqing.travelpath.model.params.RouteParam;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.route.RoutePath;

import java.util.List;

/**
 * 路径规划服务
 *
 * @author guqing
 * @date 2020-12-13
 */
public interface RouteService extends IService<Route> {
    /**
     * 根据车辆卡口序列生成多条轨迹
     *
     * @param points 坐标点
     * @return 返回车辆出行轨迹
     */
    List<RoutePath> route(List<GHPoint> points);

    /**
     * 创建车辆出现轨迹还原记录
     *
     * @param routeParam 出现轨迹参数
     */
    void createBy(RouteParam routeParam);

    /**
     * 查询车辆轨迹列表,不返回卡口序列及轨迹坐标点序列
     *
     * @param pageQuery 分页
     * @return 返回车辆轨迹分页列表
     */
    Page<Route> listBy(PageQuery pageQuery);

    /**
     * 根据id查询轨迹详情
     *
     * @param id 轨迹id
     * @return 返回轨迹详情dto
     */
    RouteDTO getDetailById(Long id);
}

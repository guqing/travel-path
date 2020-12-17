package xyz.guqing.travelpath.service.impl;

import com.graphhopper.GraphHopper;
import com.graphhopper.routing.AlternativeRoute;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.querygraph.QueryGraph;
import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.TraversalMode;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.Snap;
import com.graphhopper.util.details.PathDetailsBuilderFactory;
import com.graphhopper.util.shapes.GHPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.route.MyPathMerger;
import xyz.guqing.travelpath.route.PathHelper;
import xyz.guqing.travelpath.route.RoutePath;
import xyz.guqing.travelpath.service.RouteService;
import xyz.guqing.travelpath.utils.CartesianUtils;
import xyz.guqing.travelpath.utils.TopSis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.graphhopper.util.Parameters.Details.AVERAGE_SPEED;

/**
 * 路径规划服务实现类
 *
 * @author guqing
 * @date 2020-12-13
 */
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final GraphHopper graphHopper;
    private final CarFlagEncoder carFlagEncoder;
    private final EncodingManager encodingManager;

    @Override
    public List<RoutePath> route(List<GHPoint> points) {
        LocationIndex locationIndex = graphHopper.getLocationIndex();
        List<Snap> snaps = PathHelper.lookupClosest(locationIndex, points);
        Weighting weighting = new FastestWeighting(carFlagEncoder);
        QueryGraph queryGraph = QueryGraph.create(graphHopper.getGraphHopperStorage(), snaps);

        AlternativeRoute alternativeRoute = new AlternativeRoute(queryGraph, weighting, TraversalMode.EDGE_BASED);
        alternativeRoute.setMaxPaths(5);
        alternativeRoute.setMaxShareFactor(0.8);
        alternativeRoute.setMaxWeightFactor(1.6);

        List<List<Path>> pathsList = PathHelper.calcAlternativePaths(alternativeRoute, snaps);
        System.out.println(pathsList);
        MyPathMerger myPathMerger = new MyPathMerger(queryGraph, weighting, encodingManager)
                .setPathDetailsBuilders(new PathDetailsBuilderFactory(), Collections.singletonList(AVERAGE_SPEED));

        List<List<Path>> descartesPaths = CartesianUtils.cartesianProduct(pathsList);

        List<RoutePath> pathList = new ArrayList<>();
        for (List<Path> subPathList : descartesPaths) {
            RoutePath routePath = myPathMerger.pathMerger(subPathList);
            pathList.add(routePath);
        }
        return pathList;
    }

    private List<RoutePath> topSis(List<RoutePath> pathList) {
        double[][] matrix = new double[pathList.size()][6];
        // 提取数据矩阵
        for (int i = 0; i < pathList.size(); i++) {
            RoutePath routePath = pathList.get(i);
            // 效益型
            matrix[i][0] = routePath.getDistance();
            // 成本型
            matrix[i][1] = routePath.getTime();
            // 效益型
            matrix[i][2] = routePath.getAverageSpeed();
            // 成本型
            matrix[i][3] = routePath.getRegularTurnCount();
            // 成本型
            matrix[i][4] = routePath.getSharpTurnCount();
            // 成本型
            matrix[i][5] = routePath.getUTurnCount();
        }

        double[] weights = {0.2, 0.2, 0.2, 0.1, 0.2, 0.1};
        // 计算
        int[] attributeType = {1, 0, 1, 0, 0, 0};
        double[] degrees = TopSis.decisionAttributes(matrix, weights, attributeType);
        // 设置值
        for (int i = 0; i < degrees.length; i++) {
            RoutePath routePath = pathList.get(i);
            routePath.setDecisionValue(degrees[i]);
        }

        return pathList.stream()
                .sorted(Comparator.comparing(RoutePath::getDecisionValue))
                .collect(Collectors.toList());
    }
}

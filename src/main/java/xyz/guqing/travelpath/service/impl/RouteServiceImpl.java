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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}

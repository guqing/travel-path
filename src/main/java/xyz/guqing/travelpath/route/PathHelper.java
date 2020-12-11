package xyz.guqing.travelpath.route;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.IntIndexedContainer;
import com.graphhopper.routing.AlternativeRoute;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.Snap;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.util.CollectionUtils;
import xyz.guqing.travelpath.exception.MultiplePointsNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路径工具
 *
 * @author guqing
 * @date 2020-11-18
 */
public class PathHelper {

    /**
     * 拼接多条路径为一条
     *
     * @param graph 路网拓扑图对象
     * @param paths 路径分支集合
     * @return 返回拼接后的整条路径
     */
    public static Path concat(Graph graph, List<Path> paths) {
        if (CollectionUtils.isEmpty(paths)) {
            return null;
        }
        Path path = new Path(graph);
        path.setFromNode(paths.get(0).calcNodes().get(0));
        for (Path svPath : paths) {
            for (EdgeIteratorState edge : svPath.calcEdges()) {
                path.addEdge(edge.getEdge());
            }
            path.setWeight(path.getWeight() + svPath.getWeight());
            path.setDistance(path.getDistance() + svPath.getDistance());
            path.addTime(svPath.getTime());
        }
        path.setEndNode(paths.get(paths.size() - 1).getEndNode());
        path.setFound(true);
        return path;
    }

    /**
     * 批量点捕捉到边
     *
     * @param locationIndex 位置索引对象
     * @param points        待捕捉的坐标点
     * @return 返回捕捉到的集合
     * @throws MultiplePointsNotFoundException 存在无法捕捉到边的点时抛出点找不到异常
     */
    public static List<Snap> lookupClosest(LocationIndex locationIndex, List<GHPoint> points) {
        if (CollectionUtils.isEmpty(points)) {
            return Collections.emptyList();
        }

        IntArrayList pointsNotFound = new IntArrayList();
        List<Snap> snapResults = new ArrayList<>(points.size());
        for (int placeIndex = 0; placeIndex < points.size(); placeIndex++) {
            GHPoint point = points.get(placeIndex);
            Snap closest = locationIndex.findClosest(point.lat, point.lon, EdgeFilter.ALL_EDGES);
            if (!closest.isValid()) {
                pointsNotFound.add(placeIndex);
            }
            snapResults.add(closest);
        }

        if (!pointsNotFound.isEmpty()) {
            throw new MultiplePointsNotFoundException(pointsNotFound);
        }

        return snapResults;
    }

    /**
     * 计算备选路径，允许途径多个点
     *
     * @param alternativeRoute 备选路径规划类
     * @param snaps            捕捉到边的对象集合
     * @return 返回规划到的多条路径集合，每一个子集合都是经过途径点的分支路径
     */
    public static List<List<Path>> calcAlternativePaths(AlternativeRoute alternativeRoute, List<Snap> snaps) {
        if (snaps.size() < 2) {
            throw new IllegalArgumentException("At least 2 snaps have to be specified, but was:" + snaps.size());
        }

        final int legs = snaps.size() - 1;
        List<List<Path>> pathsList = new ArrayList<>();
        for (int leg = 0; leg < legs; leg++) {
            Snap fromSnap = snaps.get(leg);
            Snap toSnap = snaps.get(leg + 1);

            // 计算路线
            List<Path> paths = alternativeRoute
                    .calcPaths(fromSnap.getClosestNode(), toSnap.getClosestNode());
            pathsList.add(paths);
        }
        // 返回一个List<List<Path>>便于组合多条分支路径
        return pathsList;
    }

}

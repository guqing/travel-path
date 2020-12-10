package xyz.guqing.travelpath.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.details.PathDetail;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author guqing
 * @date 2020-10-12
 */
@Setter
@Getter
@ToString
public class RoutePath implements Comparable<RoutePath> {
    @JsonIgnore
    private PointList pointList;

    private List<Point> points;
    /**
     * 距离,单位:米
     */
    private double distance;
    /**
     * 时间,单位:毫秒
     */
    private long time;
    /**
     * 急转弯次数
     */
    private int sharpTurnCount;
    /**
     * 掉头次数
     */
    private int uTurnCount;

    /**
     * 正常转弯次数
     */
    private int regularTurnCount;

    /**
     * 平均速度: km / h
     */
    private double averageSpeed;

    /**
     * 路径决策综合得分
     */
    private double decisionValue;

    private final Map<String, List<PathDetail>> pathDetails = new HashMap<>();

    private String debugInfo = "";

    private InstructionList instructions;

    public RoutePath() {
        this.points = new ArrayList<>();
    }

    public RoutePath(@NonNull List<Point> points) {
        this.points = points;
    }

    public void addPoint(@NonNull Point point) {
        this.points.add(point);
    }

    public void addPoints(@NonNull List<Point> points) {
        Assert.notNull(points, "The parameter points cannot be null");
        Point tailPoint = points.get(points.size() - 1);
        Point headPoint = points.get(0);
        if (Objects.equals(tailPoint, headPoint)) {
            // 删除第一个
            points.remove(0);
        }
        this.points.addAll(points);
    }

    /**
     * Adds the given PathDetails to the existing ones. If there are already PathDetails set, the number
     * details has to be equal to <code>details</code>.
     *
     * @param details The PathDetails to add
     */
    public void addPathDetails(Map<String, List<PathDetail>> details) {
        if (!this.pathDetails.isEmpty() && !details.isEmpty() && this.pathDetails.size() != details.size()) {
            throw new IllegalStateException("Details have to be the same size");
        }
        for (Map.Entry<String, List<PathDetail>> detailEntry : details.entrySet()) {
            if (this.pathDetails.containsKey(detailEntry.getKey())) {
                List<PathDetail> pd = this.pathDetails.get(detailEntry.getKey());
                merge(pd, detailEntry.getValue());
            } else {
                this.pathDetails.put(detailEntry.getKey(), detailEntry.getValue());
            }
        }
    }

    public static void merge(List<PathDetail> pathDetails, List<PathDetail> otherDetails) {
        // Make sure that the PathDetail list is merged correctly at via points
        if (!pathDetails.isEmpty() && !otherDetails.isEmpty()) {
            PathDetail lastDetail = pathDetails.get(pathDetails.size() - 1);
            boolean extend = lastDetail.getValue() != null
                    ? lastDetail.getValue().equals(otherDetails.get(0).getValue())
                    : otherDetails.get(0).getValue() != null;
            if (extend) {
                lastDetail.setLast(otherDetails.get(0).getLast());
                otherDetails.remove(0);
            }
        }

        pathDetails.addAll(otherDetails);
    }

    public RoutePath addDebugInfo(String debugInfo) {
        if (debugInfo == null) {
            throw new IllegalStateException("Debug information has to be none null");
        }

        if (!this.debugInfo.isEmpty()) {
            this.debugInfo += ";";
        }

        this.debugInfo += debugInfo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoutePath path = (RoutePath) o;
        return Objects.equals(points, path.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public int compareTo(RoutePath o) {
        if (Objects.equals(points, o.getPoints())) {
            return 0;
        }
        return 1;
    }
}

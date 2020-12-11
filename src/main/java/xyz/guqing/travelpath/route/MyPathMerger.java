package xyz.guqing.travelpath.route;

import com.graphhopper.routing.InstructionsFromEdges;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.Graph;
import com.graphhopper.util.*;
import com.graphhopper.util.details.PathDetail;
import com.graphhopper.util.details.PathDetailsBuilderFactory;
import com.graphhopper.util.details.PathDetailsFromEdges;
import com.graphhopper.util.exceptions.ConnectionNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class merges multiple {@link Path} objects into one continuous object that
 * can be used in the {@link RoutePath}. There will be a Path between every waypoint.
 * So for two waypoints there will be only one Path object. For three waypoints there will be
 * two Path objects.
 * <p>
 * The instructions are generated per Path object and are merged into one continuous InstructionList.
 * The PointList per Path object are merged and optionally simplified.
 *
 * @author guqing
 * @date 2020-11-19
 */
public class MyPathMerger {
    private static final DouglasPeucker DP = new DouglasPeucker();
    private final Graph graph;
    private final Weighting weighting;
    private DouglasPeucker douglasPeucker = DP;
    private final EncodingManager encodingManager;
    private final Translation translation;
    private boolean calcPoints = true;
    private PathDetailsBuilderFactory pathBuilderFactory;
    private List<String> requestedPathDetails = Collections.emptyList();
    private double favoredHeading = Double.NaN;
    private boolean enableInstructions = true;
    private boolean simplifyResponse = true;

    public MyPathMerger(Graph graph, Weighting weighting, EncodingManager encodingManager) {
        this.graph = graph;
        this.weighting = weighting;
        this.encodingManager = encodingManager;
        TranslationMap translationMap = new TranslationMap().doImport();
        this.translation = translationMap.getWithFallBack(Locale.CHINA);
    }

    public RoutePath pathMerger(List<Path> paths) {
        RoutePath routePath = new RoutePath();
        int origPoints = 0;
        // double fullWeight = 0;

        // 时间
        long fullTimeInMillis = 0;
        // 距离
        double fullDistance = 0;
        // 平均速度
        double averageSpeed = 0;

        int sharpTurnCount = 0;
        int uTurnCount = 0;
        int regularTurnCount = 0;

        boolean allFound = true;

        InstructionList fullInstructions = new InstructionList(translation);
        PointList fullPoints = PointList.EMPTY;

        for (int pathIndex = 0; pathIndex < paths.size(); pathIndex++) {
            Path path = paths.get(pathIndex);
            if (!path.isFound()) {
                allFound = false;
                continue;
            }

            fullTimeInMillis += path.getTime();
            fullDistance += path.getDistance();
            // fullWeight += path.getWeight();
            if (isEnableInstructions()) {
                InstructionList il = InstructionsFromEdges.calcInstructions(path, graph, weighting, encodingManager, translation);

                if (!il.isEmpty()) {
                    fullInstructions.addAll(il);

                    // for all paths except the last replace the FinishInstruction with a ViaInstruction
                    if (pathIndex + 1 < paths.size()) {
                        ViaInstruction newInstr = new ViaInstruction(fullInstructions.get(fullInstructions.size() - 1));
                        newInstr.setViaCount(pathIndex + 1);
                        fullInstructions.set(fullInstructions.size() - 1, newInstr);
                    }
                }

                for (Instruction instruction : il) {
                    int sign = instruction.getSign();
                    if (sign == Instruction.TURN_SHARP_LEFT || sign == Instruction.TURN_SHARP_RIGHT) {
                        sharpTurnCount = sharpTurnCount + 1;
                    } else if (sign == Instruction.U_TURN_LEFT || sign == Instruction.U_TURN_RIGHT || sign == Instruction.U_TURN_UNKNOWN) {
                        uTurnCount = uTurnCount + 1;
                    } else if (sign == Instruction.TURN_LEFT || sign == Instruction.TURN_RIGHT) {
                        regularTurnCount = regularTurnCount + 1;
                    }
                }
            }

            if (calcPoints || enableInstructions) {
                PointList tmpPoints = path.calcPoints();
                if (fullPoints.isEmpty()) {
                    fullPoints = new PointList(tmpPoints.size(), tmpPoints.is3D());
                }

                // Remove duplicated points, see #1138
                if (pathIndex + 1 < paths.size()) {
                    tmpPoints.removeLastPoint();
                }

                fullPoints.add(tmpPoints);
                try {
                    Map<String, List<PathDetail>> pathDetails = PathDetailsFromEdges.calcDetails(path, encodingManager, weighting,
                            requestedPathDetails, pathBuilderFactory, origPoints);
                    averageSpeed = pathDetails.get("average_speed").stream()
                            .mapToDouble(item -> (Double) item.getValue())
                            .average().orElse(0D);
                    routePath.addPathDetails(pathDetails);
                } catch (Exception e) {
                    // ignore this exception
                }
                origPoints = fullPoints.size();
            }

            allFound = allFound && path.isFound();
        }

        if (!allFound) {
            throw new ConnectionNotFoundException("Connection between locations not found", Collections.<String, Object>emptyMap());
        }

        if (enableInstructions) {
            updateInstructionsWithContext(fullInstructions);
            routePath.setInstructions(fullInstructions);
        }

        routePath.setAverageSpeed(averageSpeed);
        routePath.setDistance(fullDistance);
        routePath.setTime(fullTimeInMillis);
        routePath.setDistance(fullDistance);
        routePath.setSharpTurnCount(sharpTurnCount);
        routePath.setUTurnCount(uTurnCount);
        routePath.setRegularTurnCount(regularTurnCount);
        routePath.setPointList(fullPoints);

        // 简化路径
        boolean needToSimplifyPath = simplifyResponse && (calcPoints || enableInstructions);
        if (needToSimplifyPath) {
            fullPoints = MyPathSimplification.simplify(routePath, douglasPeucker, enableInstructions);
        }

        if (!fullPoints.isEmpty()) {
            routePath.addDebugInfo("simplify (" + origPoints + "->" + fullPoints.getSize() + ")");
        }

        for (int i = 0; i < fullPoints.getSize(); i++) {
            Point point = new Point();
            double lat = fullPoints.getLat(i);
            double lon = fullPoints.getLon(i);
            point.setLat(lat);
            point.setLng(lon);
            routePath.addPoint(point);
        }
        return routePath;
    }

    private void updateInstructionsWithContext(InstructionList instructions) {
        Instruction instruction;
        Instruction nextInstruction;

        for (int i = 0; i < instructions.size() - 1; i++) {
            instruction = instructions.get(i);

            if (i == 0 && !Double.isNaN(favoredHeading) && instruction.getExtraInfoJSON().containsKey("heading")) {
                double heading = (double) instruction.getExtraInfoJSON().get("heading");
                double diff = Math.abs(heading - favoredHeading) % 360;
                if (diff > 170 && diff < 190) {
                    // The requested heading points into the opposite direction of the calculated heading
                    // therefore we change the continue instruction to a u-turn
                    instruction.setSign(Instruction.U_TURN_UNKNOWN);
                }
            }

            if (instruction.getSign() == Instruction.REACHED_VIA) {
                nextInstruction = instructions.get(i + 1);
                if (nextInstruction.getSign() != Instruction.CONTINUE_ON_STREET
                        || !instruction.getExtraInfoJSON().containsKey("last_heading")
                        || !nextInstruction.getExtraInfoJSON().containsKey("heading")) {
                    // TODO throw exception?
                    continue;
                }
                double lastHeading = (double) instruction.getExtraInfoJSON().get("last_heading");
                double heading = (double) nextInstruction.getExtraInfoJSON().get("heading");

                // Since it's supposed to go back the same edge, we can be very strict with the diff
                double diff = Math.abs(lastHeading - heading) % 360;
                if (diff > 179 && diff < 181) {
                    nextInstruction.setSign(Instruction.U_TURN_UNKNOWN);
                }
            }
        }
    }

    public double getFavoredHeading() {
        return favoredHeading;
    }

    public MyPathMerger setFavoredHeading(double favoredHeading) {
        this.favoredHeading = favoredHeading;
        return this;
    }

    public boolean isEnableInstructions() {
        return enableInstructions;
    }

    public MyPathMerger setEnableInstructions(boolean enableInstructions) {
        this.enableInstructions = enableInstructions;
        return this;
    }

    public boolean isCalcPoints() {
        return calcPoints;
    }

    public MyPathMerger setCalcPoints(boolean calcPoints) {
        this.calcPoints = calcPoints;
        return this;
    }

    public boolean isSimplifyResponse() {
        return simplifyResponse;
    }

    public MyPathMerger setSimplifyResponse(boolean simplifyResponse) {
        this.simplifyResponse = simplifyResponse;
        return this;
    }

    public MyPathMerger setPathDetailsBuilders(PathDetailsBuilderFactory pathBuilderFactory, List<String> requestedPathDetails) {
        this.pathBuilderFactory = pathBuilderFactory;
        this.requestedPathDetails = requestedPathDetails;
        return this;
    }

    public MyPathMerger setDouglasPeucker(DouglasPeucker douglasPeucker) {
        this.douglasPeucker = douglasPeucker;
        return this;
    }
}

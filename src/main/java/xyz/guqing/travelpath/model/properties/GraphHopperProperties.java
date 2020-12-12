package xyz.guqing.travelpath.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guqing
 * @date 2020-12-12
 */
@Data
@ConfigurationProperties(prefix = "travel.graphhopper")
public class GraphHopperProperties {
    private String dataFile = "data.osm";
    private String graphLocation = "graph-cache";
    private String flagEncoders = "car";
}

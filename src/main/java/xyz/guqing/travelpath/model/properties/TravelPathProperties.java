package xyz.guqing.travelpath.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 系统配置类
 *
 * @author guqing
 * @date 2020-12-12
 */
@Data
@ConfigurationProperties(prefix = "travel")
public class TravelPathProperties {
    private String home = "~/.travel-path";
    private final GraphHopperProperties graphHopper = new GraphHopperProperties();
    private final Path path = Paths.get(home);

    public String getGraphLocation() {
        Path resolve = path.resolve(graphHopper.getGraphLocation());
        return resolve.toString();
    }

    public String getGraphhopperDataFile() {
        Path resolve = path.resolve(graphHopper.getDataFile());
        return resolve.toString();
    }
}

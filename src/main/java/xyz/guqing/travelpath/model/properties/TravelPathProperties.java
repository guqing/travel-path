package xyz.guqing.travelpath.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
    private GraphHopperProperties graphHopper = new GraphHopperProperties();
}

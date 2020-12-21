package xyz.guqing.travelpath.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import xyz.guqing.travelpath.model.constant.TravelPathConstant;

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
    private final GraphHopperProperties graphHopper = new GraphHopperProperties();
    private String home = TravelPathConstant.USER_HOME + "/.travel-path";
    private String uploadLocation = home + "/upload/";
    private final Path path = Paths.get(home);
    private String serverUrl = "http://127.0.0.1:8080";
    /**
     * 上传文件访问路径映射
     * 访问uploadMappingUri时映射到磁盘uploadLocation
     */
    private String uploadMappingUri = "files/static";

    public String getGraphLocation() {
        Path resolve = path.resolve(graphHopper.getGraphLocation());
        return resolve.toString();
    }

    public String getGraphhopperDataFile() {
        Path resolve = path.resolve(graphHopper.getDataFile());
        return resolve.toString();
    }
}

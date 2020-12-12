package xyz.guqing.travelpath.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.parsers.OSMMaxSpeedParser;
import com.graphhopper.routing.util.parsers.OSMMaxWidthParser;
import com.graphhopper.routing.util.parsers.OSMTollParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.guqing.travelpath.model.properties.TravelPathProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author guqing
 * @date 2020-10-08
 */
@Configuration
@EnableConfigurationProperties(TravelPathProperties.class)
public class GraphhopperConfig {
    @Autowired
    private TravelPathProperties travelPathProperties;

    @Bean
    public GraphHopper graphHopper() {
        Path graphPath = Paths.get(travelPathProperties.getHome());
        GraphHopper graphHopper = new GraphHopperOSM();
        graphHopper.setDataReaderFile(graphPath.resolve("shenzheng.osm").toString());
        graphHopper.setEncodingManager(encodingManager());
        graphHopper.setGraphHopperLocation(graphPath.resolve("graph-cache").toString());
        // see docs/core/profiles.md to learn more about profiles
        graphHopper.setProfiles(
                new Profile("car").setVehicle("car").setWeighting("shortest")
        );
        // this enables speed mode for the profile we call "car" here
        graphHopper.getCHPreparationHandler().setCHProfiles(
                new CHProfile("car")
        );
        System.out.println(graphHopper.getCHPreparationHandler());
        graphHopper.importOrLoad();
        return graphHopper;
    }

    public EncodingManager encodingManager() {
        return new EncodingManager.Builder()
                .setEnableInstructions(true)
                .add(new OSMMaxSpeedParser())
                .add(new OSMMaxWidthParser())
                .add(new OSMTollParser())
                .add(new CarFlagEncoder())
                .build();
    }
}

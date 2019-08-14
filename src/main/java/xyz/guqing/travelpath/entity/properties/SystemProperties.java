package xyz.guqing.travelpath.entity.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import xyz.guqing.travelpath.entity.support.TravelPathConst;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ConfigurationProperties(prefix = "auth.security.system")
public class SystemProperties {
    /**
     * Doc api disabled. (Default is true)
     */
    private boolean docDisabled = true;

    private String workDir = TravelPathConst.USER_HOME + "/.travel_path/";

    public SystemProperties() throws IOException {
        // Create work directory if not exist
        Files.createDirectories(Paths.get(workDir));
    }

    public boolean isDocDisabled() {
        return docDisabled;
    }

    public void setDocDisabled(boolean docDisabled) {
        this.docDisabled = docDisabled;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }
}

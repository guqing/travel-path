package xyz.guqing.travelpath.entity.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guqing
 * @date 2019/8/10
 */
@ConfigurationProperties(prefix = "auth.security.login")
public class LoginProperties {
    private String loginUrl = "/authentication/login";
    private String logoutUrl = "/logout";

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }
}

package xyz.guqing.travelpath.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guqing
 * @date 2019/8/10
 */
@ConfigurationProperties(prefix = "auth.security.token")
public class TokenProperties {
    /**
     * 30分钟(以秒s计)
     */
    private long expirationTime = 1_800;
    /**
     * 允许过期时间时钟偏移秒s为单位
     */
    private long allowedClockSkewSeconds = 1_800;
    /**
     *  JWT密码
     */
    private String secret = "CodeSheepSecret";
    /**
     * Token前缀
     */
    private String tokenPrefix = "";
    /**
     * 存放Token的Header Key
     */
    private String headerString = "Authorization";

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public long getAllowedClockSkewSeconds() {
        return allowedClockSkewSeconds;
    }

    public void setAllowedClockSkewSeconds(long allowedClockSkewSeconds) {
        this.allowedClockSkewSeconds = allowedClockSkewSeconds;
    }
}

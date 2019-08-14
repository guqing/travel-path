package xyz.guqing.travelpath.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.properties.MySecurityAutoConfiguration;
import xyz.guqing.travelpath.entity.properties.TokenProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guqing
 * @date 2019/8/10
 */
@Component
public class JwtTokenUtil implements Serializable {
    @Autowired
    MySecurityAutoConfiguration securityProperties;

    private static final long serialVersionUID = -5625635588908941275L;

    private static final String CLAIM_KEY_USER_ID = "ID";
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Integer getUserIdFromToken(String token) {
        Integer userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = (Integer) claims.get(CLAIM_KEY_USER_ID);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        TokenProperties tokenProperties = securityProperties.getTokenProperties();
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setAllowedClockSkewSeconds(tokenProperties.getAllowedClockSkewSeconds())
                    .setSigningKey(tokenProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    private Date generateExpirationDate() {
        TokenProperties tokenProperties = securityProperties.getTokenProperties();
        return new Date(System.currentTimeMillis() + tokenProperties.getExpirationTime() * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if(expiration != null) {
            //判断是否过期
            return expiration.before(new Date());
        }
        //获取不到expiration肯定过期
        return true;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(CLAIM_KEY_USER_ID, userDetails.getId());
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        TokenProperties tokenProperties = securityProperties.getTokenProperties();
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret())
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if(expiration != null && expiration.before(new Date())) {
            //判断是否可以刷新
            return true;
        }
        //获取不到expiration肯定过期并且不能刷新
        return false;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetails;
        final String username = getUsernameFromToken(token);

        return (
                username.equals(myUserDetails.getUsername())
                        && !isTokenExpired(token)
        );
    }
}

package xyz.guqing.travelpath.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;

@Component
public class UserTokenUtil {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public final String generateToken(MyUserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }
}

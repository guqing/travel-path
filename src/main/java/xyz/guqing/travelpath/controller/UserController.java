package xyz.guqing.travelpath.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.properties.TokenProperties;
import xyz.guqing.travelpath.service.MyUserDetailsService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.UserTokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Resource
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserTokenUtil userTokenutil;
    @Autowired
    private TokenProperties tokenProperties;
    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        Object error = validateUser(user);
        if(error != null) {
            return error;
        }
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        boolean isPasswordEqual = bCryptPasswordEncoder.matches(user.getPassword(), userDetails.getPassword());;
        if(isPasswordEqual) {
            String token = userTokenutil.generateToken(userDetails);

            return Result.ok(token);
        }
        return Result.fail(403, "认证失败，用户名或密码不正确");
    }

    public Object getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeaderString());
        return null;
    }

    @PostMapping("/register")
    public Object register(@RequestBody User user){
        return Result.ok(user);
    }

    public Object validateUser(User user) {
        if(StringUtils.isBlank(user.getUsername())) {
            return Result.badArgument();
        }
        if(StringUtils.isBlank(user.getPassword())) {
            return Result.badArgument();
        }
        return null;
    }
}

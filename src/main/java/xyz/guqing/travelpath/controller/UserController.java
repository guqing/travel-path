package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.dto.UserDTO;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.entity.properties.TokenProperties;
import xyz.guqing.travelpath.service.MyUserDetailsServiceImpl;
import xyz.guqing.travelpath.service.UserService;
import xyz.guqing.travelpath.utils.JwtTokenUtil;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guqing
 * @date 2019/8/11
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(PresetSchemeController.class);
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public Object login(@RequestBody User user) {
        Object error = validateUser(user);
        if(error != null) {
            return error;
        }
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        boolean isPasswordEqual = bCryptPasswordEncoder.matches(user.getPassword(), userDetails.getPassword());;
        if(isPasswordEqual) {
            String token = jwtTokenUtil.generateToken(userDetails);

            return Result.ok(token);
        }
        return Result.fail(403, "认证失败，用户名或密码不正确");
    }

    @GetMapping("/user/info")
    public Object getUserInfo(HttpServletRequest request) {
        try {
            MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
            Integer userId = user.getId();
            UserDTO userInfo = userService.getUserInfo(userId);
            return Result.ok(userInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/list")
    public Object list(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<UserDTO> userList = userService.listUser(current, pageSize);
        return Result.okList(userList);
    }

    @PostMapping("/auth/register")
    public Object register(@RequestBody User user){
        return Result.ok(user);
    }

    private Object validateUser(User user) {
        if(StringUtils.isBlank(user.getUsername())) {
            return Result.badArgument();
        }
        if(StringUtils.isBlank(user.getPassword())) {
            return Result.badArgument();
        }
        return null;
    }
}

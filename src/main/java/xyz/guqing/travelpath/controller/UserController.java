package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.annotation.WriteLog;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.dto.UserDTO;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.entity.params.LoginParam;
import xyz.guqing.travelpath.entity.params.UserParam;
import xyz.guqing.travelpath.service.MyUserDetailsServiceImpl;
import xyz.guqing.travelpath.service.UserService;
import xyz.guqing.travelpath.utils.IpUtil;
import xyz.guqing.travelpath.utils.JwtTokenUtil;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author guqing
 * @date 2019/8/11
 */
@RestController
public class UserController {
    private final MyUserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public UserController(MyUserDetailsServiceImpl userDetailsService,
                          JwtTokenUtil jwtTokenUtil,
                          UserService userService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public Object login(@RequestBody @Valid LoginParam user, HttpServletRequest request) {
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername(), user.getLoginType());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordEqual = bCryptPasswordEncoder.matches(user.getPassword(), userDetails.getPassword());;

        if(isPasswordEqual) {
            // 颁发token
            String token = jwtTokenUtil.generateToken(userDetails);
            // 更新用户最后登录时间和ip
            String ip = IpUtil.getIpAddr(request);
            userService.updateLoginTime(userDetails.getId(), ip);
            return Result.ok(token);
        }
        return Result.fail(403, "认证失败，用户名或密码不正确");
    }

    @GetMapping("/user/info")
    public Object getUserInfo(HttpServletRequest request) {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();
        UserDTO userInfo = userService.getUserInfo(userId);
        return Result.ok(userInfo);
    }

    @GetMapping("/user/baseInfo")
    public Object getBaseInfo() {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();
        UserDTO userInfo = userService.getBaseUserInfo(userId);
        return Result.ok(userInfo);
    }

    @GetMapping("/user/list")
    public Object list(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<UserDTO> userList = userService.listUser(current, pageSize);
        return Result.okList(userList);
    }

    @PutMapping("/user/updateInfo")
    public Object updateUserInfo(@RequestBody UserParam userParam,
                                 BindingResult result) {
        if(result.hasErrors()) {
            return Result.badArgument();
        }
        userService.updateUserInfo(userParam);
        return Result.ok();
    }

    @PostMapping("/auth/register")
    public Object register(@RequestBody User user){
        return Result.ok(user);
    }
}

package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.dto.UserDTO;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.entity.params.LoginParam;
import xyz.guqing.travelpath.entity.params.RegisterParam;
import xyz.guqing.travelpath.entity.params.UserParam;
import xyz.guqing.travelpath.entity.params.UserPasswordParam;
import xyz.guqing.travelpath.service.MyUserDetailsServiceImpl;
import xyz.guqing.travelpath.service.UserService;
import xyz.guqing.travelpath.utils.IpUtil;
import xyz.guqing.travelpath.utils.JwtTokenUtil;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

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
        if(Objects.isNull(userDetails)) {
            return Result.fail(403, "用户不存在");
        }

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

    @PutMapping("/user/updatePassword")
    public Object updatePassword(@RequestBody UserPasswordParam passwordParam) throws Exception{

        // 去除新密码首尾空格
        String newPassword = passwordParam.getNewPassword().trim();
        boolean isExists = userService.isExistsWithIdAndPassword(passwordParam.getId(), passwordParam.getOldPassword());
        if(!isExists) {
            return Result.fail(402 ,"原始密码不正确");
        }
        userService.updatePassword(passwordParam.getId(), newPassword);
        return Result.ok();
    }

    @GetMapping("/user/has-user")
    public Object hasUser(String username) {
        if(username == null) {
            return Result.badArgument();
        }
        boolean isExists = userService.checkUserExistsByUsername(username);
        return Result.ok(isExists);
    }

    @PostMapping("/auth/register")
    public Object register(@RequestBody RegisterParam model,
                           BindingResult result,
                           HttpServletRequest request){
        if(result.hasErrors()) {
            return Result.badArgument();
        }

        boolean isExists = userService.checkUserExistsByUsername(model.getUsername());
        if(isExists) {
            return Result.fail(401, "用户名已经存在");
        }

        // 保存用户
        userService.register(model, getServerPath(request));
        return Result.ok(model);
    }

    @GetMapping("/user/activate")
    public String activateAccount(String username) {
        userService.activateAccount(username);
        return "<h1 style='color:green;'>恭喜！邮箱验证成功</h1>";
    }

    /**
     * 获取当前服务器基地址
     * @param request request请求对象
     * @return 返回服务器基地址
     */
    private String getServerPath(HttpServletRequest request) {
        return request.getScheme() + "://"+request.getServerName()+":" +
                request.getServerPort() + request.getContextPath() + "/";
    }
}

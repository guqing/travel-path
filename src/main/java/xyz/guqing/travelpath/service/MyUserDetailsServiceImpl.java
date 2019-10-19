package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.entity.model.User;
import xyz.guqing.travelpath.entity.support.LoginTypeConstant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author guqing
 * @date 2019/8/11
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;

    @Autowired
    public MyUserDetailsServiceImpl(UserService userService,
                                    RoleService roleService,
                                    PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUser(username, LoginTypeConstant.USERNAME);
    }

    public MyUserDetails loadUserByUsername(String username, Integer loginType) throws UsernameNotFoundException {
        return loadUser(username, loginType);
    }

    private MyUserDetails loadUser(String username, Integer loginType) {
        MyUserDetails userDetails = new MyUserDetails();
        User user = userService.getUserByUsername(username, loginType);
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());

        // 设置权限url
        Set<PermissionAction> actions = permissionService.listActionsByRoleId(user.getRoleId());
        userDetails.setPermissionUrl(this.getPermissionUrl(actions));

        Role role = roleService.getRoleById(user.getRoleId());
        userDetails.setRole(role);
        return userDetails;
    }
    /**
     * 根据权限列表获取权限对应的url集合
     * @param actions 权限action集合
     * @return 返回权限url
     */
    private Set<String> getPermissionUrl(Set<PermissionAction> actions) {
        Set<String> actionUrl = new HashSet<>();
        actions.forEach(action -> {
            actionUrl.add(action.getUrl());
        });

        return actionUrl;
    }

}
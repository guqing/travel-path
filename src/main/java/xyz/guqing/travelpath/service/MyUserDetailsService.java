package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.Permission;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.entity.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author guqing
 * @date 2019/8/11
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionActionService actionService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails userDetails = new MyUserDetails();
        User user = userService.getUserByUsername(username);
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());

        // 设置权限url
        List<Permission> permissions = permissionService.listPermissionByRoleId(user.getRoleId());
        userDetails.setPermissionUrl(this.getPermissionUrl(permissions));

        Role role = roleService.getRoleById(user.getRoleId());
        userDetails.setRole(role);

        return userDetails;
    }

    /**
     * 根据权限列表获取权限对应的url集合
     * @param permissions 权限集合
     * @return 返回权限url
     */
    private Set<String> getPermissionUrl(List<Permission> permissions) {
        Set<String> actionUrl = new HashSet<>();
        permissions.forEach(permission -> {
            Set<PermissionAction> permissionActions = actionService.listPermissionAction(permission.getId());
            permissionActions.forEach(permissionAction -> {
                actionUrl.add(permissionAction.getUrl());
            });
        });

        return actionUrl;
    }

}
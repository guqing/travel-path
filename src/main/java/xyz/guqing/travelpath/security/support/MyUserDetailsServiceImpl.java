package xyz.guqing.travelpath.security.support;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.model.bo.CurrentUser;
import xyz.guqing.travelpath.model.bo.MyUserDetails;
import xyz.guqing.travelpath.service.MenuService;
import xyz.guqing.travelpath.service.UserService;
import xyz.guqing.travelpath.utils.TravelPathUtils;

import java.util.List;


/**
 * @author guqing
 * @date 2019-12-22 24:53
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final MenuService menuService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CurrentUser user = userService.loadUserByUsername(username);
        String password = user.getPassword();

        String permissions = menuService.findUserPermissions(username);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
        if (StringUtils.isNotBlank(permissions)) {
            grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
        }

        MyUserDetails myUserDetails = new MyUserDetails(
                username,
                password,
                true,
                true,
                true,
                true,
                grantedAuthorities
        );

        BeanUtils.copyProperties(user, myUserDetails);
        myUserDetails.setRoleIds(TravelPathUtils.commaSeparatedToList(user.getRoleId()));
        myUserDetails.setRoleNames(TravelPathUtils.commaSeparatedToList(user.getRoleName()));

        // 返回自定义的 MyUserDetails
        return myUserDetails;
    }
}
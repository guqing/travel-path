package xyz.guqing.travelpath.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.Permission;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacauthorityservice")
public class RbacAuthorityService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        boolean hasPermission  = false;

        if (principal instanceof MyUserDetails) {
            MyUserDetails myUserDetails = (MyUserDetails)principal;
            String username = ((UserDetails)principal).getUsername();

            //获取资源
            //这些 url 都是要登录后才能访问，且其他的 url 都不能访问
            Set<String> permissionUrls = myUserDetails.getPermissionUrl();

            hasPermission = matcherUrl(request, permissionUrls);
    
            return hasPermission;
        }

        return hasPermission;
    }
    
    /**
     * 匹配url规则
     * @param request 请求对象
     * @param urls 允许访问的url地址
     */
    private boolean matcherUrl(HttpServletRequest request, Set<String> urls) {
        boolean hasPermission = false;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                hasPermission = true;
                break;
            }
        }

        return hasPermission;
    }
}

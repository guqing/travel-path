package xyz.guqing.travelpath.entity.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.guqing.travelpath.entity.model.Permission;
import xyz.guqing.travelpath.entity.model.Role;

import java.util.*;

/**
 * @author guqing
 * @date 2019/8/9
 */
public class MyUserDetails implements UserDetails {
    private Integer id;
    private String username;
    private String password;

    private Role role;
    private Set<String> permissionUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if(!Objects.isNull(role) && StringUtils.isNotBlank(role.getName())) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<String> getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(Set<String> permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

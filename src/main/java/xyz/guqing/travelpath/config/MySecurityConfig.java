package xyz.guqing.travelpath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.guqing.travelpath.service.MyUserDetailsService;

/**
 * @author guqing
 * @date 2019/8/9
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().csrf().disable()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.map",
                        "/**/*.svg",
                        "/**/fonts/**"
                ).permitAll()
                .antMatchers("/pages/user/*.html","/auth/login","/auth/logout")
                .permitAll()
                .anyRequest().authenticated()
                // 对登录注册要允许匿名访问
                .and()
                .formLogin().loginPage("/pages/user/login.html")
                .loginProcessingUrl("/auth/login")
                .and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/pages/user/login.html");

    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 校private验用户
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

package xyz.guqing.travelpath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.guqing.travelpath.filter.JwtTokenFilter;
import xyz.guqing.travelpath.properties.LoginProperties;
import xyz.guqing.travelpath.properties.MySecurityAutoConfiguration;
import xyz.guqing.travelpath.security.AjaxAccessDeniedHandler;
import xyz.guqing.travelpath.security.AjaxAuthenticationEntryPoint;
import xyz.guqing.travelpath.security.AjaxAuthenticationFailureHandler;
import xyz.guqing.travelpath.security.AjaxLogoutSuccessHandler;
import xyz.guqing.travelpath.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
     */
    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;

    /**
     *  登录失败返回的 JSON 格式数据给前端（否则为 html）
     */
    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;

    @Autowired
    AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private MyUserDetailsService userService;

    @Autowired
    private MySecurityAutoConfiguration securityAutoConfiguration;

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userService );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        LoginProperties loginProperties = securityAutoConfiguration.getLoginProperties();

        httpSecurity.cors().and().csrf().disable()
                // 使用 JWT，关闭token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()

                // 对登录注册要允许匿名访问
                .antMatchers("/auth/login", "/auth/register", "/tile/**")
                .permitAll()

                .anyRequest()
                // RBAC 动态 url 认证
                .access("@rbacauthorityservice.hasPermission(request,authentication)")

                .and()
                .logout().logoutUrl(loginProperties.getLogoutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();



        // 无权访问
        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

}
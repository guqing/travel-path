package xyz.guqing.travelpath.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.properties.MySecurityAutoConfiguration;
import xyz.guqing.travelpath.entity.properties.TokenProperties;
import xyz.guqing.travelpath.service.MyUserDetailsService;
import xyz.guqing.travelpath.utils.JwtTokenUtil;
import xyz.guqing.travelpath.utils.UserTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserTokenUtil userTokenutil;

    @Autowired
    MySecurityAutoConfiguration securityProperties;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        TokenProperties tokenProperties = securityProperties.getTokenProperties();

        String authTokenHeader = request.getHeader(tokenProperties.getHeaderString());
        if (authTokenHeader != null) {
            String username = jwtTokenUtil.getUsernameFromToken(authTokenHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                MyUserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(authTokenHeader, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else if(jwtTokenUtil.canTokenBeRefreshed(authTokenHeader)){
                    // token过期了，需要刷新token
                    String newToken = userTokenutil.generateToken(userDetails);
                    System.out.println("---->颁发了新Token");
                    response.setHeader(tokenProperties.getHeaderString(), newToken);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
package com.dy.common.security;

import com.dy.common.service.UserDetailsServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.common.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //获取令牌
            String token = tokenUtils.getToken(request);
            //判断令牌是否存在并且正确
            if (null != token) {
                //根据令牌获取用户名
                String username = tokenUtils.getUserNameFromToken(token);
                //如果用户名不为空但是认证对象为空
                if (null != username && null == SecurityUtils.getAuthentication()) {
                    //根据用户名获取用户对象
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //判断token是否有效
                    if (tokenUtils.validateToken(token, userDetails)) {
                        //给SpringSecurity全局上下文设置当前登录用户对象
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        WebAuthenticationDetails webAuthenticationDetails =
                                new WebAuthenticationDetailsSource().buildDetails(request);
                        authenticationToken.setDetails(webAuthenticationDetails);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            //放行
            filterChain.doFilter(request,response);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}

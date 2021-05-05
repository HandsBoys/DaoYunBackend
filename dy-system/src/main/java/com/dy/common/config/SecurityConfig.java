package com.dy.common.config;

import com.dy.common.security.AuthenticationEntryPointImpl;
import com.dy.common.security.JwtAuthenticationFilter;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginUser;
import com.dy.service.LoginService;
import com.dy.service.SysRoleService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;

    @Autowired
    SysUserService userService;

    @Autowired
    SysRoleService roleService;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "/img/**",
                "/fonts/**",
                "/favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha",
                "/ws/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //使用了JWT，不需要csrf
        http.csrf().disable()
                //基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //上面WebConfigure已进行了放行，此处不需要放行
                .antMatchers("/login", "/logout")
                .permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().permitAll()
                .and()
                //禁用缓存
                .headers()
                .cacheControl().disable();
        // 添加jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.cors().and().csrf().disable().authorizeRequests()
//            .antMatchers("/test").authenticated()
//            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//            .antMatchers("/").permitAll()
//
//            .antMatchers("/webjars/**").permitAll()
//            // swagger
//            .antMatchers("/swagger-ui.html").permitAll()
//            .antMatchers("/swagger-resources/**").permitAll()
//            .antMatchers("/v2/api-docs").permitAll()
//            .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
//            //验证码
//            .antMatchers("/captcha.jpg").permitAll()
//            // 其他
////            .anyRequest().authenticated()
//                .anyRequest().permitAll();
////            .and()
////            .formLogin()
////            .loginProcessingUrl("/login").permitAll();

        // 未授权处理
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //获取用户登录信息
        return username -> {
            SysUser user = userService.getUserByUserName(username);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(user);
            if(null != loginUser){
                return loginUser;
            }
            return  null;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
}

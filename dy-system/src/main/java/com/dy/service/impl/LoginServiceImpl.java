package com.dy.service.impl;

import com.dy.core.utils.AjaxResult;
import com.dy.core.utils.JwtUtils;
import com.dy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录成功，返回token
     *
     * @param username
     * @param password
     */
    @Override
    public AjaxResult login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println(userDetails);
//        System.out.println(username);
//        System.out.println("password:"+ password);
//        System.out.println("getPassword:" + userDetails.getPassword());
        //判断用户是否存在
        //TODO:解决密码加密后的验证问题
        //||!passwordEncoder.matches(password,userDetails.getPassword())
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        //判断用户是否启用
        if (!userDetails.isEnabled()){
            return AjaxResult.error("用户尚未启用，请联系管理员");
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        String token = jwtUtils.generatorToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        return AjaxResult.success("登录成功",tokenMap);
    }
}

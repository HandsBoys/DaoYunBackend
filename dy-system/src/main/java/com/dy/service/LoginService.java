package com.dy.service;

import com.dy.core.utils.AjaxResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface LoginService{

    /**
     * 登录成功，返回token
     */
    public AjaxResult login(String userName, String password);

}

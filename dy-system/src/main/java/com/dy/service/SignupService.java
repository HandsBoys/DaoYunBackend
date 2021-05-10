package com.dy.service;

import com.dy.dto.login.LoginBody;

import javax.servlet.http.HttpServletRequest;

public interface SignupService {
    /**
     * 新增用户
     * @param loginBody
     * @return
     */
    boolean signUp(LoginBody loginBody);

}

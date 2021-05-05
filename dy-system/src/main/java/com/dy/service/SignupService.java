package com.dy.service;

import com.dy.dto.login.LoginBody;

import javax.servlet.http.HttpServletRequest;

public interface SignupService {
    /**
     * 新增用户
     * @param loginBody
     * @return
     */
    boolean signUp(LoginBody loginBody, HttpServletRequest request);

    //TODO:删除，未作验证码校验
    boolean signUpWithoutCode(LoginBody loginBody, HttpServletRequest request);
}

package com.dy.service;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.dto.login.TokenDto;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author cxj
 */
public interface LoginService {
    /**
     * 通过用户名和密码登录，成功则返回token
     * @param loginBody
     * @param request
     * @return AjaxResult
     */
    public TokenDto loginByPassword(LoginBody loginBody, HttpServletRequest request);

    /**
     * 使用短信验证登录，成功则返回token
     * @param loginBody
     * @param request
     * @return AjaxResult
     */
    public TokenDto loginBySms(LoginBody loginBody, HttpServletRequest request);

    /**
     * 无验证码登录
     * @param loginBody
     * @param request
     * @return
     */
    TokenDto loginWithoutCode(LoginBody loginBody, HttpServletRequest request);

    SysUser getLoginUser(Principal principal);
}

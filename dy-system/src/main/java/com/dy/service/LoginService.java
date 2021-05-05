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
     * @return AjaxResult
     */
    public TokenDto loginByPassword(LoginBody loginBody);

    /**
     * 使用短信验证登录，成功则返回token
     * @param loginBody
     * @return AjaxResult
     */
    public TokenDto loginBySms(LoginBody loginBody);

    /**
     * 无验证码登录
     * @param loginBody
     * @return
     */
    TokenDto loginWithoutCode(LoginBody loginBody );

    SysUser getLoginUser(Principal principal);
}

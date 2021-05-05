package com.dy.common.utils;

import com.dy.common.exception.CustomException;
import com.dy.dto.login.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全服务工具
 */
public class SecurityUtils {
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

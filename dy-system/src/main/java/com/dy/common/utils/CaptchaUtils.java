package com.dy.common.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
 * @author cxj
 */
@Component
public class CaptchaUtils {
    /**
     * 校验验证码
     * @param source
     * @param target:需要校验的验证码
     * @return true:校验正确;false:校验错误
     */
    public static boolean checkCaptcha(String source, String target){
        if(StringUtils.isBlank(source) || StringUtils.isBlank(target) || !target.equals(source)){
            return false;
        }
        return  true;
    }

    /**
     * 校验接受验证码的手机号和登陆的手机号是否一致
     * @param source
     * @param target:需要校验的手机号
     * @return true:校验正确;false:校验错误
     */
    public static boolean checkPhone(String source, String target){
        if(StringUtils.isBlank(source) || StringUtils.isBlank(target) || !target.equals(source)){
            return false;
        }
        return  true;
    }
}

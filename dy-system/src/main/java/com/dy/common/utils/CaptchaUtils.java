package com.dy.common.utils;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.constant.UserConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
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
     * 获取生成的验证码
     *
     * @param request
     * @return 验证码
     */
    public static String getImageCaptcha(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(GlobalConstants.IMAGE_CAPTCHA_SESSION_KEY);
    }

    public static String getSmsCaptcha(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(GlobalConstants.SMS_CAPTCHA_SESSION_KEY);
    }

    public static String getPhone(HttpServletRequest request){
        return (String) request.getSession().getAttribute(UserConstants.PHONE);
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

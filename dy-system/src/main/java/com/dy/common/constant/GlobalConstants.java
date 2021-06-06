package com.dy.common.constant;

import com.google.code.kaptcha.Constants;

/**
 * @author cxj
 */
public class GlobalConstants {
    /**
     * token
     */
    public  static final String TOKEN = "token";

    /**
     *
     */
    public static final String TOKEN_HEAD = "tokenHead";

    /**
     * 会话中的图片验证码的key
     */
    public static final String IMAGE_CAPTCHA_SESSION_KEY = "imageCaptcha";

    /**
     * 会话中的短信验证码的key
     */
    public static final String SMS_CAPTCHA_SESSION_KEY = "smsCaptcha";

    public static final String PHONE = "phone";

    /**
     * 所有权限标识
     */
    public static final String ALL_PERMISSION = "*:*:*";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";
}

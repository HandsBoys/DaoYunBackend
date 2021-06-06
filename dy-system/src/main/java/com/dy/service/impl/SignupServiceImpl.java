package com.dy.service.impl;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.redis.RedisCacheUtils;
import com.dy.common.utils.CaptchaUtils;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.service.SignupService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {
    @Autowired
    SysUserService userService;
    @Autowired
    RedisCacheUtils redisCacheUtils;

    @Override
    public boolean signUp(LoginBody loginBody) {
        String phone = loginBody.getPhone();
        String password = loginBody.getPassword();
        String code  = loginBody.getCode();
        String captcha = redisCacheUtils.getCacheObject(GlobalConstants.SMS_CAPTCHA_SESSION_KEY);

        if(!CaptchaUtils.checkPhone(redisCacheUtils.getCacheObject(GlobalConstants.PHONE),phone) ||
                !CaptchaUtils.checkCaptcha(captcha,code) ||
                StringUtils.isBlank(phone) ||
                StringUtils.isBlank(password) ||
                !userService.checkPhoneUnique(phone)){
            return false;
        }
        else{
            // 密码加密再传入数据库
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            String encodePassword = encoder.encode(password);
            SysUser user = new SysUser(phone,encodePassword);
            userService.insertUser(user);
            return true;
        }
    }
}

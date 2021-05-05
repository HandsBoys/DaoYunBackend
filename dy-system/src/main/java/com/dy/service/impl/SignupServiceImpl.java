package com.dy.service.impl;

import com.dy.common.utils.CaptchaUtils;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.service.SignupService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SignupServiceImpl implements SignupService {
    @Autowired
    SysUserService userService;
    @Override
    public boolean signUp(LoginBody loginBody, HttpServletRequest request) {
        String phone = loginBody.getPhone();
        String password = loginBody.getPassword();
        String code  = loginBody.getCode();
        String captcha = CaptchaUtils.getSmsCaptcha(request);

        //TODO:删除
        System.out.println("phone:"+phone);
        System.out.println("password:"+password);
        System.out.println("code:"+code);
        System.out.println("captcha:"+captcha);

        if(!CaptchaUtils.checkPhone(CaptchaUtils.getPhone(request),phone) ||
                !CaptchaUtils.checkCaptcha(captcha,code) ||
                StringUtils.isBlank(phone) ||
                StringUtils.isBlank(password) ||
                userService.checkPhoneUnique(phone) != null){
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

    //TODO:删除，未验证验证码
    @Override
    public boolean signUpWithoutCode(LoginBody loginBody, HttpServletRequest request) {
        String phone = loginBody.getPhone();
        String password = loginBody.getPassword();
        String code  = loginBody.getCode();
        String captcha = CaptchaUtils.getSmsCaptcha(request);

        //TODO:删除
        System.out.println("phone:"+phone);
        System.out.println("password:"+password);
        System.out.println("code:"+code);
        System.out.println("captcha:"+captcha);

        if(StringUtils.isBlank(phone) ||
                StringUtils.isBlank(password) ||
                userService.checkPhoneUnique(phone) != null){
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

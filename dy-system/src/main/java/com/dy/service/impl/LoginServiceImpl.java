package com.dy.service.impl;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.redis.RedisCacheUtils;
import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.CaptchaUtils;
import com.dy.common.utils.TokenUtils;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.dto.login.TokenDto;
import com.dy.service.LoginService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @author cxj
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 通过用户名和密码登录，成功则返回token
     *
     * @param loginBody
     * @return AjaxResult
     */
    @Override
    public AjaxResult loginByPassword(LoginBody loginBody) {
        String username = loginBody.getUserName();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();

        String captcha = (String)redisCacheUtils.getCacheObject(GlobalConstants.IMAGE_CAPTCHA_SESSION_KEY);

        String msg = null;
        // 校验验证码
        if(!CaptchaUtils.checkCaptcha(code,captcha)){
            msg = "验证码输入错误!";
            return AjaxResult.error(msg);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return AjaxResult.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            msg = "账户被禁用，请联系管理员!";
            return AjaxResult.error(msg);
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        TokenDto token = new TokenDto(tokenUtils.generatorToken(userDetails));

        return AjaxResult.success("登陆成功！",token);
    }

    /**
     * 使用短信验证登录，成功则返回token
     *
     * @param loginBody
     * @return token
     */
    @Override
    public AjaxResult loginBySms(LoginBody loginBody) {
        String phone = loginBody.getPhone();
        String code = loginBody.getCode();
        String captcha = (String)redisCacheUtils.getCacheObject(GlobalConstants.SMS_CAPTCHA_SESSION_KEY);

        String msg = null;

        if(!phone.equals((String)redisCacheUtils.getCacheObject(GlobalConstants.PHONE))){
            msg = "请使用接受验证码的手机号登录!";
            return AjaxResult.error(msg);
        }
        if(!CaptchaUtils.checkCaptcha(code,captcha)){
            msg = "验证码输入错误!";
            return AjaxResult.error(msg);
        }

        String username = userService.getUserByPhone(phone).getUserName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null ){
            throw new UsernameNotFoundException("手机号未注册");
        }
        if (!userDetails.isEnabled()){
            msg = "账户被禁用，请联系管理员!";
            return AjaxResult.error(msg);
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        TokenDto token = new TokenDto(tokenUtils.generatorToken(userDetails));

        msg = "登录成功!";

        return AjaxResult.success(msg,token);
    }


    @Override
    public SysUser getLoginUser(Principal principal) {
        String username = principal.getName();
        SysUser user = userService.getUserByUserName(username);
        user.setPassword(null);
        return user;
    }


}

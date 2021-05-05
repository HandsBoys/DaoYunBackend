package com.dy.service.impl;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.CaptchaUtils;
import com.dy.common.utils.JwtUtils;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.dto.login.TokenDto;
import com.dy.service.LoginService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;

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
    private JwtUtils jwtUtils;
    @Autowired
    private SysUserService userService;

    /**
     * 通过用户名和密码登录，成功则返回token
     *
     * @param loginBody
     * @param request
     * @return AjaxResult
     */
    @Override
    public TokenDto loginByPassword(LoginBody loginBody, HttpServletRequest request) {
        String username = loginBody.getUserName();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String captcha = CaptchaUtils.getImageCaptcha(request);

        TokenDto tokenDto = new TokenDto();

        String msg = null;
        // 校验验证码
        if(!CaptchaUtils.checkCaptcha(code,captcha)){
            msg = "验证码输入错误!";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            msg = "账户被禁用，请联系管理员!";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        String token = jwtUtils.generatorToken(userDetails);

        tokenDto.setToken(token);
        tokenDto.ajaxResult = AjaxResult.success("登录成功！");
        return tokenDto;
    }

    /**
     * 使用短信验证登录，成功则返回token
     *
     * @param loginBody
     * @param request
     * @return token
     */
    @Override
    public TokenDto loginBySms(LoginBody loginBody, HttpServletRequest request) {
        String phone = loginBody.getPhone();
        String code = loginBody.getCode();
        String captcha = CaptchaUtils.getSmsCaptcha(request);

        TokenDto tokenDto = new TokenDto();
        String msg = null;
        if(!phone.equals(CaptchaUtils.getPhone(request))){
            msg = "请使用接受验证码的手机号登录!";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }
        if(!CaptchaUtils.checkCaptcha(code,captcha)){
            msg = "验证码输入错误!";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }

        String username = userService.getUserByPhone(phone).getUserName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null ){
            throw new UsernameNotFoundException("手机号未注册");
        }
        if (!userDetails.isEnabled()){
            msg = "账户被禁用，请联系管理员!";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        String token = jwtUtils.generatorToken(userDetails);

        msg = "登录成功!";
        tokenDto.setAjaxResult(AjaxResult.success(msg));
        tokenDto.setToken(token);
        return tokenDto;
    }

    /**
     * 无验证码登录
     *
     * @param loginBody
     * @param request
     * @return
     */
    @Override
    public TokenDto loginWithoutCode(LoginBody loginBody, HttpServletRequest request) {
        String username = loginBody.getUserName();
        String password = loginBody.getPassword();

        String msg = null;
        TokenDto tokenDto = new TokenDto();
        SysUser user = userService.getUserByPhone(username);
        if( user != null){
            username = user.getUserName();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //TODO:解决密码加密后的验证问题
        if (userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            msg = "用户名或密码不正确";
            tokenDto.setAjaxResult(AjaxResult.error(msg));
            return tokenDto;
        }
        if (!userDetails.isEnabled()){
            msg = "账户被禁用，请联系管理员!";
            tokenDto.setAjaxResult(AjaxResult.error(HttpStatus.UNAUTHORIZED.value(), msg));
            return tokenDto;
        }
        //将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token并返回
        String token = jwtUtils.generatorToken(userDetails);

        msg = "登录成功!";
        tokenDto.setAjaxResult(AjaxResult.success(msg));
        tokenDto.setToken(token);
        return tokenDto;
    }

    @Override
    public SysUser getLoginUser(Principal principal) {
        String username = principal.getName();
        SysUser user = userService.getUserByUserName(username);
        user.setPassword(null);
        return user;
    }


}

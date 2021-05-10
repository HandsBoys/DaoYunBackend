package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.dto.login.TokenDto;
import com.dy.service.LoginService;
import com.dy.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * 登录操作
 * @author cxj
 */
@RestController
@RequestMapping()
public class LoginController extends BaseController{
    @Autowired
    LoginService loginService;
    @Autowired
    SysUserService userService;

    @PostMapping("/login")
    public AjaxResult<TokenDto> loginByPassword(@RequestBody LoginBody loginBody ){
        return loginService.loginByPassword(loginBody);
    }

    @PostMapping("/login/sms")
    public AjaxResult<TokenDto> loginBySms(@RequestBody LoginBody loginBody ){
        return loginService.loginBySms(loginBody);
    }

    @GetMapping("/getInfo")
    public SysUser getInfo(Principal principal){
        if(principal == null){
            return null;
        }
        return loginService.getLoginUser(principal);
    }

    @PostMapping("/logout")
    public AjaxResult logout(){
        return AjaxResult.success("退出成功！");
    }

}

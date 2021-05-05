package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysUser;
import com.dy.dto.login.LoginBody;
import com.dy.dto.login.TokenDto;
import com.dy.service.LoginService;
import com.dy.service.SysUserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


/**
 * 登录操作
 * @author cxj
 */
@Api(tags="登录操作接口")
@RestController
@RequestMapping()
public class LoginController extends BaseController{
    @Autowired
    LoginService loginService;
    @Autowired
    SysUserService userService;

    @ApiOperation(value = "用户名密码登录，成功返回token")
    @ApiImplicitParam(name = "loginBody",dataType = "LoginBody",value = "需要传输的字段:  userName(用户名),password(密码),code" +
            "(图片验证码)", required = true,paramType = "body")
    @PostMapping("/login")
    public TokenDto loginByPassword(@RequestBody LoginBody loginBody ){
        return loginService.loginByPassword(loginBody);
    }

    @ApiOperation(value = "手机号验证码登录，成功则返回token")
    @ApiImplicitParam(name = "loginBody",dataType = "LoginBody",value = "需要传输的字段:  phone(手机号),code" +
            "(短信验证码)", required = true,paramType = "body")
    @PostMapping("/login2")
    public TokenDto loginBySms(@RequestBody LoginBody loginBody ){
        return loginService.loginBySms(loginBody);
    }

    @ApiOperation(value = "获取登录用户的信息")
    @GetMapping("/getInfo")
    public SysUser getInfo(Principal principal){
        if(principal == null){
            return null;
        }
        return loginService.getLoginUser(principal);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public AjaxResult logout(){
        return AjaxResult.success("退出成功！");
    }

}

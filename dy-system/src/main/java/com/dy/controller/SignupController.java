package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.dto.login.LoginBody;
import com.dy.service.SignupService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册操作
 */
@Api(tags = "注册操作接口")
@RestController
@RequestMapping()
public class SignupController extends BaseController{
    @Autowired
    private SignupService signupService;
    /**
     * 注册
     */
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginBody",dataType = "LoginBody",value = "需要传输的字段:  phone(手机号),password(密码),code" +
                "(验证码)", required = true,paramType = "body"),
    })

    @PostMapping("/sign")
    public AjaxResult signUp(@RequestBody LoginBody loginBody, HttpServletRequest request){
        if(signupService.signUp(loginBody,request)){
            return AjaxResult.success("注册成功！");
        }
        return AjaxResult.error("注册失败！");
    }

}

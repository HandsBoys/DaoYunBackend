package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.dto.login.LoginBody;
import com.dy.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册操作
 */
@RestController
@RequestMapping()
public class SignupController extends BaseController{
    @Autowired
    private SignupService signupService;
    /**
     * 注册
     */
    @PostMapping("/sign")
    public AjaxResult signUp(@RequestBody LoginBody loginBody){
        if(signupService.signUp(loginBody)){
            return AjaxResult.success("注册成功！");
        }
        return AjaxResult.error("注册失败！");
    }

}

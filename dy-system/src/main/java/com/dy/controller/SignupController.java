package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册操作
 */
@RestController
@RequestMapping()
public class SignupController extends BaseController{
    @Autowired
    private SysUserService sysUserService;
    /**
     * 注册
     */
    @RequestMapping("/sign")
    public AjaxResult signUp(String username, String password){
        AjaxResult  ajax;
        if(username == null || password == null){
            return ajax = AjaxResult.error("请输入用户名和密码");
        }
        else if(sysUserService.Signup(username,password)){
            return ajax = AjaxResult.success("注册成功");
        }
        else{
            return ajax = AjaxResult.error("注册失败");
        }
    }
}

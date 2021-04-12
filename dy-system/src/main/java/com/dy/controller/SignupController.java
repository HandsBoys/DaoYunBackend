package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.LoginUser;
import com.dy.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册操作
 */
@Api(tags = "注册操作接口")
@RestController
@RequestMapping()
public class SignupController extends BaseController{
    @Autowired
    private SysUserService sysUserService;
    /**
     * 注册
     */
    @PostMapping("/sign")
    public AjaxResult signUp(@RequestBody LoginUser loginUser){
        AjaxResult  ajax;
        if(loginUser.getUserName() == null || loginUser.getPassword() == null){
            return ajax = AjaxResult.error("请输入用户名和密码");
        }
        else if(sysUserService.Signup(loginUser.getUserName(), loginUser.getPassword())){
            return ajax = AjaxResult.success("注册成功");
        }
        else{
            return ajax = AjaxResult.error("注册失败");
        }
    }
}

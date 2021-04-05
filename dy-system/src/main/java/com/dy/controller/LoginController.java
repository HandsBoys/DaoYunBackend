package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.LoginUser;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录和注册操作
 */
@RestController
@RequestMapping()
public class LoginController extends BaseController{
    @Autowired
    private SysUserService sysUserService;
    /**
     * 登录验证
     */
    @RequestMapping("/login")
    public AjaxResult checkLoginUser(String username,String password){
        LoginUser user = new LoginUser(username,password);
        AjaxResult ajax;
        if(sysUserService.checkLoginUser(user) != null){
            ajax = AjaxResult.success();
        }
        else{
            ajax = AjaxResult.error();
        }
        return ajax;
    }
}

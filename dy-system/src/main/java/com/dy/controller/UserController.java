package com.dy.controller;

import com.dy.core.constant.UserConstants;
import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysUser;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理的操作
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private SysUserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public List<SysUser> listUser(SysUser user){
        List<SysUser> list = userService.listUser(user);
        return list;
    }

    /**
     * 修改用户手机号，邮箱
     * @param user:userName不变
     */
    @PostMapping()
    public AjaxResult edit(SysUser user){
        // 添加：校验是否是管理员


        if(user.getPhone() != null && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已经存在");
        }
        else if (user.getEmail() != null && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱已经存在");
        }
        //更新用户信息
        System.out.println(user);
        userService.updateUser(user);
        return AjaxResult.success("修改用户'" + user.getUserName() + "'成功");
    }

    /**
     * 删除用户
     */

    /**
     * 分配角色
     */

    /**
     * 添加用户
     */
}

package com.dy.controller;

import com.dy.core.constant.UserConstants;
import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysRole;
import com.dy.domain.SysUser;
import com.dy.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理的操作
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController{
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
    @PutMapping
    public AjaxResult editUser(SysUser user){
        // 添加：校验是否是管理员

        if(user.getPhone() == null){
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，手机号码不能为空");
        }
        else if(user.getEmail() == null){
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，邮箱不能为空");
        }
        else if(user.getPhone() != null && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，手机号码已经存在");
        }
        else if (user.getEmail() != null && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("修改用户'" + user.getUsername() + "'失败，邮箱已经存在");
        }
        //更新用户信息
        System.out.println(user);
        userService.updateUser(user);
        return AjaxResult.success("修改用户'" + user.getUsername() + "'成功");
    }

    /**
     * 删除用户
     */
    //TODO:批量删除用户
    @DeleteMapping("{userId}")
    public AjaxResult deleteUser(SysUser user){
        return toAjax(userService.deleteUserById(user.getUserId()));
    }

    /**
     * 新增用户
     */
    @PostMapping
    public AjaxResult addUser(SysUser user){
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user))){
            return AjaxResult.error("用户名已使用");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return  AjaxResult.error("手机号已使用");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("邮箱已使用");
        }

        //TODO:设置创建者

        return toAjax(userService.insertUser(user));
    }
}

package com.dy.controller;

import com.dy.common.constant.UserConstants;
import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysUser;
import com.dy.dto.SysUserDto;
import com.dy.service.SysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理的操作
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController{
    @Autowired
    private SysUserService userService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("hasAuthority('system:user:list') or hasAuthority('*:*:*')")
    @GetMapping
    public List<SysUserDto> listUserAll(){
        List<SysUserDto> list = userService.listUserAll();
        return list;
    }


    /**
     * 修改用户手机号，邮箱
     * @param user:userName不变
     */
    @PreAuthorize("hasAuthority('system:user:edit') or hasAuthority('*:*:*')")
    @PutMapping
    public AjaxResult editUser(SysUser user){
        // 添加：校验是否是管理员

        if(user.getPhone() == null){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码不能为空");
        }
        else if(user.getEmail() == null){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱不能为空");
        }
        else if(user.getPhone() != null && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getPhone()))){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已经存在");
        }
        else if (user.getEmail() != null && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱已经存在");
        }
        //更新用户信息
        userService.updateUser(user);
        return AjaxResult.success("修改用户'" + user.getUserName() + "'成功");
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('system:user:remove') or hasAuthority('*:*:*')")
    @DeleteMapping("/{userIds}")
    public AjaxResult delete(@PathVariable Long[] userIds){
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("hasAuthority('system:user:add') or hasAuthority('*:*:*')")
    @PostMapping
    public AjaxResult addUser(SysUser user){
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user))){
            return AjaxResult.error("用户名已使用");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getPhone()))){
            return  AjaxResult.error("手机号已使用");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("邮箱已使用");
        }

        //TODO:设置创建者

        return toAjax(userService.insertUser(user));
    }
}

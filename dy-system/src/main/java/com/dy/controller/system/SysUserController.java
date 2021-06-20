package com.dy.controller.system;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysUser;
import com.dy.dto.system.SysUserDto;
import com.dy.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理的操作
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService userService;


    @Operation(description = "获取所有用户列表")
    @PreAuthorize("hasAuthority('system:user:list') or hasAuthority('*:*:*')")
    @GetMapping
    public List<SysUserDto> listUserAll(){
        List<SysUserDto> list = userService.listUserAll();
        return list;
    }


    @Operation(description = "修改用户信息" )
    @PreAuthorize("hasAuthority('system:user:edit') or hasAuthority('*:*:*')")
    @PostMapping
    public AjaxResult editUser(@Validated @RequestBody SysUserDto userDto){
        if(userDto.getPhone() == null){
            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，手机号码不能为空");
        }
//        else if(userDto.getEmail() == null){
//            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，邮箱不能为空");
//        }
        else if(userDto.getPhone() != null && GlobalConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(userDto.getId(),
                userDto.getPhone()))){
            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，手机号码已使用");
        }
//        else if (userDto.getEmail() != null && !userService.checkEmailUnique(userDto.getEmail())){
//            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，邮箱已经存在");
//        }
        //更新用户信息
        System.out.println(userDto);
        userService.updateUser(userDto);
        return AjaxResult.success("修改用户'" + userDto.getUserName() + "'成功");
    }

    @Operation(description = "删除用户")
    @PreAuthorize("hasAuthority('system:user:remove') or hasAuthority('*:*:*')")
    @DeleteMapping("/{userIds}")
    public AjaxResult delete(@PathVariable Long[] userIds){
        return toAjax(userService.deleteUserByIds(userIds));
    }


    @Operation(description = "新增用户")
    @PreAuthorize("hasAuthority('system:user:add') or hasAuthority('*:*:*')")
    @PutMapping
    public AjaxResult addUser(@Validated @RequestBody SysUserDto userDto){
        System.out.println(userDto);
        if(!userService.checkUserNameUnique(userDto.getUserName())){
            return AjaxResult.error("用户名已使用");
        }
        else if(!userService.checkPhoneUnique(userDto.getPhone())){
            return  AjaxResult.error("手机号已使用");
        }
        else if(!userService.checkEmailUnique(userDto.getEmail())){
            return AjaxResult.error("邮箱已使用");
        }
        return toAjax(userService.addUser(userDto));
    }
}

package com.dy.controller.client;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.ClientRoleDto;
import com.dy.dto.client.ClientUserDto;
import com.dy.service.SysRoleService;
import com.dy.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.bas.CurrencyNames_bas;

import java.util.List;

@Tag(name = "client-usesr-controller",description = "移动端用户接口")
@RestController
@RequestMapping("/client/user")
public class UserInfoController extends BaseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Operation(description = "修改用户信息" )
    @PostMapping
    public AjaxResult editUser(@Validated @RequestBody ClientUserDto userDto){
        if(userDto.getPhone() == null){
            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，手机号码不能为空");
        }
        else if(userDto.getPhone() != null && GlobalConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(userDto.getId(),
                userDto.getPhone()))){
            return AjaxResult.error("修改用户'" + userDto.getUserName() + "'失败，手机号码已使用");
        }
        else if(userDto.getUserName() != null && GlobalConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(userDto.getId(),
                userDto.getUserName()))){
            return AjaxResult.error("用户名已使用");
        }
        //更新用户信息
        return toAjax(userService.updateUser(userDto));
    }

    @Operation(description = "获取教师和学生角色信息")
    @GetMapping("/role")
    public AjaxResult<List<ClientRoleDto>> listTeacherAndStudentRoles(){
        List<ClientRoleDto> list = roleService.listTeacherAndStudentRoles();
        if(list != null && list.size() != 0){
            return  AjaxResult.success("success", list);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error", null);
    }

    @Operation(description = "修改密码")
    @PostMapping("/password")
    public AjaxResult editPassword(@RequestParam String newPassword){
        return toAjax(userService.editPassword(newPassword));
    }

}

package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysRole;
import com.dy.dto.SysRoleDto;
import com.dy.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("system/role")
public class RoleController extends BaseController{
    @Autowired
    private SysRoleService roleService;

    //TODO:获取角色列表
    @GetMapping
    public List<SysRole> listRoles(Long userId){
        List<SysRole> list = roleService.listRoles(userId);
        return list;
    }

    //TODO:修改角色信息
    @ApiOperation(value = "修改角色信息")
    @PostMapping
    public AjaxResult editRole(@Validated @RequestBody SysRoleDto roleDto){
        return toAjax(roleService.updateRole(roleDto));
    }

    //TODO:删除角色
    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/{roleIds}")
    public AjaxResult deleteRole(@PathVariable Long[] roleIds){
        return toAjax(roleService.deleteRoles(roleIds));
    }

    //TODO:新增角色
    @ApiOperation(value = "新增角色")
    @PutMapping
    public AjaxResult addRole(@Validated @RequestBody SysRoleDto roleDto){
        return toAjax(roleService.insertRole(roleDto));
    }
}

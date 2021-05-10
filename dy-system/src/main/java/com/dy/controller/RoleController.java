package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysRole;
import com.dy.dto.SysRoleDto;
import com.dy.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("system/role")
public class RoleController extends BaseController{
    @Autowired
    private SysRoleService roleService;

    @Operation
    @GetMapping
    @PreAuthorize("hasAuthority('system:role:list') or hasAuthority('*:*:*')")
    public List<SysRole> listRolesAll(){
        List<SysRole> list = roleService.listRolesAll();
        return list;
    }

    //TODO:修改角色信息
    @Operation(summary = "修改角色信息")
    @PostMapping
    @PreAuthorize("hasAuthority('system:role:edit') or hasAuthority('*:*:*')")
    public AjaxResult editRole(@Validated @RequestBody SysRoleDto roleDto){
        return toAjax(roleService.updateRole(roleDto));
    }

    //TODO:删除角色
    @Operation(summary = "删除角色", parameters = {
            @Parameter(name = "roleIds", description = "角色id的Long数组", in = ParameterIn.PATH)
    })
    @DeleteMapping("/{roleIds}")
    @PreAuthorize("hasAuthority('system:role:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteRole(@PathVariable Long[] roleIds){
        return toAjax(roleService.deleteRoles(roleIds));
    }

    //TODO:新增角色
    @PutMapping
    @PreAuthorize("hasAuthority('system:role:add') or hasAuthority('*:*:*')")
    public AjaxResult addRole(@Validated @RequestBody SysRoleDto roleDto){
        return toAjax(roleService.insertRole(roleDto));
    }
}

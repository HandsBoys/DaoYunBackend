package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysRole;
import com.dy.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/list")
    public AjaxResult listRoles(Long userId){
        List<SysRole> list = roleService.listRoles(userId);
        return AjaxResult.success(list);
    }

    //TODO:修改角色信息

    //TODO:删除角色

    //TODO:新增角色
}

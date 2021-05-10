package com.dy.service;

import com.dy.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysRoleDto;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> listRolesAll();

    int updateRole(SysRoleDto roleDto);

    int deleteRoles(Long[] roleIds);

    int insertRole(SysRoleDto roleDto);

    Set<String> selectRolePermissionByUserId(Long id);

}

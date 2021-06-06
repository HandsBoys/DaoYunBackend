package com.dy.service;

import com.dy.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.SysRoleDto;

import java.util.List;
import java.util.Set;

/**
 *
 * @author cxj
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRoleDto> listRolesAll();

    int updateRole(SysRoleDto roleDto);

    int deleteRoles(Long[] roleIds);

    int insertRole(SysRoleDto roleDto);

    Set<String> selectRolePermissionByUserId(Long id);

    public int insertRoleMenu(SysRoleDto roleDto);

    public SysRoleDto getRoleById(Long id);

}

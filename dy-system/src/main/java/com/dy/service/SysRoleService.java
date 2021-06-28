package com.dy.service;

import com.dy.dto.client.ClientRoleDto;
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

    /**
     * 根据单个角色id获取角色信息
     * @param id
     * @return
     */
    public SysRoleDto getRoleById(Long id);

    /**
     * 根据多个角色id获取角色信息
     * @param roleIds
     * @return
     */
    List<SysRoleDto> getRolesById(List<Long> roleIds);

    List<ClientRoleDto> listTeacherAndStudentRoles();
}

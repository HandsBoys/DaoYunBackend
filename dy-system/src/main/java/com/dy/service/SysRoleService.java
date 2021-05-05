package com.dy.service;

import com.dy.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysRoleDto;

import java.util.List;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> listRoles(Long userId);

    int updateRole(SysRoleDto roleDto);

    int deleteRoles(Long[] roleIds);

    int insertRole(SysRoleDto roleDto);
}

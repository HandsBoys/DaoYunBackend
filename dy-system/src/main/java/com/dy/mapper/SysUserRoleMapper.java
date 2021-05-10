package com.dy.mapper;

import com.dy.domain.SysRole;
import com.dy.domain.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.dy.domain.SysUserRole
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> getRolePermissionByUserId(Long userId);

    Long isAdmin(Long userId);
}





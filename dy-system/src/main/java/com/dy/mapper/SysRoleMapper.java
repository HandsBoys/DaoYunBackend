package com.dy.mapper;

import com.dy.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.dy.domain.SysRole
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    int deleteRolesById(Long[] roleIds);

    Long getRoleIdByRoleKey(String roleKey);
}





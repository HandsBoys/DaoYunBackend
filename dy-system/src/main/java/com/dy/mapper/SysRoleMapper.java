package com.dy.mapper;

import com.dy.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.dy.domain.SysRole
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    int deleteRolesById(Long[] roleIds);

    Long getRoleIdByRoleKey(String roleKey);
}





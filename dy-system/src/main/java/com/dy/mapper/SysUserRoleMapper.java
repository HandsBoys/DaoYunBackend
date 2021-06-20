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

    /**
     * 删除单个用户所关联的所有角色
     * @param userId
     * @return
     */
    int deleteAllByUserId(Long userId);

}





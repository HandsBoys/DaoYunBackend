package com.dy.manager.service;

import com.dy.domain.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Set;

/**
 *
 */
public interface SysUserRoleManager extends IService<SysUserRole> {
    public Set<String> getRolePermissionByUserId(Long userId);

    boolean isAdmin(Long userId);

    int getItemByRoleId(Long roleId);

    Long getRoleIdByUserId(Long userId);
}

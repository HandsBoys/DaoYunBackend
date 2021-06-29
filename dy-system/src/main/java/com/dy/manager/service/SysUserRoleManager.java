package com.dy.manager.service;

import com.dy.domain.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface SysUserRoleManager extends IService<SysUserRole> {
    public Set<String> getRolePermissionByUserId(Long userId);

    boolean isAdmin(Long userId);

    int getItemByRoleId(Long roleId);

    List<Long> getRoleIdByUserId(Long userId);

    int deleteAllByUserId(Long userId);

    void insertBatch(List<SysUserRole> list);

    void deleteTeacherAndStudentByUserId(Long userId);

    void insertDefaultRole(Long userId, Long defaultRole);
}

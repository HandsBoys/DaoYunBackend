package com.dy.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysRole;
import com.dy.domain.SysUserRole;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class SysUserRoleManagerImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
implements SysUserRoleManager {

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRolePermissionByUserId(Long userId) {
        List<SysRole> perms = baseMapper.getRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public boolean isAdmin(Long userId) {
        if(baseMapper.isAdmin(userId) == null){
            return false;
        }
        return true;
    }

    @Override
    public int getItemByRoleId(Long roleId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("role_id",roleId);
        return baseMapper.selectCount(param);
    }

    @Override
    public Long getRoleIdByUserId(Long userId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("user_id",userId)
                .select("role_id");
        SysUserRole userRole = baseMapper.selectOne(param);
        return userRole.getRoleId();
    }
}





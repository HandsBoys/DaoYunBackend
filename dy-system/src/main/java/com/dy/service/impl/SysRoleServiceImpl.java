package com.dy.service.impl;

import com.dy.domain.SysRole;
import com.dy.mapper.SysRoleMapper;
import com.dy.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询所有角色数据
     *
     * @param
     * @return List<SysRole> 角色的列表
     */
    @Override
    public List<SysRole> listRoles(Long userId) {
        return roleMapper.listRoles();
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }
}

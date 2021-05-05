package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysRole;
import com.dy.dto.SysRoleDto;
import com.dy.service.SysRoleService;
import com.dy.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
implements SysRoleService{
//TODO
    @Override
    public List<SysRole> listRoles(Long userId) {
        return null;
    }

    @Override
    public int updateRole(SysRoleDto roleDto) {
        return 0;
    }

    @Override
    public int deleteRoles(Long[] roleIds) {
        return 0;
    }

    @Override
    public int insertRole(SysRoleDto roleDto) {
        return 0;
    }
}





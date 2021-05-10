package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.exception.CustomException;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysRole;
import com.dy.dto.SysRoleDto;
import com.dy.mapper.SysUserMapper;
import com.dy.service.SysRoleService;
import com.dy.mapper.SysRoleMapper;
import com.dy.manager.service.SysUserRoleManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
implements SysRoleService{

    @Autowired
    private SysUserRoleManager userRoleManager;

    @Autowired
    private SysUserMapper userMapper;

//TODO
    @Override
    public List<SysRole> listRolesAll() {
        QueryWrapper param = new QueryWrapper<>()
                .isNotNull("id")
                .isNotNull("role_key");
        return baseMapper.selectList(param);
    }

    @Override
    public int updateRole(SysRoleDto roleDto) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDto, role);
        return baseMapper.updateById(role);
    }

    @Override
    public int deleteRoles(Long[] roleIds) {
        for(Long roleId:roleIds){
            int row = userRoleManager.getItemByRoleId(roleId);
            if(row > 0){
                throw new CustomException(String.format("角色%1$s已分配,不能删除", getRoleById(roleId).getRoleKey()));
            }
        }
        return baseMapper.deleteRolesById(roleIds);
    }

    @Override
    public int insertRole(SysRoleDto roleDto) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDto,role);
        Date createTime = new Date();
        role.setCreateTime(createTime);
        role.setCreateBy(userMapper.getIdByUserName(SecurityUtils.getUsername()));
        return baseMapper.insert(role);
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long id) {
        return userRoleManager.getRolePermissionByUserId(id);
    }

    private SysRole getRoleById(Long id){
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",id);
        return baseMapper.selectOne(param);
    }


}





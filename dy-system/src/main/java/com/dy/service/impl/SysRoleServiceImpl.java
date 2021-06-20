package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.exception.CustomException;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysRole;
import com.dy.domain.SysRoleMenu;
import com.dy.dto.system.SysRoleDto;
import com.dy.manager.service.SysRoleMenuManager;
import com.dy.mapper.SysRoleMenuMapper;
import com.dy.mapper.SysUserMapper;
import com.dy.service.SysRoleService;
import com.dy.mapper.SysRoleMapper;
import com.dy.manager.service.SysUserRoleManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysRoleMenuManager roleMenuManager;

//TODO
    @Override
    public List<SysRoleDto> listRolesAll() {
        try {
            List<SysRoleDto> ret = new ArrayList<SysRoleDto>();
            QueryWrapper param = new QueryWrapper<>()
                    .isNotNull("id")
                    .isNotNull("role_key");
            List<SysRole> roleList = baseMapper.selectList(param);
            for(SysRole role : roleList){
                Long[] menus = roleMenuManager.getMenuIdsByRoleId(role.getId());
                SysRoleDto roleDto = new SysRoleDto();
                BeanUtils.copyProperties(role,roleDto);
                roleDto.setMenuIds(menus);
                ret.add(roleDto);
            }
            return ret;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int updateRole(SysRoleDto roleDto) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDto, role);
        baseMapper.updateById(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        return insertRoleMenu(roleDto);
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
        if(isUnique(roleDto)){
            SysRole role = new SysRole();
            BeanUtils.copyProperties(roleDto,role);
            Date createTime = new Date();
            role.setCreateTime(createTime);
            role.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
            baseMapper.insert(role);
            return insertRoleMenu(roleDto);
        }else{
            return 0;
        }
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long id) {
        return userRoleManager.getRolePermissionByUserId(id);
    }

    @Override
    public int insertRoleMenu(SysRoleDto roleDto) {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : roleDto.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(baseMapper.getRoleIdByRoleKey(roleDto.getRoleKey()));
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    @Override
    public SysRoleDto getRoleById(Long id){
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",id);
        SysRoleDto roleDto = new SysRoleDto();
        BeanUtils.copyProperties(baseMapper.selectOne(param),roleDto);
        return roleDto;
    }

    @Override
    public List<SysRoleDto> getRolesById(List<Long> roleIds) {
        List<SysRoleDto> ret = new ArrayList<>();
        for(Long id:roleIds){
            ret.add(getRoleById(id));
        }
        return ret;
    }

    private boolean isUnique(SysRoleDto roleDto){
        QueryWrapper param = new QueryWrapper<>()
                .eq("role_name",roleDto.getRoleName())
                .or()
                .eq("role_key",roleDto.getRoleKey())
                .or()
                .eq("id",roleDto.getId());
        if(baseMapper.selectCount(param) > 0){
            return false;
        }
        else {
            return true;
        }
    }
}





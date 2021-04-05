package com.dy.mapper;

import com.dy.domain.SysMenu;
import com.dy.domain.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表 数据层
 * @Entity com.dy.system.domain.SysRole
 */
@Repository
public interface SysRoleMapper {
    /**
     * 获取角色列表
     */
    public List<SysRole> listRoles();
}





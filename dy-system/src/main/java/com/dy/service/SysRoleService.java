package com.dy.service;

import com.dy.domain.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色业务层
 */
public interface SysRoleService {
    /**
     * 查询所有角色数据
     */
    //TODO:增加分页功能
    public List<SysRole> listRoles(Long userId);

}

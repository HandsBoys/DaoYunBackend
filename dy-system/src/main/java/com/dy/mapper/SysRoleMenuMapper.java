package com.dy.mapper;

import com.dy.domain.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.dy.domain.SysRoleMenu
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<String> getMenuPermsByUserId(Long userId);
}





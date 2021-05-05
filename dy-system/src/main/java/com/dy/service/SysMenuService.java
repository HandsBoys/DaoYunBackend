package com.dy.service;

import com.dy.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysMenuDto;

import java.util.List;

/**
 * 菜单管理业务功能
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> listMenus(Long userId);

    List<SysMenu> buildMenuTree(List<SysMenu> menus);

    int insertMenu(SysMenuDto menuDto);

    int updateMenu(SysMenuDto menuDto);

    int deleteMenus(Long[] menuIds);
}

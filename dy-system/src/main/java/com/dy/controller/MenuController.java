package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysMenu;
import com.dy.dto.SysMenuDto;
import com.dy.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单操作
 * @author cxj
 */
@RestController
@RequestMapping("system/menu")
public class MenuController extends BaseController{
    @Autowired
    private SysMenuService menuService;

    /**
     * 按照用户的权限获取可访问的菜单项
     * @param userId
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('system:menu:list') or hasAuthority('*:*:*')")
    public List<SysMenu> listMenus(Long userId){
        //TODO:保存登录用户的信息？
        List<SysMenu> menus = menuService.listMenus(userId);
        List<SysMenu> menuTree = menuService.buildMenuTree(menus);
        return menuTree;
    }

    //TODO:新增菜单
    @PutMapping
    @PreAuthorize("hasAuthority('system:menu:add') or hasAuthority('*:*:*')")
    public AjaxResult addMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.insertMenu(menuDto));
    }

    //TODO:修改菜单
    @PostMapping
    @PreAuthorize("hasAuthority('system:menu:edit') or hasAuthority('*:*:*')")
    public AjaxResult editMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.updateMenu(menuDto));
    }

    //TODO:删除菜单
    @DeleteMapping("/{menuIds}")
    @PreAuthorize("hasAuthority('system:menu:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteMenus(@PathVariable Long[] menuIds){
        return toAjax(menuService.deleteMenus(menuIds));
    }

}

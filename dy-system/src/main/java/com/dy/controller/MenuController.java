package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysMenu;
import com.dy.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单操作
 */
@Api(tags="菜单管理接口")
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
    @GetMapping("/list")
    public AjaxResult listMenus(Long userId){
        //TODO:保存登录用户的信息？
        List<SysMenu> menus = menuService.listMenus(userId);
        List<SysMenu> menuTree = menuService.buildMenuTree(menus);
        return AjaxResult.success(menuTree);
    }

    //TODO:新增菜单

    //TODO:修改菜单

    //TODO:删除菜单

}

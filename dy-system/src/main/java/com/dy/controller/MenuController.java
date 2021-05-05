package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysMenu;
import com.dy.dto.SysMenuDto;
import com.dy.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单操作
 * @author cxj
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
    @GetMapping
    public List<SysMenu> listMenus(Long userId){
        //TODO:保存登录用户的信息？
        List<SysMenu> menus = menuService.listMenus(userId);
        List<SysMenu> menuTree = menuService.buildMenuTree(menus);
        return menuTree;
    }

    //TODO:新增菜单
    @ApiOperation(value = "增加菜单")
    @ApiImplicitParam(name = "menuDto",dataType = "SysMenuDto")
    @PutMapping
    public AjaxResult addMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.insertMenu(menuDto));
    }

    //TODO:修改菜单
    @ApiOperation(value = "修改菜单信息")
    @PostMapping
    public AjaxResult editMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.updateMenu(menuDto));
    }

    //TODO:删除菜单
    @ApiOperation(value = "删除菜单项")
    @DeleteMapping("/{menuIds}")
    public AjaxResult deleteMenus(@PathVariable Long[] menuIds){
        return toAjax(menuService.deleteMenus(menuIds));
    }

}

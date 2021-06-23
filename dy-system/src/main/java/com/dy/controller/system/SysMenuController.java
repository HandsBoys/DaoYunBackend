package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysMenu;
import com.dy.dto.system.SysMenuDto;
import com.dy.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;

    @Operation(description = "按照用户的权限获取可访问的菜单项")
    @GetMapping
    @PreAuthorize("hasAuthority('system:menu:list') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysMenu>> listMenus(){
        List<SysMenu> menus = menuService.listMenus();
        List<SysMenu> menuTree = menuService.buildMenuTree(menus);
        if(menuTree != null){
            return AjaxResult.success("success", menuTree);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error" , null);
    }

    @Operation(description = "获取所有菜单项")
    @GetMapping("/listall")
    @PreAuthorize("hasAuthority('system:menu:list') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysMenu>> listAllMenus(){
        List<SysMenu> menus = menuService.listAllMenus();
        List<SysMenu> menuTree = menuService.buildMenuTree(menus);
        if(menuTree != null){
            return AjaxResult.success("success", menuTree);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error" , null);
    }


    @PutMapping
    @PreAuthorize("hasAuthority('system:menu:add') or hasAuthority('*:*:*')")
    public AjaxResult addMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.insertMenu(menuDto));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:menu:edit') or hasAuthority('*:*:*')")
    public AjaxResult editMenu(@Validated @RequestBody SysMenuDto menuDto){
        return toAjax(menuService.updateMenu(menuDto));
    }

    @Operation(description = "删除菜单")
    @DeleteMapping("/{menuIds}")
    @PreAuthorize("hasAuthority('system:menu:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteMenus(@PathVariable Long[] menuIds){
        return toAjax(menuService.deleteMenus(menuIds));
    }

}

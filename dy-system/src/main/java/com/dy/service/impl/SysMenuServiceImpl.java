package com.dy.service.impl;


import com.dy.domain.SysMenu;
import com.dy.service.SysMenuService;
import com.dy.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> listMenus(Long userId) {
        return listMenus(new SysMenu(), userId);
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> listMenus(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;
        //TODO:按照权限显示菜单
        // 管理员显示所有菜单信息
//        if (SysUser.isAdmin(userId)) {
//            menuList = menuMapper.listMenu(menu);
//        }
//        else {
//            menuList = menuMapper.listMenuByUserId(menu,userId);
//        }
        menuList = menuMapper.listMenus(menu);
        return menuList;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysMenu dept : menus)
        {
            tempList.add(dept.getMenuId());
        }
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            SysMenu menu = (SysMenu) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 获取子节点列表
     */
    private  List<SysMenu> getChildList(List<SysMenu> list, SysMenu root){
        List<SysMenu> returnList = new ArrayList<>();
        Iterator<SysMenu> it = list.iterator();
        while(it.hasNext()){
            SysMenu n = (SysMenu) it.next();
            if(n.getParentId().longValue() == root.getMenuId().longValue()){
                returnList.add(n);
            }
        }
        return returnList;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}





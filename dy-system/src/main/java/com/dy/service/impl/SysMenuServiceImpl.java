package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysMenu;
import com.dy.dto.SysMenuDto;
import com.dy.manager.service.SysRoleMenuManager;
import com.dy.service.SysMenuService;
import com.dy.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
implements SysMenuService{

    @Autowired
    private SysRoleMenuManager roleMenuService;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> listMenus(Long userId) {
        List<SysMenu> menuList = null;
        //TODO:按照权限显示菜单
        // 管理员显示所有菜单信息
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>()
                .eq("menu_type", "M")
                .or()
                .eq("menu_type", "C");
        menuList = baseMapper.selectList(queryWrapper);
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
        List<SysMenu> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for (SysMenu menu : menus)
        {
            tempList.add(menu.getId());
        }
        for (SysMenu menu : menus) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
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

    @Override
    public int insertMenu(SysMenuDto menuDto) {
        return 0;
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
            if(n.getParentId().longValue() == root.getId().longValue()){
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


    /**
     * TODO:修改菜单项信息
     *
     * @param menuDto
     */
    @Override
    public int updateMenu(SysMenuDto menuDto) {
        return 0;
    }

    @Override
    public int deleteMenus(Long[] menuIds) {
        return 0;
    }

    @Override
    public Set<String> getMenuPermsByUserId(Long userId) {
        return roleMenuService.getMenuPermsByUserId(userId);
    }


}





package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysMenu;
import com.dy.dto.system.SysMenuDto;
import com.dy.manager.service.SysRoleMenuManager;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.service.SysMenuService;
import com.dy.mapper.SysMenuMapper;
import org.springframework.beans.BeanUtils;
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
    private SysRoleMenuManager roleMenuManager;

    @Autowired
    private SysUserRoleManager userRoleManager;

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> listMenus() {
        List<SysMenu> menuList = null;
        Long userId = SecurityUtils.getLoginUser().getUser().getId();
        if(userRoleManager.isAdmin(userId)){
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>()
                    .eq("status",0)
                    .eq("visible",1)
                    .and(i->i.eq("menu_type", "M")
                            .or()
                            .eq("menu_type", "C"));
            menuList = baseMapper.selectList(queryWrapper);
        }
        else {
            menuList = menuMapper.getMenuListByUserId(userId);
        }
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
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuDto,menu);
        menu.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        menu.setCreateTime(new Date());
        if(menu.getIsFrame()==null){
            menu.setIsFrame(true);
        }
        if(menu.getIsCache()==null){
            menu.setIsCache(false);
        }
        if(menu.getStatus()==null){
            menu.setStatus(false);
        }
        return baseMapper.insert(menu);
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
     * 修改菜单项信息
     *
     * @param menuDto
     */
    @Override
    public int updateMenu(SysMenuDto menuDto) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuDto,menu);
        menu.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        menu.setLastUpdateTime(new Date());
        return baseMapper.updateById(menu);
    }

    /**
     * TODO:删除
     * @param menuIds
     * @return
     */
    @Override
    public int deleteMenus(Long[] menuIds) {
        return baseMapper.deleteMenus(menuIds);
    }

    @Override
    public Set<String> getMenuPermsByUserId(Long userId) {
        return roleMenuManager.getMenuPermsByUserId(userId);
    }

    @Override
    public List<SysMenu> listAllMenus() {
        List<SysMenu> menuList = null;
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>()
                .isNotNull("id");
        menuList = baseMapper.selectList(queryWrapper);
        return menuList;
    }


}





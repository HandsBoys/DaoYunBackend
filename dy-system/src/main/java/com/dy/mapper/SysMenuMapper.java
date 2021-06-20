package com.dy.mapper;

import com.dy.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysMenu
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuListByUserId(Long userId);

    int deleteMenus(Long[] menuIds);
}





package com.dy.manager.service;

import com.dy.domain.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 *
 * @author cxj
 */
public interface SysRoleMenuManager extends IService<SysRoleMenu> {
    Set<String> getMenuPermsByUserId(Long userId);

    Long[] getMenuIdsByRoleId(Long roleId);
}

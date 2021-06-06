package com.dy.mapper;

import com.dy.domain.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysRoleMenu
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<String> getMenuPermsByUserId(Long userId);

    int batchRoleMenu(List<SysRoleMenu> list);

    void deleteRoleMenuByRoleId(Long id);

    List<Long> getMenuIdsByRoleId(Long roleId);
}





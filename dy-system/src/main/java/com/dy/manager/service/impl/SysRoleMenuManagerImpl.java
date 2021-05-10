package com.dy.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysRoleMenu;
import com.dy.manager.service.SysRoleMenuManager;
import com.dy.mapper.SysRoleMenuMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class SysRoleMenuManagerImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
implements SysRoleMenuManager {

    @Override
    public Set<String> getMenuPermsByUserId(Long userId) {
        List<String> perms = baseMapper.getMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}





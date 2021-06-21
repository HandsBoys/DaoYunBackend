package com.dy.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysUserDept;
import com.dy.dto.system.user.UserDeptDto;
import com.dy.manager.service.SysUserDeptManager;
import com.dy.mapper.SysUserDeptMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysUserDeptManagerImpl extends ServiceImpl<SysUserDeptMapper, SysUserDept>
implements SysUserDeptManager {

    @Override
    public void insertOne(SysUserDept record) {
        baseMapper.insert(record);
    }

    @Override
    public int deleteAllByUserId(Long userId) {
        return baseMapper.deleteAllByUserId(userId);
    }

    @Override
    public UserDeptDto getDeptByUserId(Long userId, String deptLevel) {
        return baseMapper.getDeptByUserId(userId,deptLevel);
    }
}





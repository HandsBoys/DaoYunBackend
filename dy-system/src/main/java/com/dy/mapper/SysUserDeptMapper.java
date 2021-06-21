package com.dy.mapper;

import com.dy.domain.SysUserDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.system.user.UserDeptDto;

/**
 * @Entity com.dy.domain.SysUserDept
 */
public interface SysUserDeptMapper extends BaseMapper<SysUserDept> {

    int deleteAllByUserId(Long userId);

    UserDeptDto getDeptByUserId(Long userId, String deptLevel);
}





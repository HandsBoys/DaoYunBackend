package com.dy.mapper;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.dy.domain.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Long getIdByUserName(String username);

    int deleteUserByIds(Long[] userIds);
}





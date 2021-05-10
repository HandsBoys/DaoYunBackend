package com.dy.mapper;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.dy.domain.SysUser
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    Long getIdByUserName(String username);

    int deleteUserByIds(Long[] userIds);
}





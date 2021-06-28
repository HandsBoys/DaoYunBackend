package com.dy.mapper;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysUser
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    Long getIdByUserName(String username);

    int deleteUserByIds(Long[] userIds);

    List<String> getRoleKeys(Long userId);

    String getNickname(@Param("id") Long id);

    String getIdentityNumber(@Param("id") Long id);
}





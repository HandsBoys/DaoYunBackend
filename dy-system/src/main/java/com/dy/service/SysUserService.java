package com.dy.service;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysUserDto;

import java.util.List;

/**
 *
 * @author cxj
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询用户
     * @param username
     * @return SysUser
     */
    SysUser getUserByUserName(String username);

    SysUser getUserByPhone(String phone);

    SysUser checkPhoneUnique(String phone);

    String checkEmailUnique(SysUser user);

    void updateUser(SysUser user);

    String checkUserNameUnique(SysUser user);

    /**
     * 删除用户
     * @param userIds
     */
    public int deleteUserByIds(Long[] userIds);

    List<SysUserDto> listUserAll();

    int insertUser(SysUser user);

    /**
     * 根据用户名获取用户id
     * @param username
     * @return
     */
    Long getIdByUserName(String username);

    boolean isAdmin(Long userId);
}

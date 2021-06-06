package com.dy.service;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.SysUserDto;

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

    boolean checkPhoneUnique(String phone);

    boolean checkEmailUnique(String email);

    void updateUser(SysUserDto userDto);

    boolean checkUserNameUnique(String username);

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

    /**
     * 判断是否是管理员
     * @param userId
     * @return
     */
    boolean isAdmin(Long userId);
}

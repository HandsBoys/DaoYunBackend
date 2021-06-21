package com.dy.service;

import com.dy.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.user.SysUserDto;

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

    /**
     * 检验手机号是否唯一
     * @param phone
     * @return
     */
    boolean checkPhoneUnique(String phone);

    /**
     * 除去指定用户的手机号，检验手机号是否唯一
     * @param userId
     * @param phone
     * @return
     */
    boolean checkPhoneUnique(Long userId,String phone);

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

    /**
     * 新增用户账号
     * @param userDto
     * @return
     */
    int addUser(SysUserDto userDto);

    String getNickNameById(Long teacherId);
}

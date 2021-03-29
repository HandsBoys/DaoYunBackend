package com.dy.mapper;

import com.dy.core.login.LogonUser;
import com.dy.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  com.dy.system.domain.SysUser
 */
@Repository
public interface SysUserMapper {

    public List<SysUser> listUser(SysUser sysUser);
    /**
     * 根据用户名查询用户
     */
    public SysUser getUserByUserName(String username);

    /**
     * 根据用户ID查询用户
     */
    public SysUser getUserByUserId(Long userId);

    /**
     * 新增用户
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int updateUserPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public SysUser checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phone);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);

    /**
     * 检查登录的用户名和密码是否正确
     */
    public SysUser checkLoginUser(String userName,String password);


    /**
     * 修改用户手机号
     */
    public SysUser updatePhone(String phone);

    /**
     * 修改用户邮箱
     */
    public SysUser updateEmail(String email);
}





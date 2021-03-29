package com.dy.service;

import com.dy.core.login.LogonUser;
import com.dy.domain.SysUser;

import java.util.List;


public interface SysUserService {

    /**
     * 查询用户列表
     * @param user 用于控制查询条件
     * @return
     */
    public List<SysUser> listUser(SysUser user);

    SysUser checkLoginUser(LogonUser logonUser);

    /**
     * 注册用户
     * @return 注册是否成功的信息
     */
    boolean Signup(String userName,String password);

    /**
     * 校验手机号是否唯一
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验邮箱是否唯一
     */
    String checkEmailUnique(SysUser user);

    /**
     * 校验用户名是否唯一
     */
    String checkUserNameUnique(SysUser user);

    /**
     * 修改用户信息
     */
    public int updateUser(SysUser user);

}

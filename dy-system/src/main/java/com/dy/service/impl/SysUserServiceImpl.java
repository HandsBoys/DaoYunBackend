package com.dy.service.impl;

import com.dy.core.constant.UserConstants;
import com.dy.core.login.LogonUser;
import com.dy.domain.SysUser;
import com.dy.service.SysUserService;
import com.dy.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询用户列表
     * @param user 用于控制查询条件（待添加）
     * @return
     */
    @Override
    public List<SysUser> listUser(SysUser user) {
        return userMapper.listUser(user);
    }

    @Override
    public SysUser checkLoginUser(LogonUser logonUser) {
        SysUser user = userMapper.checkLoginUser(logonUser.getUsername(),logonUser.getPassword());
        return user;
    }

    /**
     * 注册用户
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean Signup(String userName, String password) {
        if(userMapper.checkUserNameUnique(userName) != null){
            return false;
        }
        else{
            SysUser user = new SysUser(password,userName);
            userMapper.insertUser(user);
            return true;
        }
    }

    /**
     * 校验手机号是否唯一
     *
     * @param user
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        if(userMapper.checkPhoneUnique(user.getPhone()) == null){
            return UserConstants.UNIQUE;
        }
        return UserConstants.NOT_UNIQUE;
    }

    /**
     * 校验邮箱是否唯一
     *
     * @param user
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        if(userMapper.checkEmailUnique(user.getEmail()) == null){
            return UserConstants.UNIQUE;
        }
        else{
            return UserConstants.NOT_UNIQUE;
        }
    }

    /**
     * 校验用户名是否唯一
     *
     * @param user
     */
    @Override
    public String checkUserNameUnique(SysUser user) {
        if(userMapper.checkUserNameUnique(user.getUserName()) == null){
            return UserConstants.UNIQUE;
        }
        else{
            return UserConstants.NOT_UNIQUE;
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }
}





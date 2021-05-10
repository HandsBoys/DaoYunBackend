package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysUser;
import com.dy.dto.SysUserDto;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.service.SysUserService;
import com.dy.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author cxj
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
implements SysUserService{
    @Autowired
    private SysUserRoleManager userRoleService;

    @Override
    public SysUser getUserByUserName(String username) {
        QueryWrapper param = new QueryWrapper();
        param.eq("user_name",username);
        SysUser user = baseMapper.selectOne(param);
        SysUserDto ret = new SysUserDto();
        return user;
    }

    @Override
    public SysUser getUserByPhone(String phone) {
        QueryWrapper param = new QueryWrapper();
        param.eq("phone",phone);
        return baseMapper.selectOne(param);
    }

    @Override
    public SysUser checkPhoneUnique(String phone) {
        QueryWrapper param = new QueryWrapper();
        param.eq("phone",phone);
        return baseMapper.selectOne(param);
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    public void updateUser(SysUser user) {


    }

    @Override
    public String checkUserNameUnique(SysUser user) {
        return null;
    }

    /**
     * 删除用户
     *
     * @param userIds
     */
    @Override
    public int deleteUserByIds(Long[] userIds) {
        return baseMapper.deleteUserByIds(userIds);
    }

    @Override
    public List<SysUserDto> listUserAll() {
        QueryWrapper param = new QueryWrapper();
        param.isNotNull("id");
        return baseMapper.selectList(param);
    }

    @Override
    public int insertUser(SysUser user) {
        return baseMapper.insert(user);
    }

    /**
     * 根据用户名获取用户id
     *
     * @param username
     * @return
     */
    @Override
    public Long getIdByUserName(String username) {
        return baseMapper.getIdByUserName(username);
    }

    @Override
    public boolean isAdmin(Long userId) {
        return userRoleService.isAdmin(userId);
    }
}





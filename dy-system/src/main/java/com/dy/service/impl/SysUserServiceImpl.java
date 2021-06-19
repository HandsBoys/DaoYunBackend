package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysUser;
import com.dy.domain.SysUserRole;
import com.dy.dto.system.SysUserDto;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.service.SysRoleService;
import com.dy.service.SysUserService;
import com.dy.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cxj
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
implements SysUserService{
    @Autowired
    private SysUserRoleManager userRoleManager;

    @Autowired
    private SysRoleService roleService;

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
    public boolean checkPhoneUnique(String phone) {
        QueryWrapper param = new QueryWrapper();
        param.eq("phone",phone);
        if(baseMapper.selectCount(param) > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmailUnique(String email) {
        if(email != ""){
            QueryWrapper param = new QueryWrapper();
            param.eq("email",email);
            if(baseMapper.selectCount(param) > 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateUser(SysUserDto userDto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDto,user);
        baseMapper.updateById(user);
        Long roleId = userDto.getRole().getId();
        UpdateWrapper<SysUserRole> param = new UpdateWrapper<>();
        param.set("role_id",roleId);
        param.eq("user_id",userDto.getId());
        userRoleManager.update(param);
    }

    @Override
    public boolean checkUserNameUnique(String username) {
        QueryWrapper param = new QueryWrapper();
        param.eq("user_name",username);
        if(baseMapper.selectCount(param) > 0){
            return false;
        }
        return true;
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
        List<SysUser> userList = baseMapper.selectList(param);
        System.out.println(userList);
        List<SysUserDto> userDtoList = new ArrayList<>();
        for(SysUser user:userList){
            SysUserDto userDto = new SysUserDto();
            BeanUtils.copyProperties(user,userDto);
            userDto.setRole(roleService.getRoleById(userRoleManager.getRoleIdByUserId(user.getId())));
            userDtoList.add(userDto);
        }
        return userDtoList;
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
        return userRoleManager.isAdmin(userId);
    }

    @Override
    public int addUser(SysUserDto userDto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDto,user);
        user.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        user.setCreateTime(new Date());
        return baseMapper.insert(user);
    }

    @Override
    public String getNickNameById(Long teacherId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",teacherId)
                .select("nick_name");
        return baseMapper.selectOne(param).getNickName();
    }
}





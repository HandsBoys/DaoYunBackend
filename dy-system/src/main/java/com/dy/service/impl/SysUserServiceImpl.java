package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.SysConfigConstants;
import com.dy.common.utils.SecurityUtils;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysUser;
import com.dy.domain.SysUserRole;
import com.dy.dto.system.SysRoleDto;
import com.dy.dto.system.SysUserDto;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.service.SysConfigService;
import com.dy.service.SysRoleService;
import com.dy.service.SysUserService;
import com.dy.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private SysConfigService configService;

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
    public boolean checkPhoneUnique(Long userId, String phone) {
        QueryWrapper param = new QueryWrapper();
        param.eq("phone",phone);
        param.ne("id",userId);
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
        try{
            Long userId = userDto.getId();
            SysUser user = new SysUser();
            BeanUtils.copyProperties(userDto,user);
            user.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
            user.setLastUpdateTime(new Date());
            baseMapper.updateById(user);

            //删除userDto的所有角色关联
            userRoleManager.deleteAllByUserId(userId);
            //更改关联的角色
            addRoles(userId,userDto.getRoles());
        }
        catch (Exception e){
            System.out.println(e);
        }

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
        List<SysUserDto> userDtoList = new ArrayList<>();
        for(SysUser user:userList){
            SysUserDto userDto = new SysUserDto();
            BeanUtils.copyProperties(user,userDto);
            userDto.setRoles(roleService.getRolesById(userRoleManager.getRoleIdByUserId(user.getId())));
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
        try{
            SysUser user = new SysUser();
            BeanUtils.copyProperties(userDto,user);
            // 设置密码
            if(StringUtils.isEmpty(user.getPassword())){
                String password = configService.getConfigValueByKey(SysConfigConstants.PASSWORD);
                if(StringUtils.isEmpty(password)){
                    password = "123456";
                }
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
                String encodePassword = encoder.encode(password);
                user.setPassword(encodePassword);
            }

            user.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
            user.setCreateTime(new Date());
            baseMapper.insert(user);
            addRoles(getIdByUserName(userDto.getUserName()), userDto.getRoles());
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public String getNickNameById(Long teacherId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",teacherId)
                .select("nick_name");
        return baseMapper.selectOne(param).getNickName();
    }

    private void addRoles(Long userId,List<SysRoleDto> roleList){
        List<SysUserRole> urs = new ArrayList<>();
        for(SysRoleDto roleDto:roleList){
            Long roleId = roleDto.getId();
            urs.add(new SysUserRole(userId, roleId));
        }
        userRoleManager.insertBatch(urs);
    }
}





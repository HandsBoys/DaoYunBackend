package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.SysConfigConstants;
import com.dy.common.utils.SecurityUtils;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysUser;
import com.dy.domain.SysUserDept;
import com.dy.domain.SysUserRole;
import com.dy.dto.client.ClientUserDto;
import com.dy.dto.system.SysRoleDto;
import com.dy.dto.system.user.SysUserDto;
import com.dy.manager.service.SysUserDeptManager;
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

    @Autowired
    private SysUserDeptManager userDeptManager;

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

            //??????userDto?????????????????????
            userRoleManager.deleteAllByUserId(userId);
            //?????????????????????
            addRoles(userId,userDto.getRoles());

            // ?????????????????????????????????
            userDeptManager.deleteAllByUserId(userId);
            //?????????????????????
            addUserDepts(userId,userDto);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public int updateUser(ClientUserDto userDto){
        Long userId = userDto.getId();
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDto,user);
        user.setId(SecurityUtils.getLoginUser().getUser().getId());
        user.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        user.setLastUpdateTime(new Date());
        user.setStatus(false);
        user.setDelFlag("0");
        try{
            if(baseMapper.updateById(user) == 0){
                return 0;
            }
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }

        try {
            //??????userDto?????????????????????????????????
            userRoleManager.deleteTeacherAndStudentByUserId(userId);
            //?????????????????????
            addRoles(userId,userDto.getRoles());
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
        return 1;
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

    @Override
    public boolean checkUserNameUnique(Long userId, String username) {
        QueryWrapper param = new QueryWrapper();
        param.eq("user_name",username);
        param.ne("id", userId);
        if(baseMapper.selectCount(param) > 0){
            return false;
        }
        return true;
    }

    /**
     * ????????????
     *
     * @param userIds
     */
    @Override
    public int deleteUserByIds(Long[] userIds) {
        try{
            //?????????????????????????????????????????????
            for(Long userId:userIds){
                userRoleManager.deleteAllByUserId(userId);
                userDeptManager.deleteAllByUserId(userId);
            }
            return baseMapper.deleteUserByIds(userIds);
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
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

            // ?????????????????????
            userDto.setRoles(roleService.getRolesById(userRoleManager.getRoleIdByUserId(user.getId())));
            // ?????????????????????
            userDto = setDepts(user.getId(),userDto);

            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    /**
     * ???????????????????????????????????????
     * @param userId
     * @param userDto
     */
    private SysUserDto setDepts(Long userId, SysUserDto userDto) {
        userDto.setUniversity(userDeptManager.getDeptByUserId(userId,SysConfigConstants.UNIVERSITY));
        userDto.setCollege(userDeptManager.getDeptByUserId(userId,SysConfigConstants.COLLEGE));
        userDto.setSubject(userDeptManager.getDeptByUserId(userId, SysConfigConstants.SUBJECT));
        return userDto;
    }

    @Override
    public Long insertUser(SysUser user) {
        baseMapper.insert(user);
        return user.getId();
    }

    /**
     * ???????????????????????????id
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
            // ????????????
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

            Long userId = getIdByUserName(userDto.getUserName());
            // ??????????????????
            addRoles(userId, userDto.getRoles());

            // ??????????????????
            addUserDepts(userId,userDto);


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

    @Override
    public SysUser getUserInfo(Long id) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id", id);
        return baseMapper.selectOne(param);
    }

    @Override
    public List<String> getRoleKeys(Long userId) {
        return baseMapper.getRoleKeys(userId);
    }

    @Override
    public int editPassword(String newPassword) {
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            String encodePassword = encoder.encode(newPassword);
            Long userId = SecurityUtils.getLoginUser().getUser().getId();
            baseMapper.editPassword(userId,encodePassword);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    private void addRoles(Long userId,List<SysRoleDto> roleList){
        List<SysUserRole> urs = new ArrayList<>();
        for(SysRoleDto roleDto:roleList){
            Long roleId = roleDto.getId();
            urs.add(new SysUserRole(userId, roleId));
        }
        userRoleManager.insertBatch(urs);
    }

    private void addRoles(Long userId,Long[] roleList) throws Exception{
        List<SysUserRole> urs = new ArrayList<>();
        for(Long roleId:roleList){
            urs.add(new SysUserRole(userId, roleId));
        }
        try {
            userRoleManager.insertBatch(urs);
        }catch (Exception e){
            throw new Exception("????????????????????????",e);
        }
    }

    private void addUserDepts(Long userId,SysUserDto userDto){
        if(userDto.getUniversity() != null && userDto.getUniversity().getId() != null){
            addUserDept(userId,userDto.getUniversity().getId());
        }
        if(userDto.getCollege() != null && userDto.getCollege().getId() != null){
            addUserDept(userId,userDto.getCollege().getId());
        }
        if(userDto.getSubject() != null && userDto.getSubject().getId() != null){
            addUserDept(userId,userDto.getSubject().getId());
        }
    }

    private void addUserDept(Long userId, Long deptId){
        SysUserDept ud = new SysUserDept(userId,deptId);
        userDeptManager.insertOne(ud);
    }
}





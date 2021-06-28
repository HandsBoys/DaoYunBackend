package com.dy.common.service;

import com.dy.domain.SysUser;
import com.dy.dto.login.LoginUser;
import com.dy.service.SysRoleService;
import com.dy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.getUserByUserName(username);
        if(user == null){
            user = userService.getUserByPhone(username);
        }
        // TODO:对用户的异常情况进行处理
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user){
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setRoles(userService.getRoleKeys(user.getId()));
        Set<String> permissions = permissionService.getMenuPermission(user);
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}

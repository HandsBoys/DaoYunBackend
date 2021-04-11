package com.dy.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "登录接受对象",description = "")
public class LoginUser {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

//    public String getUsername() {
//        return userName;
//    }
//
//    public void updateUsername(String username) {
//        this.userName = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void updatePassword(String password) {
//        this.password = password;
//    }

//    public String getCode() {
//        return code;
//    }
//
//    public void updateCode(String code) {
//        this.code = code;
//    }
}

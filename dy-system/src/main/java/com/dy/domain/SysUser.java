package com.dy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 *
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
@ApiModel("用户类")
public class SysUser implements UserDetails, Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    @ApiModelProperty("用户id，主键")
    private Long userId;

    /**
     * 用户账号
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 性别（0男 1女 2未知）
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty("帐号状态")
    private String status;

    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String lastUpdateBy;
    /**
     * 更新时间
     */
    private Date lastUpdateTime;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户角色
     */
    private List<SysRole> roles;

    public SysUser() {
    }

    public SysUser( String userName,String password) {
        this.password = password;
        this.userName = userName;
        this.nickName = userName;
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
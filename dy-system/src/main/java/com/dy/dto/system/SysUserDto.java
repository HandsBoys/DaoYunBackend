package com.dy.dto.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @author cxj
 */
@Data
public class SysUserDto {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 学/工号
     */
    private String identityNumber;

    /**
     * 性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色
     */
    private List<SysRoleDto> roles;

    /**
     * 所属机构
     */
    private String university;
    private String college;
    private String subject;
}

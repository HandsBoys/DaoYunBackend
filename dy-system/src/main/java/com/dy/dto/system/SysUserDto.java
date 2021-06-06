package com.dy.dto.system;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author cxj
 */
@Data
public class SysUserDto {
    /**
     * 用户ID
     */
    @TableId
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
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色
     */
    private SysRoleDto role;

}

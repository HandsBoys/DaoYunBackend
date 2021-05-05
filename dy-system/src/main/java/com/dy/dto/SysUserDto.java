package com.dy.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.dy.domain.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@ApiModel
public class SysUserDto {
    /**
     * 用户ID
     */
    @TableId
    @ApiModelProperty("用户id，主键")
    private Long id;

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
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

}

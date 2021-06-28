package com.dy.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClientUserDto {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;

    /**
     * 用户账号
     */
    @Schema(description = "用户名")
    private String userName;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickName;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 学/工号
     */
    @Schema(description = "学工号")
    private String identityNumber;

    /**
     * 性别（0男 1女 2未知）
     */
    @Schema(description = "性别（0男 1女 2未知）")
    private Integer sex;

    /**
     * 角色组
     */
    @Schema(description = "角色组，存放修改的角色id")
    private Long[] roles;

}

package com.dy.dto.system.course;

import lombok.Data;

@Data
public class CourseTeacherDto {
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
}

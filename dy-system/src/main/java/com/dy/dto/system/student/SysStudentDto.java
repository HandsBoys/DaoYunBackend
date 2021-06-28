package com.dy.dto.system.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author cxj
 */
@Data
public class SysStudentDto {
    /**
     * 主键
     */
    private Long id;

    /**
     * 班课id
     */
    private Long courseId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 分数
     */
    private Long score;

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
}

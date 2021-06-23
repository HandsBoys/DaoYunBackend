package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班课表
 * @TableName sys_course
 */
@TableName(value ="sys_course")
@Data
public class SysCourse implements Serializable {
    /**
     * 课程id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程名称
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 学期
     */
    @TableField(value = "term")
    private String term;

    /**
     * 课程教师
     */
    @TableField(value = "teacher_id")
    private Long teacherId;

    /**
     * 班级id
     */
    @TableField(value = "class_id")
    private Long classId;

    /**
     * 能否加入（0：不可以加入，1：可以加入）
     */
    @TableField(value = "enable_join")
    private Boolean enableJoin;

    /**
     * 班课是否结束（0：未结束，1：结束）
     */
    @TableField(value = "finish")
    private Boolean finish;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "last_update_by")
    private Long lastUpdateBy;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
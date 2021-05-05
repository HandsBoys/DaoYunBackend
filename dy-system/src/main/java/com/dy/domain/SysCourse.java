package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程表
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
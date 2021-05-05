package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级课程表
 * @TableName sys_class_course
 */
@TableName(value ="sys_class_course")
@Data
public class SysClassCourse implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班级id
     */
    @TableField(value = "class_id")
    private Long classId;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Long courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
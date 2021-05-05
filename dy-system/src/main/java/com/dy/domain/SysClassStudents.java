package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级学生表
 * @TableName sys_class_students
 */
@TableName(value ="sys_class_students")
@Data
public class SysClassStudents implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班课id
     */
    @TableField(value = "class_id")
    private Long classId;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private Long studentId;

    /**
     * 经验值
     */
    @TableField(value = "score")
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
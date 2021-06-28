package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级学生表
 * @TableName sys_course_students
 */
@TableName(value ="sys_course_students")
@Data
public class SysCourseStudents implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班课id
     */
    @TableField(value = "course_id")
    private Long courseId;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private Long studentId;

    /**
     * 分数
     */
    @TableField(value = "score")
    private Long score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public SysCourseStudents(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = 0L;
    }
    public SysCourseStudents(){}
}
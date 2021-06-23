package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班课机构关联表

 * @TableName sys_course_dept
 */
@TableName(value ="sys_course_dept")
@Data
public class SysCourseDept implements Serializable {
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
     * 机构id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public SysCourseDept(Long courseId, Long deptId) {
        this.courseId = courseId;
        this.deptId = deptId;
    }

    public SysCourseDept() {
    }
}
package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author cxj
 */
@Data
public class CourseDto {
    /**
     * 课程id
     */
    private Long id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 学期
     */
    private String term;

    /**
     * 课程教师id
     */
    private Long teacherId;
    /**
     * 教师名字
     */
    private String teacherName;

    /**
     * 能否加入（0：可以加入，1：禁止加入）
     */
    private Boolean enableJoin;

    /**
     * 班课是否结束（0：未结束，1：结束）
     */
    private Boolean finish;

    private ClassDto classDto;

    /**
     * 班课所属单位（学校、学院）
     */
    private String dept;

    /**
     * 机构组
     */
    private List<Long> deptIds;

}

package com.dy.dto.system.course;

import com.dy.domain.SysCourse;
import com.dy.dto.client.CourseDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author cxj
 */
@Data
public class SysCourseDto {
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
     * 大学id
     */
    private Long universityId;

    /**
     * 学院id
     */
    private Long collegeId;

    /**
     * 专业id
     */
    private Long subjectId;

    /**
     * 课程教师
     */
    private Long teacherId;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 能否加入（0：可以加入，1：禁止加入）
     */
    private Boolean enableJoin;

    /**
     * 班课是否结束（0：未结束，1：结束）
     */
    private Boolean finish;

    /**
     * 课程所属班级
     */
    private CourseClassDto classDto;

    /**
     * 课程教师
     */
    private CourseTeacherDto teacher;

}

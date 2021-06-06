package com.dy.dto.client;

import lombok.Data;

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
     * 课程教师
     */
    private Long teacherId;

    /**
     * 能否加入（0：可以加入，1：禁止加入）
     */
    private Boolean enableJoin;

    private ClassDto classDto;
}

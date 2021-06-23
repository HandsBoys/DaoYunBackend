package com.dy.dto.system.course;

import lombok.Data;

@Data
public class CourseClassDto {
    /**
     * 班级id
     */
    private Long id;

    /**
     * 班级名称
     */
    private String className;
}

package com.dy.service;

import com.dy.domain.SysCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysCourseDto;

import java.util.List;

/**
 *
 */
public interface SysCourseService extends IService<SysCourse> {

    List<SysCourse> listCoursesAll();

    int deleteCourses(Long[] courseIds);

    int insertCourse(SysCourseDto courseDto);

    int updateCourse(SysCourseDto courseDto);
}

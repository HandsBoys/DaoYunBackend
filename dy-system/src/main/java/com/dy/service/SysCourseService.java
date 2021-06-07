package com.dy.service;

import com.dy.domain.SysCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.SysCourseDto;

import java.util.List;

/**
 *
 */
public interface SysCourseService extends IService<SysCourse> {

    List<SysCourse> listCoursesAll();

    int deleteCoursesByIds(Long[] courseIds);

    int insertCourse(SysCourseDto courseDto);

    int updateCourse(SysCourseDto courseDto);

    /**
     * 新建班课
     * @param course
     * @return
     */
    int addNewCourse(CourseDto course);

    List<CourseDto> listCreatedCourse();

}

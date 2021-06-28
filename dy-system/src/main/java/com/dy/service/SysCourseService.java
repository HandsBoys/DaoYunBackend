package com.dy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.domain.SysCourse;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.course.SysCourseDto;

import java.util.List;

/**
 *
 */
public interface SysCourseService extends IService<SysCourse> {

    /**
     * 获取所有的课程信息
     *
     * @return 课程构成的列表List<SysCourseDto>
     */
    List<SysCourseDto> listCoursesAll();

    List<CourseDto> listCreatedCourse();

    /**
     * 获取加入的班课
     * @return 班课信息列表
     */
    List<CourseDto> listJoinedCourse();

    List<SysCourseDto> listCreatedCourse(Long userId);

    int deleteCoursesByIds(Long[] courseIds);

    int insertCourse(SysCourseDto courseDto);

    int updateCourse(SysCourseDto courseDto);

    /**
     * 新建班课
     *
     * @param course
     * @return
     */
    int addNewCourse(CourseDto course);

    CourseDto getCourseById(Long id);

    boolean setCourseEnableJoin(Long id, Boolean enableJoin);

    boolean setCourseFinish(Long id, Boolean finish);

    List<SysCourseDto> listCourses();

    boolean enableJoinCourse(Long courseId);
}

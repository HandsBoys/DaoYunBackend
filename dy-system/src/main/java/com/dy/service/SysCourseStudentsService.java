package com.dy.service;

import com.dy.domain.SysCourseStudents;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SysCourseStudentsService extends IService<SysCourseStudents> {

    int joinCourse(Long courseId);

    int quitCourse(Long id);
}

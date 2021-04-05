package com.dy.service;

import com.dy.domain.SysCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */

public interface SysCourseService {
    /**
     * 根据用户权限查询班课
     */
    public List<SysCourse> listCourses(Long userId);
}

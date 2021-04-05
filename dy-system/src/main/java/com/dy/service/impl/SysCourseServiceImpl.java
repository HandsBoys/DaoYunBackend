package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysCourse;
import com.dy.service.SysCourseService;
import com.dy.mapper.SysCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysCourseServiceImpl implements SysCourseService{
    @Autowired
    SysCourseMapper courseMapper;

    /**
     * 根据用户权限查询班课
     *
     * @param userId
     */
    @Override
    public List<SysCourse> listCourses(Long userId) {
        return courseMapper.listCoursesAll(userId);
    }
}





package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysCourse;
import com.dy.dto.SysCourseDto;
import com.dy.service.SysCourseService;
import com.dy.mapper.SysCourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysCourseServiceImpl extends ServiceImpl<SysCourseMapper, SysCourse>
implements SysCourseService{
//TODO
    @Override
    public List<SysCourse> listCoursesAll() {
        return null;
    }

    @Override
    public int deleteCourses(Long[] courseIds) {
        return 0;
    }

    @Override
    public int insertCourse(SysCourseDto courseDto) {
        return 0;
    }

    @Override
    public int updateCourse(SysCourseDto courseDto) {
        return 0;
    }
}





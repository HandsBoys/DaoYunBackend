package com.dy.mapper;

import com.dy.domain.SysCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.client.CourseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourse
 */
@Repository
public interface SysCourseMapper extends BaseMapper<SysCourse> {

    int deleteCourseByIds(Long[] courseIds);

    List<SysCourse> listCreatedCourse(Long teacherId);

}





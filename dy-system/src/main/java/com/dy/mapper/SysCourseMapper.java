package com.dy.mapper;

import com.dy.domain.SysCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.client.CourseDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourse
 */
@Repository
public interface SysCourseMapper extends BaseMapper<SysCourse> {

    int deleteCourseByIds(Long[] courseIds);

    List<SysCourse> listCreatedCourse(Long teacherId);

    List<SysCourse> listCourseAll();

    /**
     * 获取指定课程的班级id
     * @param courseId
     * @return 班级id
     */
    Long getClassId(Long courseId);

    boolean enableJoinCourse(@Param("courseId") Long courseId);
}





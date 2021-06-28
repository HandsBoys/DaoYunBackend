package com.dy.mapper;

import com.dy.domain.SysCourse;
import com.dy.domain.SysCourseStudents;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.system.student.SysStudentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourseStudents
 */
@Repository
public interface SysCourseStudentsMapper extends BaseMapper<SysCourseStudents> {

    List<SysCourse> getCoursesByStudentId(Long studentId);

    List<SysStudentDto> getStudents(Long courseId);

    int updateScore(Long courseId, Long studentId, Long score);

    int removeStudents(@Param("courseId") Long courseId, @Param("studentIds") Long[] studentIds);

    List<Long> getStudentIds(Long courseId);

    Long getScore(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}





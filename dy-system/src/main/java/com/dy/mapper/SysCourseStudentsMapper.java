package com.dy.mapper;

import com.dy.domain.SysCourse;
import com.dy.domain.SysCourseStudents;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourseStudents
 */
@Repository
public interface SysCourseStudentsMapper extends BaseMapper<SysCourseStudents> {

    List<SysCourse> getCoursesByStudentId(Long studentId);
}





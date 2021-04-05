package com.dy.mapper;

import com.dy.domain.SysCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.system.domain.SysCourse
 */
@Repository
public interface SysCourseMapper {
    /**
     * 查询所有班课
     */
    public List<SysCourse> listCoursesAll(Long userId);

    /**
     * 根据用户的班课
     *
     * @param userId;
     * @return List<SysCourse>
     */
    public  List<SysCourse> listCoursesByUserId(Long userId);

}





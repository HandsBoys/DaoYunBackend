package com.dy.mapper;

import com.dy.domain.SysCourseDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourseDept
 */
@Repository
public interface SysCourseDeptMapper extends BaseMapper<SysCourseDept> {

    Long getDeptId(Long courseId, String deptLevel);

    int deleteByCourseIds(Long[] courseIds);

    List<Long> getDeptIds(@Param("courseId") Long courseId);

    String getdeptNameByCourseId(Long courseId, String deptLevel);

    int deleteByCourseId(Long courseId);
}





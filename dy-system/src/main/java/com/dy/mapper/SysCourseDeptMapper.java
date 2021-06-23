package com.dy.mapper;

import com.dy.domain.SysCourseDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCourseDept
 */
@Repository
public interface SysCourseDeptMapper extends BaseMapper<SysCourseDept> {

    Long getDeptId(Long courseId, String deptLevel);

    int deleteByCourseId(Long[] courseIds);

    List<Long> getDeptIds(Long id);

    String getdeptNameByCourseId(Long courseId, String deptLevel);
}





package com.dy.mapper;

import com.dy.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.dy.domain.SysDept
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

    int deleteDeptsById(Long[] deptIds);
}





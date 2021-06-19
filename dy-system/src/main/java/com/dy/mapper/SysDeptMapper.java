package com.dy.mapper;

import com.dy.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.dy.domain.SysDept
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    int deleteDeptsById(Long[] deptIds);
}





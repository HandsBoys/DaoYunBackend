package com.dy.mapper;

import com.dy.domain.SysClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.dy.domain.SysClass
 */
public interface SysClassMapper extends BaseMapper<SysClass> {

    Long getLastId();
}





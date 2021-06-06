package com.dy.mapper;

import com.dy.domain.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.dy.domain.SysConfig
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    int deleteConfigsById(Long[] configIds);
}





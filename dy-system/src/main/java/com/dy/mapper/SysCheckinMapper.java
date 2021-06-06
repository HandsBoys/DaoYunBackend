package com.dy.mapper;

import com.dy.domain.SysCheckin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCheckin
 */
public interface SysCheckinMapper extends BaseMapper<SysCheckin> {

    List<SysCheckin> getActiveCheckin(Long courseId);
}





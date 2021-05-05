package com.dy.mapper;

import com.dy.domain.SysCheckin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCheckin
 */
@Repository
public interface SysCheckinMapper extends BaseMapper<SysCheckin> {

    List<SysCheckin> getActiveCheckin(Long courseId);
}





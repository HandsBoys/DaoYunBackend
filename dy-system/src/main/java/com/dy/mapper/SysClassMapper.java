package com.dy.mapper;

import com.dy.domain.SysClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.client.ClassDto;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.dy.domain.SysClass
 */
@Repository

public interface SysClassMapper extends BaseMapper<SysClass> {

    Long getLastId();

    ClassDto getClassById(Long id);
}





package com.dy.mapper;

import com.dy.domain.SysDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.system.SysDictTypeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysDictType
 */
@Repository
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictTypeDto> listAllDictTypes();

    SysDictType getDictTypeById(Long dictId);

    int deleteDictTypeByIds(Long[] dictIds);
}





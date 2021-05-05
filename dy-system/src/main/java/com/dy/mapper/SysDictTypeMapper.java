package com.dy.mapper;

import com.dy.domain.SysDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.SysDictTypeDto;

import java.util.List;

/**
 * @Entity com.dy.domain.SysDictType
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictTypeDto> listAllDictTypes();

    SysDictType getDictTypeById(Long dictId);

    int deleteDictTypeByIds(Long[] dictIds);
}





package com.dy.mapper;

import com.dy.domain.SysDictType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.system.domain.SysDictType
 */
@Repository
public interface SysDictTypeMapper {

    List<SysDictType> listDictTypes(SysDictType dictType);

    int insertDictType(SysDictType dictType);

    SysDictType checkDictTypeUnique(String dictType);

    int deleteDictTypeById(Long dictId);

    int updateDictType(SysDictType dictType);

    int deleteDictTypeByIds(Long[] dictIds);

    SysDictType getDictTypeById(Long dictId);
}





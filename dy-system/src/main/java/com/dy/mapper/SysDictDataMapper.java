package com.dy.mapper;

import com.dy.domain.SysDictData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.system.domain.SysDictData
 */
@Repository
public interface SysDictDataMapper {

    List<SysDictData> listDictData();

    int insertDictData(SysDictData dictData);

    int countDictDataByType(String dictType);

    int updateDictData(SysDictData dictData);

    int deleteDictDataById(Long dictCode);
}





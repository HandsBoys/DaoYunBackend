package com.dy.mapper;

import com.dy.domain.SysDictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.system.SysDictDataDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysDictData
 */
@Repository
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    int countDictDataByType(String dictType);

    List<SysDictDataDto> listDictDataAll();

    int deleteDictDataByIds(Long[] dictIds);
}





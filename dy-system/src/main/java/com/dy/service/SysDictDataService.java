package com.dy.service;

import com.dy.domain.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.SysDictDataDto;

import java.util.List;

/**
 *
 * @author cxj
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictDataDto> listDictDataAll();

    int insertDictData(SysDictDataDto dictDataDto);

    int updateDictData(SysDictDataDto dictDataDto);

    int deleteDictDataByIds(Long[] dictIds);

    String checkValue(SysDictDataDto dictDataDto);

    List<SysDictDataDto> listDictDataByType(String dictType);
}

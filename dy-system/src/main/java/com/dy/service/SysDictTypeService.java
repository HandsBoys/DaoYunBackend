package com.dy.service;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysDictTypeDto;

import java.util.List;

/**
 *
 */
public interface SysDictTypeService extends IService<SysDictType> {

    List<SysDictTypeDto> listAllDictTypes();

    int insertDictType(SysDictType dictType);

    int deleteDictTypeByIds(Long[] dictIds);

    int updateDictType(SysDictTypeDto dictTypeDto);

    String checkValue(SysDictTypeDto dictTypeDto);

    String checkDictTypeUnique(String dictType);

    public String checkDictTypeUnique(SysDictTypeDto dictTypeDto);
}

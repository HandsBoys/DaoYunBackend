package com.dy.service;

import com.dy.domain.SysDictType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典类型
 */
public interface SysDictTypeService {

    /**
     * 获取数据字典类型的列表
     * @param dictType
     * @return
     */
    public List<SysDictType> listDictTypes(SysDictType dictType);

    public int insertDictType(SysDictType dict);

    public String checkDictTypeUnique(SysDictType dict);

    public String checkDictTypeUnique(String  dict);

    public int deleteDictTypeById(SysDictType dictType);

    public int updateDictType(SysDictType dictType);

    boolean checkDictTypeStatus(SysDictType dict);

    /**
     * 根据数据字典类型的id批量删除
     * @param dictIds
     * @return
     */
    int deleteDictTypeByIds(Long[] dictIds);
}

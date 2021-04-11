package com.dy.service.impl;

import com.dy.domain.SysDictData;
import com.dy.service.SysDictDataService;
import com.dy.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService{
    @Autowired
    SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> listDictData(SysDictData dictData) {
        return dictDataMapper.listDictData();
    }

    @Override
    public int insertDictData(SysDictData dictData) {
        return dictDataMapper.insertDictData(dictData);
    }

    @Override
    public int updateDictData(SysDictData dictData) {
        return dictDataMapper.updateDictData(dictData);
    }

    /**
     * 检验status是否设置正确
     * @param dictData
     * @return true：正确；false：设置错误
     */
    @Override
    public boolean checkDictTypeStatus(SysDictData dictData) {
        if(dictData.getStatus().equals("0") || dictData.getStatus().equals("1")){
            return true;
        }
        return false;
    }

    @Override
    public int deleteDictDataById(SysDictData dictData) {
        return dictDataMapper.deleteDictDataById(dictData.getDictCode());
    }
}





package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.core.constant.UserConstants;
import com.dy.core.exception.CustomException;
import com.dy.domain.SysDictType;
import com.dy.mapper.SysDictDataMapper;
import com.dy.service.SysDictTypeService;
import com.dy.mapper.SysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService{

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 获取数据字典类型的列表
     *
     * @param dictType
     * @return
     */
    @Override
    public List<SysDictType> listDictTypes(SysDictType dictType) {
        return dictTypeMapper.listDictTypes(dictType);
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        int row = dictTypeMapper.insertDictType(dictType);

        //TODO:添加缓存时考虑清除缓存

        return row;
    }

    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = dict.getDictId() == null ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if ((dictType!= null) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkDictTypeUnique(String dict) {
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict);
        if (dictType!= null)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int deleteDictTypeById(SysDictType dictType) {
        return dictTypeMapper.deleteDictTypeById(dictType.getDictId());
    }

    @Override
    public int updateDictType(SysDictType dictType) {
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     * 检查dict的状态字段是否为0或1
     * 1：禁用
     * 0：正常
     * @param dict
     * @return true：设置正确；false：设置错误
     */
    @Override
    public boolean checkDictTypeStatus(SysDictType dict) {
        if(dict.getStatus().equals("0") || dict.getStatus().equals("1")){
            return true;
        }
        return false;
    }

    /**
     * 根据数据字典类型的id批量删除
     *
     * @param dictIds
     * @return
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        for(Long dictId:dictIds){
            SysDictType dictType = getDictTypeById(dictId);
            if(dictDataMapper.countDictDataByType(dictType.getDictType()) > 0){
                // 自定义异常处理
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        return dictTypeMapper.deleteDictTypeByIds(dictIds);
    }

    private SysDictType getDictTypeById(Long dictId) {
        return dictTypeMapper.getDictTypeById(dictId);
    }
}





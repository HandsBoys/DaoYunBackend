package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.GlobalConstants;
import com.dy.common.exception.CustomException;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysDictType;
import com.dy.dto.system.SysDictTypeDto;
import com.dy.mapper.SysDictDataMapper;
import com.dy.service.SysDictTypeService;
import com.dy.mapper.SysDictTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
implements SysDictTypeService{

    @Autowired
    SysDictDataMapper dictDataMapper;

    @Override
    public String checkDictTypeUnique(String dictType) {
        QueryWrapper param = new QueryWrapper();
        param.eq("dict_type",dictType);
        SysDictType dict = baseMapper.selectOne(param);
        if (dict!= null)
        {
            return GlobalConstants.NOT_UNIQUE;
        }
        return GlobalConstants.UNIQUE;
    }

    @Override
    public String checkDictTypeUnique(SysDictTypeDto dictTypeDto) {
        Long dictId = dictTypeDto.getId() == null ? -1L : dictTypeDto.getId();
        QueryWrapper param = new QueryWrapper();
        param.eq("dict_type",dictTypeDto.getDictType());
        SysDictType dictType = baseMapper.selectOne(param);
        if ((dictType!= null) && dictType.getId().longValue() != dictId.longValue())
        {
            return GlobalConstants.NOT_UNIQUE;
        }
        return GlobalConstants.UNIQUE;
    }

    @Override
    public List<SysDictTypeDto> listAllDictTypes() {
        return baseMapper.listAllDictTypes();
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        int row = baseMapper.insert(dictType);
        return row;
    }

    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        for(Long dictId:dictIds){
            SysDictType dictType = getDictTypeById(dictId);
            if(dictDataMapper.countDictDataByType(dictType.getDictType()) > 0){
                // 自定义异常处理
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        return baseMapper.deleteDictTypeByIds(dictIds);
    }

    private SysDictType getDictTypeById(Long dictId) {
        return baseMapper.getDictTypeById(dictId);
    }

    @Override
    public int updateDictType(SysDictTypeDto dictTypeDto) {
        SysDictType dictType = new SysDictType();
        BeanUtils.copyProperties(dictTypeDto,dictType);
        dictType.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        dictType.setLastUpdateTime(new Date());
        return baseMapper.updateById(dictType);
    }


    @Override
    public String checkValue(SysDictTypeDto dictTypeDto){
        String msg = null;
        if(GlobalConstants.NOT_UNIQUE.equals(checkDictTypeUnique(dictTypeDto))){
            msg = dictTypeDto.getDictName() + "字典类型已存在";
        }
        return msg;
    }
}





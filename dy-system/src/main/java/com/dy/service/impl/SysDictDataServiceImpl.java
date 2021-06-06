package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.GlobalConstants;
import com.dy.domain.SysDictData;
import com.dy.dto.system.SysDictDataDto;
import com.dy.service.SysDictDataService;
import com.dy.mapper.SysDictDataMapper;
import com.dy.service.SysDictTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData>
implements SysDictDataService{
    @Autowired
    SysDictTypeService dictTypeService;

    @Override
    public List<SysDictDataDto> listDictDataAll() {
        return baseMapper.listDictDataAll();
    }

    @Override
    public int insertDictData(SysDictDataDto dictDataDto) {
        //TODO:设置创建者
        SysDictData dictData = new SysDictData();
        BeanUtils.copyProperties(dictDataDto,dictData);
        //TODO:System.out.println
        System.out.println(dictDataDto);
        System.out.println(dictData);

        return baseMapper.insert(dictData);
    }

    @Override
    public int updateDictData(SysDictDataDto dictDataDto) {
        SysDictData dictData = new SysDictData();
        BeanUtils.copyProperties(dictDataDto,dictData);
        return baseMapper.updateById(dictData);
    }

    @Override
    public int deleteDictDataByIds(Long[] dictIds) {
        return baseMapper.deleteDictDataByIds(dictIds);
    }

    @Override
    public String checkValue(SysDictDataDto dictDataDto){
        String msg = null;
        if(GlobalConstants.UNIQUE.equals(dictTypeService.checkDictTypeUnique(dictDataDto.getDictType()))){
            msg = dictDataDto.getDictLabel()+"的数据字典类型"+dictDataDto.getDictType()+"不存在";
        }
        return msg;
    }

    @Override
    public List<SysDictDataDto> listDictDataByType(String dictType) {
        QueryWrapper param = new QueryWrapper();
        param.eq("dict_type",dictType);
        return baseMapper.selectList(param);
    }

}





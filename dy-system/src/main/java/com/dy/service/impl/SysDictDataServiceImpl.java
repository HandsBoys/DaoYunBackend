package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.GlobalConstants;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysDictData;
import com.dy.dto.system.SysDictDataDto;
import com.dy.service.SysDictDataService;
import com.dy.mapper.SysDictDataMapper;
import com.dy.service.SysDictTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        SysDictData dictData = new SysDictData();
        BeanUtils.copyProperties(dictDataDto,dictData);
        dictData.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        dictData.setCreateTime(new Date());
        if(dictData.getIsDefault() == null){
            dictData.setIsDefault(false);
        }
        // setAllDefault(dictData.getDictType(),false);
        return baseMapper.insert(dictData);
    }

    @Override
    public int updateDictData(SysDictDataDto dictDataDto) {
        SysDictData dictData = new SysDictData();
        BeanUtils.copyProperties(dictDataDto,dictData);
        dictData.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        dictData.setLastUpdateTime(new Date());
        if(dictData.getIsDefault() == null){
            dictData.setIsDefault(true);
        }
        // setAllDefault(dictData.getDictType(),false);
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
        QueryWrapper param = new QueryWrapper<>()
            .eq("dict_type",dictType)
            .orderByAsc("dict_sort");
        return baseMapper.selectList(param);
    }

    @Override
    public boolean queryDictValue(String dictType, int dictValue) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("dict_value",dictValue)
                .eq("dict_type",dictType);
        if(baseMapper.selectCount(param) > 0){
            return false;
        }
        return true;
    }

    // 将同一字典类型的所有数据项的是否默认设置为false
//    private void setAllDefault(String dictType,boolean isDefault){
//        UpdateWrapper param = new UpdateWrapper<>()
//                .eq("dict_type",dictType)
//                .set("is_default",isDefault);
//        baseMapper.update(null,param);
//    }

}





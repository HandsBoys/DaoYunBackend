package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysDictData;
import com.dy.dto.SysDictDataDto;
import com.dy.service.SysDictDataService;
import com.dy.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数字字典信息
 */
@Api(tags="数据字典DictionaryData管理接口")
@RestController
@RequestMapping("/system/dictdata")
public class DictionaryDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @ApiOperation(value = "获取所有字典数据")
    @GetMapping("/all")
    public List<SysDictDataDto> listDictDataAll(){
        return dictDataService.listDictDataAll();
    }

    @ApiOperation(value = "根据字典类型获取字典数据")
    @ApiImplicitParam(name = "dictType",dataType = "String")
    @GetMapping
    public List<SysDictDataDto> listDictDataByType(String dictType){
        return dictDataService.listDictDataByType(dictType);
    }

    @PutMapping
    public AjaxResult add(@Validated @RequestBody SysDictDataDto dictDataDto){
        String msg = dictDataService.checkValue(dictDataDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        return toAjax(dictDataService.insertDictData(dictDataDto));
    }

    @PostMapping
    public AjaxResult edit(@Validated @RequestBody SysDictDataDto dictDataDto){
        String msg = dictDataService.checkValue(dictDataDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        //TODO:设置修改者
        return toAjax(dictDataService.updateDictData(dictDataDto));
    }

    @DeleteMapping("/{dictIds}")
    public AjaxResult delete(@PathVariable Long[] dictIds){
        return toAjax(dictDataService.deleteDictDataByIds(dictIds));
    }
}

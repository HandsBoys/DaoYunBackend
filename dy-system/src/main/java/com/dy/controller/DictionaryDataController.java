package com.dy.controller;

import com.dy.core.constant.UserConstants;
import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysDictData;
import com.dy.domain.SysDictType;
import com.dy.service.SysDictDataService;
import com.dy.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数字字典信息
 */
@RestController
@RequestMapping("/system/dict/data")
public class DictionaryDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/list")
    public List<SysDictData> list(SysDictData dictData){
        return dictDataService.listDictData(dictData);
    }

    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysDictData dictData){
        if(!dictDataService.checkDictTypeStatus(dictData)){
            return AjaxResult.error("修改字典'" + dictData.getDictLabel() + "'失败，字典类型status字段设置错误");
        }
        //判断数据字典的dict_type字段是否在数据库中存在
        if(UserConstants.UNIQUE.equals(dictTypeService.checkDictTypeUnique(dictData.getDictType()))){
            return AjaxResult.error("修改数据字典'"+dictData.getDictLabel()+"'失败，数据字典类型不存在");
        }
        return toAjax(dictDataService.insertDictData(dictData));
    }

    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysDictData dictData){
        if(!dictDataService.checkDictTypeStatus(dictData)){
            return AjaxResult.error("修改字典'" + dictData.getDictLabel() + "'失败，字典类型status字段设置错误");
        }
        if(UserConstants.UNIQUE.equals(dictTypeService.checkDictTypeUnique(dictData.getDictType()))){
            return AjaxResult.error("修改数据字典'"+dictData.getDictLabel()+"'失败，数据字典类型不存在");
        }
        //TODO:设置修改者
        return toAjax(dictDataService.updateDictData(dictData));
    }

    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody SysDictData dictData){
        return toAjax(dictDataService.deleteDictDataById(dictData));
    }
}

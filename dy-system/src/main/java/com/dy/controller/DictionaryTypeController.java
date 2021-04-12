package com.dy.controller;

import com.dy.core.constant.UserConstants;
import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysDictType;
import com.dy.service.SysDictTypeService;
import io.swagger.annotations.Api;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类型信息
 */
@Api(tags="数据字典类型DictionaryType管理接口")
@RestController
@RequestMapping("/system/dict/type")
public class DictionaryTypeController extends BaseController{

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/list")
    public List<SysDictType> list(SysDictType dictType){
        List<SysDictType> list = dictTypeService.listDictTypes(dictType);
        return list;
    }

    //TODO:封装对传入数据的检查SysDictType
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysDictType dictType){
        if(!dictTypeService.checkDictTypeStatus(dictType)){
            return AjaxResult.error("修改字典'" + dictType.getDictName() + "'失败，字典类型status字段设置错误");
        }
        if(UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dictType))){
            return AjaxResult.error("新增字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }
        //TODO:设置创建者
//        dict.setCreateBy();
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    /**
     * 单个删除数据字典类型
     * @param dictType
     * @return
     */
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody SysDictType dictType){
        return toAjax(dictTypeService.deleteDictTypeById(dictType));
    }

    /**
     * 批量删除数据字典类型
     * @param dictIds
     * @return
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] dictIds){
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysDictType dictType){
        if(!dictTypeService.checkDictTypeStatus(dictType)){
            return AjaxResult.error("修改字典'" + dictType.getDictName() + "'失败，字典类型status字段设置错误");
        }
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dictType)))
        {
            return AjaxResult.error("修改字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }

        //TODO:设置修改者
        //dict.setUpdateBy(SecurityUtils.getUsername());

        return toAjax(dictTypeService.updateDictType(dictType));
    }

}

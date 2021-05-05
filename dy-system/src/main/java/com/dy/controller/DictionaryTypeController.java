package com.dy.controller;

import com.dy.common.constant.UserConstants;
import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysDictType;
import com.dy.dto.SysDictTypeDto;
import com.dy.service.SysDictTypeService;
import com.dy.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类型信息
 */
@Api(tags="数据字典类型DictionaryType管理接口")
@RestController
@RequestMapping("/system/dicttype")
public class DictionaryTypeController extends BaseController{

    @Autowired
    private SysDictTypeService dictTypeService;
    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "获取所有数据字典类型")
    @GetMapping
    public List<SysDictTypeDto> list(){
        List<SysDictTypeDto> list = dictTypeService.listAllDictTypes();
        return list;
    }

    @ApiOperation(value = "批量删除数据字典类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictIds",paramType = "path")
    })
    @DeleteMapping("/{dictIds}")
    public AjaxResult delete(@PathVariable Long[] dictIds){
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    @PutMapping
    public AjaxResult add(@Validated @RequestBody SysDictTypeDto dictTypeDto){
        String msg = dictTypeService.checkValue(dictTypeDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        SysDictType dictType = new SysDictType();
        BeanUtils.copyProperties(dictTypeDto,dictType);
        System.out.println(dictType);
        //TODO:设置创建者
        // dictType.setCreateBy(userService.getIdByUserName(SecurityUtils.getUsername()));
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    @PostMapping
    public AjaxResult edit(@Validated @RequestBody SysDictTypeDto dictTypeDto){
        String msg = dictTypeService.checkValue(dictTypeDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        //TODO:设置修改者
        //dict.setUpdateBy(SecurityUtils.getUsername());

        return toAjax(dictTypeService.updateDictType(dictTypeDto));
    }

}

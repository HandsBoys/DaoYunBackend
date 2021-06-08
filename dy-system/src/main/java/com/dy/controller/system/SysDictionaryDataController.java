package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.StringUtils;
import com.dy.controller.BaseController;
import com.dy.dto.system.SysDictDataDto;
import com.dy.service.SysDictDataService;
import com.dy.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数字字典信息
 */

@RestController
@RequestMapping("/system/dictdata")
public class SysDictionaryDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('system:dict:list') or hasAuthority('*:*:*')")
    public List<SysDictDataDto> listDictDataAll(){
        return dictDataService.listDictDataAll();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('system:dict:query') or hasAuthority('*:*:*')")
    public List<SysDictDataDto> listDictDataByType(String dictType){
        return dictDataService.listDictDataByType(dictType);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:dict:add') or hasAuthority('*:*:*')")
    public AjaxResult add(@Validated @RequestBody SysDictDataDto dictDataDto){
        String msg = dictDataService.checkValue(dictDataDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        return toAjax(dictDataService.insertDictData(dictDataDto));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:dict:edit') or hasAuthority('*:*:*')")
    public AjaxResult edit(@Validated @RequestBody SysDictDataDto dictDataDto){
        String msg = dictDataService.checkValue(dictDataDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        return toAjax(dictDataService.updateDictData(dictDataDto));
    }

    @DeleteMapping("/{dictIds}")
    @PreAuthorize("hasAuthority('system:dict:remove') or hasAuthority('*:*:*')")
    public AjaxResult delete(@PathVariable Long[] dictIds){
        return toAjax(dictDataService.deleteDictDataByIds(dictIds));
    }

    @GetMapping("/query-dict-value")
    @PreAuthorize("hasAuthority('system:dict:add') or hasAuthority('system:dict:edit') or hasAuthority('*:*:*')")
    public boolean queryDictValue(String dictType, int dictValue){
        return dictDataService.queryDictValue(dictType,dictValue);
    }
}

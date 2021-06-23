package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.StringUtils;
import com.dy.controller.BaseController;
import com.dy.dto.system.SysDictDataDto;
import com.dy.service.SysDictDataService;
import com.dy.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public AjaxResult<List<SysDictDataDto>> listDictDataAll(){
        List<SysDictDataDto> ret = dictDataService.listDictDataAll();
        if(ret != null){
            return AjaxResult.success("成功",ret);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "失败", null);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('system:dict:query') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysDictDataDto>> listDictDataByType(String dictType){
        List<SysDictDataDto> ret = dictDataService.listDictDataByType(dictType);
        if(ret != null){
            return AjaxResult.success("成功",ret);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "失败", null);
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

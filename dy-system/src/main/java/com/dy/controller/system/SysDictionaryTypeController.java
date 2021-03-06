package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.common.utils.SecurityUtils;
import com.dy.controller.BaseController;
import com.dy.domain.SysDictType;
import com.dy.dto.system.SysDictTypeDto;
import com.dy.service.SysDictTypeService;
import com.dy.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 数据字典类型信息
 */
@RestController
@RequestMapping("/system/dicttype")
public class SysDictionaryTypeController extends BaseController {

    @Autowired
    private SysDictTypeService dictTypeService;
    @Autowired
    private SysUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:dict:list') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysDictTypeDto>> list(){
        List<SysDictTypeDto> list = dictTypeService.listAllDictTypes();
        if(list != null){
            return  AjaxResult.success("success",list);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error" ,null);
    }


    @DeleteMapping("/{dictIds}")
    @PreAuthorize("hasAuthority('system:dict:remove') or hasAuthority('*:*:*')")
    public AjaxResult delete(@PathVariable Long[] dictIds){
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:dict:add') or hasAuthority('*:*:*')")
    public AjaxResult add(@Validated @RequestBody SysDictTypeDto dictTypeDto){
        String msg = dictTypeService.checkValue(dictTypeDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        SysDictType dictType = new SysDictType();
        BeanUtils.copyProperties(dictTypeDto,dictType);
        dictType.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        dictType.setCreateTime(new Date());
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:dict:edit') or hasAuthority('*:*:*')")
    public AjaxResult edit(@Validated @RequestBody SysDictTypeDto dictTypeDto){
        String msg = dictTypeService.checkValue(dictTypeDto);
        if(msg != null){
            return AjaxResult.error(msg);
        }
        return toAjax(dictTypeService.updateDictType(dictTypeDto));
    }

}

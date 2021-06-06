package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysDictType;
import com.dy.dto.system.SysDictTypeDto;
import com.dy.service.SysDictTypeService;
import com.dy.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public List<SysDictTypeDto> list(){
        List<SysDictTypeDto> list = dictTypeService.listAllDictTypes();
        return list;
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
        System.out.println(dictType);
        //TODO:设置创建者
        // dictType.setCreateBy(userService.getIdByUserName(SecurityUtils.getUsername()));
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:dict:edit') or hasAuthority('*:*:*')")
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

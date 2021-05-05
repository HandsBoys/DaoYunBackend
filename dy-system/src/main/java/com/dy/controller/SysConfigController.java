package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysConfig;
import com.dy.dto.SysConfigDto;
import com.dy.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统参数接口")
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController{
    @Autowired
    private SysConfigService configService;

    @ApiOperation(value = "获取所有系统参数信息的接口")
    @GetMapping
    public List<SysConfig> list(SysConfigDto configDto){
        List<SysConfig> list = configService.listSysConfig(configDto);
        return list;
    }
    
    @ApiOperation(value = "修改系统参数信息")
    @PostMapping
    public AjaxResult edit(SysConfigDto configDto){
        return toAjax(configService.updateSysConfig(configDto));
    }

}

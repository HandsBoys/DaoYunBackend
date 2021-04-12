package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysConfig;
import com.dy.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统参数接口")
@RestController
@RequestMapping("/system/config")
public class SysConfigController {
    @Autowired
    private SysConfigService configService;

    @ApiOperation(value = "获取所有系统参数信息的接口")
    @GetMapping("/list")
    public AjaxResult list(SysConfig config){
        List<SysConfig> list = configService.listSysConfig(config);
        return AjaxResult.success(list);
    }
}

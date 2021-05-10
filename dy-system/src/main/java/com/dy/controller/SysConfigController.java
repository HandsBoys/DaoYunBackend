package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysConfig;
import com.dy.dto.SysConfigDto;
import com.dy.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController{
    @Autowired
    private SysConfigService configService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:config:list') or hasAuthority('*:*:*')")
    public List<SysConfig> list(SysConfigDto configDto){
        List<SysConfig> list = configService.listSysConfig(configDto);
        return list;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:config:edit') or hasAuthority('*:*:*')")
    public AjaxResult edit(SysConfigDto configDto){
        return toAjax(configService.updateSysConfig(configDto));
    }

}

package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.system.SysConfigDto;
import com.dy.service.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService configService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:config:list') or hasAuthority('*:*:*')")
    public List<SysConfigDto> list(){
        List<SysConfigDto> list = configService.listSysConfig();
        return list;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:config:edit') or hasAuthority('*:*:*')")
    public AjaxResult edit(@Validated @RequestBody SysConfigDto configDto){
        return toAjax(configService.updateSysConfig(configDto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:config:add') or hasAuthority('*:*:*')")
    public AjaxResult add(@Validated @RequestBody SysConfigDto configDto){
        return toAjax(configService.addSysConfig(configDto));
    }

    @DeleteMapping("/{configIds}")
    @PreAuthorize("hasAuthority('system:config:remove') or hasAuthority('*:*:*')")
    public AjaxResult delete(@PathVariable Long[] configIds){
        return toAjax(configService.deleteConfigs(configIds));
    }

    @Operation(summary = "查询configKey是否已经存在",description = "configKey存在：false，不存在：true")
    @GetMapping("/query-config-key")
    @PreAuthorize("hasAuthority('system:config:add') or hasAuthority('system:config:edit') or hasAuthority('*:*:*')")
    public boolean queryConfigKeyUnique(String configKey){
        return configService.queryConfigKeyUnique(configKey);
    }
}

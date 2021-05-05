package com.dy.service;

import com.dy.domain.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.SysConfigDto;

import java.util.List;

/**
 *
 */
public interface SysConfigService extends IService<SysConfig> {

    List<SysConfig> listSysConfig(SysConfigDto configDto);

    int updateSysConfig(SysConfigDto configDto);
}

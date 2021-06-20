package com.dy.service;

import com.dy.domain.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.SysConfigDto;

import java.util.List;

/**
 *
 */
public interface SysConfigService extends IService<SysConfig> {

    List<SysConfigDto> listSysConfig();

    int updateSysConfig(SysConfigDto configDto);

    /**
     * 增加系统参数项
     * @param configDto
     * @return
     */
    int addSysConfig(SysConfigDto configDto);

    int deleteConfigs(Long[] configIds);

    boolean queryConfigKeyUnique(String configKey);

    /**
     * 根据config_key获取config_value
     * @param configKey
     * @return
     */
    String getConfigValueByKey(String configKey);
}

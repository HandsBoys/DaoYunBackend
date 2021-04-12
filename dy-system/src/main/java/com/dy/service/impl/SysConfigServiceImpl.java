package com.dy.service.impl;

import com.dy.domain.SysConfig;
import com.dy.mapper.SysConfigMapper;
import com.dy.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public List<SysConfig> listSysConfig(SysConfig config) {
        return configMapper.listSysConfig(config);
    }
}

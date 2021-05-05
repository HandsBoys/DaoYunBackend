package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysConfig;
import com.dy.dto.SysConfigDto;
import com.dy.service.SysConfigService;
import com.dy.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig>
implements SysConfigService{

    //TODO
    @Override
    public List<SysConfig> listSysConfig(SysConfigDto configDto) {
        return null;
    }

    @Override
    public int updateSysConfig(SysConfigDto configDto) {
        return 0;
    }
}





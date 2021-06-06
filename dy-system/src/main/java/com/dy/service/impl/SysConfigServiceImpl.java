package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysConfig;
import com.dy.dto.system.SysConfigDto;
import com.dy.service.SysConfigService;
import com.dy.mapper.SysConfigMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig>
implements SysConfigService{

    //TODO
    @Override
    public List<SysConfigDto> listSysConfig() {
        try{
            List<SysConfigDto> ret = new ArrayList<>();
            QueryWrapper param = new QueryWrapper<>()
                    .isNotNull("id")
                    .isNotNull("config_key");
            List<SysConfig> sysConfigList = baseMapper.selectList(param);
            for(SysConfig config:sysConfigList){
                SysConfigDto configDto = new SysConfigDto();
                BeanUtils.copyProperties(config,configDto);
                ret.add(configDto);
            }
            return ret;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int updateSysConfig(SysConfigDto configDto) {
        SysConfig config = new SysConfig();
        BeanUtils.copyProperties(configDto,config);
        config.setUpdateTime(new Date());
        config.setUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        return baseMapper.updateById(config);
    }

    @Override
    public int addSysConfig(SysConfigDto configDto) {
        SysConfig config = new SysConfig();
        BeanUtils.copyProperties(configDto,config);
        System.out.println(config);
        config.setCreateTime(new Date());
        config.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        return baseMapper.insert(config);
    }

    @Override
    public int deleteConfigs(Long[] configIds) {
        return baseMapper.deleteConfigsById(configIds);
    }
}





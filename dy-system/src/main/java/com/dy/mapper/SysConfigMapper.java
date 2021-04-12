package com.dy.mapper;

import com.dy.domain.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysConfig
 */
@Repository
public interface SysConfigMapper {

    List<SysConfig> listSysConfig(SysConfig config);
}





package com.dy.service;

import com.dy.domain.SysClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SysClassService extends IService<SysClass> {

    int insertClass(SysClass sysClass);

    Long getLastId();
}

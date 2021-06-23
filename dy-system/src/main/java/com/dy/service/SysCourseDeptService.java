package com.dy.service;

import com.dy.domain.SysCourseDept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SysCourseDeptService extends IService<SysCourseDept> {

    String getDeptName(Long id);
}

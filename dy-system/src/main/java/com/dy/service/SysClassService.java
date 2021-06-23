package com.dy.service;

import com.dy.domain.SysClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SysClassService extends IService<SysClass> {

    int insertClass(SysClass sysClass);

    Long getLastId();

    /**
     * 获取班级的信息
     * @param classId 班级id
     * @return 班级信息
     */
    SysClass getClassInfo(Long classId);

    int deleteById(Long id);
}

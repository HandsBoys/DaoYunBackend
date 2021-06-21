package com.dy.manager.service;

import com.dy.domain.SysUserDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.system.user.UserDeptDto;

/**
 *
 */
public interface SysUserDeptManager extends IService<SysUserDept> {

    /**
     * 新增一条记录
     * @param record
     */
    void insertOne(SysUserDept record);

    int deleteAllByUserId(Long userId);

    /**
     * 获取用户所属的机构
     * @param userId 用户Id
     * @param deptLevel 机构级别
     * @return
     */
    UserDeptDto getDeptByUserId(Long userId, String deptLevel);

}

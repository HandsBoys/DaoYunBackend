package com.dy.service;

import com.dy.domain.SysCheckin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysCheckinService extends IService<SysCheckin> {

    int startCheckin(SysCheckin checkin);

    /**
     * 获取发起的未结束签到
     * @param courseId
     * @return
     */
    List<SysCheckin> getActiveCheckin(Long courseId);
}

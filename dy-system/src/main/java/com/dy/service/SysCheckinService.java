package com.dy.service;

import com.dy.domain.SysCheckin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.CheckinTaskDto;

import java.util.List;

/**
 *
 */
public interface SysCheckinService extends IService<SysCheckin> {

    Long startCheckin(CheckinTaskDto checkin);

    /**
     * 获取发起的未结束签到
     * @param courseId
     * @return
     */
    List<CheckinTaskDto> getActiveCheckin(Long courseId);

    int finishCheckin(Long checkinId);

    List<CheckinTaskDto> getAllCheckinTask(Long courseId);

    public void timing(Long checkinId, Long deadLine);
}

package com.dy.service;

import com.dy.domain.SysCheckinInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysCheckinInfoService extends IService<SysCheckinInfo> {

    /**
     *
     * @param id:发起签到的实例id
     * @param checkinInfo
     * @return
     */
    boolean checkIn(Long id,SysCheckinInfo checkinInfo);

    List<SysCheckinInfo> listByStartCheckinId(Long startCheckinId);
}

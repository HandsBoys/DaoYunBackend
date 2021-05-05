package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysCheckin;
import com.dy.domain.SysCheckinInfo;
import com.dy.service.SysCheckinInfoService;
import com.dy.mapper.SysCheckinInfoMapper;
import com.dy.service.SysCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysCheckinInfoServiceImpl extends ServiceImpl<SysCheckinInfoMapper, SysCheckinInfo>
implements SysCheckinInfoService{
    @Autowired
    SysCheckinService checkinService;

    /**
     * 限时签到
     * @param checkinInfo
     * @return
     */
    private boolean limitTimeCheckIn(SysCheckinInfo checkinInfo){
        SysCheckin checkinExample = checkinService.getById(checkinInfo.getStartCheckinId());
        if(checkinExample.getIsFinish() || checkinInfo.getCheckinTime().compareTo(checkinExample.getEndTime()) == 1){
            return false;
        }
        baseMapper.insert(checkinInfo);
        return true;
    }

    /**
     * 一键签到
     * @param checkinInfo
     * @return
     */
    private boolean directCheckIn(SysCheckinInfo checkinInfo){
        SysCheckin checkinExample = checkinService.getById(checkinInfo.getStartCheckinId());
        if(checkinExample.getIsFinish()){
            return false;
        }
        baseMapper.insert(checkinInfo);
        return true;
    }

    /**
     * @param id          :发起签到的实例id
     * @param checkinInfo
     * @return
     */
    @Override
    public boolean checkIn(Long id, SysCheckinInfo checkinInfo) {
        SysCheckin checkin = checkinService.getById(id);
        if(checkin.getType() == 0){
            return directCheckIn(checkinInfo);
        }else if(checkin.getType() == 1){
            return limitTimeCheckIn(checkinInfo);
        }
        return false;
    }

    @Override
    public List<SysCheckinInfo> listByStartCheckinId(Long startCheckinId) {
        QueryWrapper param = new QueryWrapper();
        param.eq("start_checkin_id",startCheckinId);
        return baseMapper.selectList(param);
    }
}





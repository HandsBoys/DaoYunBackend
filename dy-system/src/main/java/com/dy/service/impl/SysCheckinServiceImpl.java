package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.CheckinConstants;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysCheckin;
import com.dy.dto.client.CheckinTaskDto;
import com.dy.mapper.SysCheckinStudentMapper;
import com.dy.service.SysCheckinService;
import com.dy.mapper.SysCheckinMapper;
import com.dy.service.SysCheckinStudentService;
import com.dy.service.SysCourseStudentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class SysCheckinServiceImpl extends ServiceImpl<SysCheckinMapper, SysCheckin>
implements SysCheckinService{

    @Autowired
    private SysCourseStudentsService courseStudentsService;

    @Autowired
    private SysCheckinStudentMapper sysCheckinStudentMapper;

    @Override
    public int finishCheckin(Long checkinId) {
        return baseMapper.setFinish(checkinId);
    }

    @Override
    public List<CheckinTaskDto> getAllCheckinTask(Long courseId) {
        return baseMapper.getAllCheckinTask(courseId);
    }

    @Override
    public Long startCheckin(CheckinTaskDto checkin) {
        SysCheckin sysCheckin = new SysCheckin();
        checkin.setEndTime(null);
        checkin.setStartTime(null);
        checkin.setIsFinish(null);

        Long endTime;
        try {
            BeanUtils.copyProperties(checkin, sysCheckin);
            // 设置签到发起者
            sysCheckin.setTeacherId(SecurityUtils.getLoginUser().getUser().getId());
            // 设置发起时间
            sysCheckin.setStartTime(new Date());
            // 设置结束时间
            endTime = sysCheckin.getStartTime().getTime() + checkin.getLimitTime() * CheckinConstants.MINUTE;
            if(checkin.getType() == CheckinConstants.LIMIT_TIME){
                sysCheckin.setEndTime(new Date(endTime));
            }
            // 设置结束标识
            sysCheckin.setIsFinish(false);
            baseMapper.insert(sysCheckin);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        // 初始化签到记录
        Long checkinId = sysCheckin.getId();
        Long courseId = sysCheckin.getCourseId();
        try{
            List<Long> studentIds = courseStudentsService.getStudentIds(courseId);
            if(studentIds != null){
                sysCheckinStudentMapper.initRecords(checkinId, studentIds);
            }
        }catch (Exception e){
            System.out.println(e);
        }

//        try{
//            if(checkin.getType() == CheckinConstants.LIMIT_TIME){
//                timing(checkinId, endTime);
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
        return checkinId;
    }

    @Override
    public List<CheckinTaskDto> getActiveCheckin(Long courseId) {
        return baseMapper.getActiveCheckin(courseId);
    }

    @Override
    public void timing(Long checkinId, Long deadLine){
        Long current = System.currentTimeMillis();
        try {
            while(current < deadLine){
                Thread.sleep(1000);
                current = System.currentTimeMillis();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baseMapper.setFinish(checkinId);
        baseMapper.setEndTime(checkinId, new Date());
    }
}





package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysCheckinStudent;
import com.dy.dto.client.CheckinStudentRecordDto;
import com.dy.dto.client.CheckinStudentTaskDto;
import com.dy.dto.client.StudentCheckinInfo;
import com.dy.mapper.SysCheckinMapper;
import com.dy.mapper.SysUserMapper;
import com.dy.service.SysCheckinStudentService;
import com.dy.mapper.SysCheckinStudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class SysCheckinStudentServiceImpl extends ServiceImpl<SysCheckinStudentMapper, SysCheckinStudent>
implements SysCheckinStudentService{

    @Autowired
    SysCheckinMapper checkinMapper;

    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<StudentCheckinInfo> getInfo(Long checkinId) {
        List<SysCheckinStudent> list = baseMapper.getInfo(checkinId);
        List<StudentCheckinInfo> ret = new ArrayList<>();
        for(SysCheckinStudent item:list){
            Long studentId = item.getStudentId();
            StudentCheckinInfo checkinInfo = new StudentCheckinInfo();
            BeanUtils.copyProperties(item,checkinInfo);
            checkinInfo.setNickName(userMapper.getNickname(studentId));
            checkinInfo.setIdentityNumber(userMapper.getIdentityNumber(studentId));
            ret.add(checkinInfo);
        }
        return ret;
    }

    @Override
    public List<CheckinStudentTaskDto> getStudentCheckinRecordsOfCourse(Long courseId) {
        return checkinMapper.getStudentCheckinRecordsOfCourse(courseId);
    }

    @Override
    public boolean checkIn(CheckinStudentRecordDto checkinStudentRecordDto) {
        Long checkinId = checkinStudentRecordDto.getCheckinId();
        Long studentId = SecurityUtils.getLoginUser().getUser().getId();
        if(checkinMapper.isActive(checkinId)){
            QueryWrapper param = new QueryWrapper<>()
                    .eq("checkin_id",checkinId)
                    .eq("student_id",studentId);

            SysCheckinStudent sysCheckinStudent = new SysCheckinStudent();
            sysCheckinStudent.setStudentId(studentId);
            BeanUtils.copyProperties(checkinStudentRecordDto, sysCheckinStudent);
            sysCheckinStudent.setCheckinTime(new Date());
            sysCheckinStudent.setIsFinish(true);
            baseMapper.update(sysCheckinStudent, param);
            return true;
        }
        return false;
    }

    @Override
    public int setFinishCheckin(Long checkinId, Long studentId) {
        return baseMapper.setFinishCheckin(checkinId,studentId);
    }

    @Override
    public int setUnfinishCheckin(Long checkinId, Long studentId) {
        return baseMapper.setUnfinishCheckin(checkinId,studentId);
    }
}





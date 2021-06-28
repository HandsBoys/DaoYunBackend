package com.dy.service;

import com.dy.domain.SysCheckinStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.CheckinStudentRecordDto;
import com.dy.dto.client.CheckinStudentTaskDto;
import com.dy.dto.client.StudentCheckinInfo;

import java.util.List;

/**
 *
 */
public interface SysCheckinStudentService extends IService<SysCheckinStudent> {

    boolean checkIn(CheckinStudentRecordDto checkinStudentRecordDto);

    List<StudentCheckinInfo> getInfo(Long checkinId);

    List<CheckinStudentTaskDto> getStudentCheckinRecordsOfCourse(Long courseId);

    int setFinishCheckin(Long checkId, Long studentId);

    int setUnfinishCheckin(Long checkId, Long studentId);
}

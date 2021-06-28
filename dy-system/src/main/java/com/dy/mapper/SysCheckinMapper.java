package com.dy.mapper;

import com.dy.domain.SysCheckin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dy.dto.client.CheckinStudentTaskDto;
import com.dy.dto.client.CheckinTaskDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Entity com.dy.domain.SysCheckin
 */
@Repository
public interface SysCheckinMapper extends BaseMapper<SysCheckin> {

    /**
     * 获取一个班课下的所有活动的签到任务
     * @param courseId
     * @return
     */
    List<CheckinTaskDto> getActiveCheckin(Long courseId);

    int setFinish(Long id);

    int setEndTime(@Param("id") Long id, @Param("endTime") Date endTime);

    /**
     * 判断签到任务是否完成
     * @param id 签到任务id
     * @return true:可以签到；0:不可签到
     */
    boolean isActive(Long id);

    /**
     * 获取一个班级的所有签到任务
     * @param courseId
     * @return
     */
    List<CheckinTaskDto> getAllCheckinTask(Long courseId);

    List<CheckinStudentTaskDto> getStudentCheckinRecordsOfCourse(@Param("courseId") Long courseId);
}





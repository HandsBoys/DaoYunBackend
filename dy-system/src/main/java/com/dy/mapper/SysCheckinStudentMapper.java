package com.dy.mapper;

import com.dy.domain.SysCheckinStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.dy.domain.SysCheckinStudent
 */
@Repository
public interface SysCheckinStudentMapper extends BaseMapper<SysCheckinStudent> {
    public Long insertRecord(SysCheckinStudent checkinStudent);

    void initRecords(Long checkinId, List<Long> studentIds);

    List<SysCheckinStudent> getInfo(@Param("checkinId") Long checkinId);

    int setFinishCheckin(@Param("checkinId")Long checkinId,@Param("studentId") Long studentId);

    int setUnfinishCheckin(@Param("checkinId")Long checkinId,@Param("studentId") Long studentId);
}





package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发起签到的信息表

 * @TableName sys_checkin
 */
@TableName(value ="sys_checkin")
@Data
public class SysCheckin implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 签到类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 发起签到的班课id
     */
    @TableField(value = "course_id")
    private Long courseId;

    /**
     * 发起者id
     */
    @TableField(value = "teacher_id")
    private Long teacherId;

    /**
     * 经度
     */
    @TableField(value = "lng")
    private Double lng;

    /**
     * 纬度
     */
    @TableField(value = "lat")
    private Double lat;

    /**
     * 发起时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 签到是否结束
     */
    @TableField(value = "is_finish")
    private Boolean isFinish;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
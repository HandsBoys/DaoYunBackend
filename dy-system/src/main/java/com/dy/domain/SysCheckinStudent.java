package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 签到信息表
 * @TableName sys_checkin_student
 */
@TableName(value ="sys_checkin_student")
@Data
public class SysCheckinStudent implements Serializable {
    /**
     * 签到信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发起的签到id
     */
    @TableField(value = "checkin_id")
    private Long checkinId;

    /**
     * 签到者id
     */
    @TableField(value = "student_id")
    private Long studentId;

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
     * 签到时间
     */
    @TableField(value = "checkin_time")
    private Date checkinTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 签到信息表
 * @author cxj
 */
@Schema(description = "学生个人及签到信息")
@Data
public class StudentCheckinInfo {
    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学生账号昵称
     */
    private String nickName;

    /**
     * 学号
     */
    private String identityNumber;

    /**
     * 签到任务id
     */
    private Long checkinId;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 签到时间
     */
    private Date checkinTime;

    /**
     * 是否签到（0：未签到；1：已签到）
     */
    @Schema(description = "是否签到（0：未签到；1：已签到）。")
    private Boolean isFinish;
}

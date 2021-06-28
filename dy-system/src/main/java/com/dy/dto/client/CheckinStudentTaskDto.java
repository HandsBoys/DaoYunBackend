package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "学生的签到任务信息对象")
@Data
public class CheckinStudentTaskDto {
    /**
     * 主键
     */
    @Schema(description = "签到任务的id")
    private Long id;

    /**
     * 签到类型
     */
    @Schema(description = "签到类型。1:一键签到;2:限时签到")
    private Integer type;

    /**
     * 发起签到的班课id
     */
    @Schema(description = "班课id")
    private Long courseId;


    /**
     * 发起时间
     */
    @Schema(description = "签到任务发起时间")
    private Date startTime;

    /**
     * 是否签到（0：未签到；1：已签到）
     */
    @Schema(description = "是否参与签到")
    private Boolean isFinish;
}

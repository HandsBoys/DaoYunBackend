package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Schema(description = "发起签到的数据对象")
public class CheckinTaskDto {
    /**
     * 主键
     */
    @Schema(description = "签到的id。发起签到时，该字段可为null")
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
     * 经度
     */
    @Schema(description = "经度")
    private Double lng;

    /**
     * 纬度
     */
    @Schema(description = "纬度")
    private Double lat;

    @Schema(description = "限制时间(分钟)。仅当签到类型为先式签到时，设置此字段。其他类型，该字段置为null")
    @Positive
    @Max(1440)
    private Long limitTime;

    /**
     * 发起时间
     */
    @Schema(description = "发起时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private Date endTime;

    /**
     * 签到是否结束
     */
    @Schema(description = "签到是否结束。false:未结束;true:结束")
    private Boolean isFinish;
}

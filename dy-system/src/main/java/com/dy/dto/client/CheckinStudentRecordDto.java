package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author cxj
 */
@Schema(description = "学生的签到记录")
@Data
public class CheckinStudentRecordDto {
    /**
     * 签到信息id
     */
    @Schema(description = "主键。",required = false)
    private Long id;

    /**
     * 发起的签到id
     */
    @Schema(description = "签到任务的id",required = true)
    private Long checkinId;

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

}

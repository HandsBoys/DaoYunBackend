package com.dy.dto.system;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysDictTypeDto {
    /**
     * 字典主键
     */
    @TableId
    private Long id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;
}

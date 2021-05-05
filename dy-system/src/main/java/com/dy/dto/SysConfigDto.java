package com.dy.dto;

import lombok.Data;

/**
 * @author cxj
 */
@Data
public class SysConfigDto {
    /**
     * 参数主键
     */
    private Integer id;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String key;

    /**
     * 参数键值
     */
    private String value;

    /**
     * 系统内置（Y是 N否）
     */
    private String type;

    /**
     * 备注
     */
    private String remark;
}

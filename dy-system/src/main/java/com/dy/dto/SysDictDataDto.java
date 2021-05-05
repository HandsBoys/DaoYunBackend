package com.dy.dto;

import lombok.Data;


@Data
public class SysDictDataDto {
    /**
     * 字典编码
     */
    private Long id;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private Integer dictValue;

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

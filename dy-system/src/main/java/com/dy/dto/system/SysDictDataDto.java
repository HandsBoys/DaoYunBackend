package com.dy.dto.system;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


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

    /**
     * 是否为默认值（0：不是默认；1：默认）
     */
    private Boolean isDefault;
}

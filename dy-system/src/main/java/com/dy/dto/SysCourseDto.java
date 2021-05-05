package com.dy.dto;

import lombok.Data;

/**
 * @author cxj
 */
@Data
public class SysCourseDto {
    /**
     * 班课id
     */
    private Long id;

    /**
     * 班课名称
     */
    private String name;

    /**
     * 班课状态（0正常 1停用）
     */
    private Boolean status;
}

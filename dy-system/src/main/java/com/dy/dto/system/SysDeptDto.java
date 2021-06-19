package com.dy.dto.system;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysDeptDto {
    /**
     * 部门id
     */
    private Long id;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门级别（U:学校；C:学院；S:专业/系）
     */
    private String deptLevel;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 部门状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Boolean delFlag;

    /**
     * 下级dept
     */
    private List<SysDeptDto> children = new ArrayList<>();
}

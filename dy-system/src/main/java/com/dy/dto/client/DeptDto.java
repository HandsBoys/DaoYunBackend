package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DeptDto {
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
     * 部门级别（S:学校；C:学院；M:专业/系）
     */
    private String deptLevel;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 下级dept
     */
    private List<DeptDto> children = new ArrayList<>();

}

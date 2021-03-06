package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门表
 * @TableName sys_dept
 */
@TableName(value ="sys_dept")
@Data
public class SysDept implements Serializable {
    /**
     * 部门id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父部门id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 部门级别（S:学校；C:学院；M:专业/系）
     */
    @TableField(value = "dept_level")
    private String deptLevel;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
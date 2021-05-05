package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 班级表
 * @TableName sys_class
 */
@TableName(value ="sys_class")
@Data
public class SysClass implements Serializable {
    /**
     * 班级id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班级名称
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 班级状态，是否停用（0正常 1停用）
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 能否加入（0：可以加入，1：禁止加入）
     */
    @TableField(value = "enable_join")
    private Boolean enableJoin;

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
    @TableField(value = "last_update_by")
    private Long lastUpdateBy;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
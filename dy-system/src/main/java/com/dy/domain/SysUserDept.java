package com.dy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户部门关联表
 * @TableName sys_user_dept
 */
@TableName(value ="sys_user_dept")
@Data
public class SysUserDept implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public SysUserDept(Long userId,Long deptId){
        this.userId = userId;
        this.deptId = deptId;
    }
}
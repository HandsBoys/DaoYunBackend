package com.dy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录日志表

 * @TableName sys_logininfor
 */
@TableName(value ="sys_logininfor")
@Data
public class SysLogininfor implements Serializable {
    /**
     * 访问日志id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录时的ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 登录状态（0成功 1失败）
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
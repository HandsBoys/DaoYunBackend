package com.dy.common.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 操作消息
 * @author cxj
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private static final String CODE = "code";

    /**
     * 返回内容
     */
    private static final String MSG = "msg";

    public AjaxResult(){
    }

    public AjaxResult(int code) {
        super.put(CODE, code);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public AjaxResult(int code, String msg)
    {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        return new AjaxResult(HttpStatus.OK.value(), msg);
    }

    /**
     * 返回错误消息
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    /**
     * 返回错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg);
    }
}


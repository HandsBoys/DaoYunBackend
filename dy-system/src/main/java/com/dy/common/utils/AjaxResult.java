package com.dy.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 操作消息
 * @author cxj
 */
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class AjaxResult<T>{
    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public AjaxResult(){
    }

    public AjaxResult(int code) {
        this.code = code;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public AjaxResult(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        if(data != null){
            this.data = data;
        }
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
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static<T> AjaxResult success(String msg, T data)
    {
        return new AjaxResult(HttpStatus.OK.value(), msg, data);
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

    public static<T> AjaxResult error(int code, String msg, T data)
    {
        return new AjaxResult(code, msg, data);
    }
}


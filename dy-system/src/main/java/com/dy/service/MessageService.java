package com.dy.service;

import com.dy.common.utils.AjaxResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cxj
 */
public interface MessageService {
    public boolean sendMessage(String phone);
}

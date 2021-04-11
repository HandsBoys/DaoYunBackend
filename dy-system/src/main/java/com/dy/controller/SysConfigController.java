package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统参数接口")
@RestController
@RequestMapping("/system/config")
public class SysConfigController {

    @PostMapping("/list")
    public AjaxResult list()
}

package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysCheckin;
import com.dy.domain.SysCheckinInfo;
import com.dy.service.SysCheckinInfoService;
import com.dy.service.SysCheckinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "签到发起接口")
@RestController
@RequestMapping("/checkin")
public class CheckinController extends BaseController{
    @Autowired
    private SysCheckinService checkinService;
    @Autowired
    private SysCheckinInfoService checkinInfoService;

    //TODO:数据校验
    @ApiOperation("发起签到")
    @PutMapping
    public AjaxResult startCheckin(@RequestBody SysCheckin checkin){
        return toAjax(checkinService.startCheckin(checkin));
    }

    @ApiOperation(value = "获取发起的未结束签到")
    @ApiImplicitParam(name = "courseId",value = "发起签到的课程id")
    @GetMapping
    public List<SysCheckin> getCheckin(Long courseId){
        List<SysCheckin> listActiveCheckin = checkinService.getActiveCheckin(courseId);
        return listActiveCheckin;
    }

}

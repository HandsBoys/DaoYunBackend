package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysCheckin;
import com.dy.service.SysCheckinInfoService;
import com.dy.service.SysCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkin")
public class CheckinController extends BaseController{
    @Autowired
    private SysCheckinService checkinService;
    @Autowired
    private SysCheckinInfoService checkinInfoService;

    //TODO:数据校验
    @PutMapping
    public AjaxResult startCheckin(@RequestBody SysCheckin checkin){
        return toAjax(checkinService.startCheckin(checkin));
    }

    @GetMapping
    public List<SysCheckin> getCheckin(Long courseId){
        List<SysCheckin> listActiveCheckin = checkinService.getActiveCheckin(courseId);
        return listActiveCheckin;
    }

}

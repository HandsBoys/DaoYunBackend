package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysCheckin;
import com.dy.service.SysCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/checkin")
public class CheckinController extends BaseController {
    @Autowired
    private SysCheckinService checkinService;

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

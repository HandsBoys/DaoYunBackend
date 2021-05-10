package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysCheckinInfo;
import com.dy.service.SysCheckinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chickin/infor")
public class CheckinInforController {
    @Autowired
    SysCheckinInfoService checkinInfoService;

    @PutMapping
    public AjaxResult checkIn(Long id, SysCheckinInfo checkinInfo){
        if(checkinInfoService.checkIn(id,checkinInfo)){
            return AjaxResult.success("签到成功");
        }
        return AjaxResult.error("签到失败");
    }

    @GetMapping
    public List<SysCheckinInfo> listrecordsByCheckinId(Long startCheckinId){
        List<SysCheckinInfo> list = checkinInfoService.listByStartCheckinId(startCheckinId);
        return list;
    }
}

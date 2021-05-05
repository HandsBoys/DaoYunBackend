package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysCheckinInfo;
import com.dy.service.SysCheckinInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "签到记录接口")
@RestController
@RequestMapping("/chickin/infor")
public class CheckinInforController {
    @Autowired
    SysCheckinInfoService checkinInfoService;

    @ApiOperation(value = "签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "发起签到的实例id",required = true,dataType = "Long")
    })
    @PutMapping
    public AjaxResult checkIn(Long id, SysCheckinInfo checkinInfo){
        if(checkinInfoService.checkIn(id,checkinInfo)){
            return AjaxResult.success("签到成功");
        }
        return AjaxResult.error("签到失败");
    }

    @ApiOperation(value = "获取一次签到下所有人的签到记录")
    @ApiImplicitParam(name = "startCheckinId",value = "发起的签到id",dataType = "Long")
    @GetMapping
    public List<SysCheckinInfo> listrecordsByCheckinId(Long startCheckinId){
        List<SysCheckinInfo> list = checkinInfoService.listByStartCheckinId(startCheckinId);
        return list;
    }
}

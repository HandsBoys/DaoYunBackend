package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.CheckinTaskDto;
import com.dy.service.SysCheckinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxj
 */
@Tag(name = "client-check-task-controller",description = "移动端签到任务的接口")
@RestController
@RequestMapping("/client/checkin")
public class CheckinTaskController extends BaseController {
    @Autowired
    private SysCheckinService checkinService;

    @Operation(description = "发起签到，需要传递班课id和签到类型。如成功，则返回新发起的签到任务id")
    @PutMapping("/start")
    public AjaxResult<Long> startCheckin(@Validated @RequestBody CheckinTaskDto checkin){
        Long checkinId = checkinService.startCheckin(checkin);
        if( checkinId != null){
            return AjaxResult.success("success", checkinId);
        }else {
            return AjaxResult.error();
        }
    }

    @Operation(description = "获取正在进行的签到")
    @GetMapping("/active-task")
    public AjaxResult<List<CheckinTaskDto>> getActiveCheckinTask(Long courseId){
        List<CheckinTaskDto> listActiveCheckin = checkinService.getActiveCheckin(courseId);
        if(listActiveCheckin != null && listActiveCheckin.size() != 0){
            return AjaxResult.success("success",listActiveCheckin);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"error",null);
    }

    @Operation(description = "获取所有的签到")
    @GetMapping("/all-task")
    public AjaxResult<List<CheckinTaskDto>> getAllCheckinTask(Long courseId){
        List<CheckinTaskDto> listActiveCheckin = checkinService.getAllCheckinTask(courseId);
        if(listActiveCheckin != null && listActiveCheckin.size() != 0){
            return AjaxResult.success("success",listActiveCheckin);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"error",null);
    }

    @Operation(description = "结束签到任务")
    @PostMapping("/end-task")
    public AjaxResult endCheckin(Long checkinId){
        return toAjax(checkinService.finishCheckin(checkinId));
    }

    @Operation(description = "删除签到任务")
    @DeleteMapping
    public AjaxResult deleteCheckinTask(Long checkinId){
        if(checkinService.removeById(checkinId)){
            return AjaxResult.success();
        }
        else {
            return AjaxResult.error();
        }
    }

}

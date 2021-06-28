package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.CheckinStudentRecordDto;
import com.dy.dto.client.CheckinStudentTaskDto;
import com.dy.dto.client.StudentCheckinInfo;
import com.dy.mapper.SysCheckinMapper;
import com.dy.service.SysCheckinStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author cxj
 */
@Tag(name = "client-check-in-controller",description = "移动端签到接口")
@RestController
@RequestMapping("/client/checkin")
public class CheckinStudentController extends BaseController {
    @Autowired
    private SysCheckinStudentService checkinStudentService;

    @Operation(description = "学生完成签到")
    @PostMapping("/finish-task")
    public AjaxResult checkIn(@Validated @RequestBody CheckinStudentRecordDto checkinStudentRecordDto){
        if(checkinStudentService.checkIn(checkinStudentRecordDto)){
            return AjaxResult.success("签到成功");
        }else {
            return AjaxResult.error("签到失败");
        }
    }

    @Operation(description = "获取单次签到任务的签到记录")
    @GetMapping("/info")
    public AjaxResult<StudentCheckinInfo> getInfo(Long checkinId){
        List<StudentCheckinInfo> list = checkinStudentService.getInfo(checkinId);
        if(list != null && list.size() != 0){
            return AjaxResult.success("success", list);
        }else {
            return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error", null);
        }
    }

    @Operation(description = "获取一个学生在某一门课程中的所有签到记录")
    @GetMapping("/student-records")
    public AjaxResult<List<CheckinStudentTaskDto>>  getStudentCheckinRecordsOfCourse(Long courseId){
        List<CheckinStudentTaskDto> list = checkinStudentService.getStudentCheckinRecordsOfCourse(courseId);
        if(list != null && list.size() != 0){
            return AjaxResult.success("success", list);
        }else {
            return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "error", null);
        }
    }

    @Operation(description = "将指定学生设置成完成签到")
    @PostMapping("/finish")
    public AjaxResult setFinishCheckin(Long checkinId, Long studentId){
        return toAjax(checkinStudentService.setFinishCheckin(checkinId,studentId));
    }

    @Operation(description = "将指定学生设置成未签到")
    @PostMapping("/unfinish")
    public AjaxResult setUnfinishCheckin(Long checkinId, Long studentId){
        return toAjax(checkinStudentService.setUnfinishCheckin(checkinId,studentId));
    }

}

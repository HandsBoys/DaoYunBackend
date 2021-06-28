package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.system.student.SysStudentDto;
import com.dy.service.SysCourseStudentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "client-manage-student-controller", description = "移动端学生管理接口")
@RestController
@RequestMapping("/client/management")
public class ManageStudentController extends BaseController {
    @Autowired
    private SysCourseStudentsService courseStudentsService;

    @Operation(summary = "获取某班课中的所有学生信息")
    @GetMapping
    public AjaxResult<List<SysStudentDto>> listStudents(Long courseId){
        List<SysStudentDto> list = courseStudentsService.getStudents(courseId);
        if(list != null && list.size() != 0){
            return AjaxResult.success("获取学生信息成功", list);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"获取学生信息失败", null);
    }

    @Operation(summary = "修改学生成绩分数")
    @PostMapping
    public AjaxResult updateScore(Long courseId, Long studentId, Long score){
        return toAjax(courseStudentsService.updateScore( courseId,  studentId,  score));
    }

    @Operation(summary = "给学生加分")
    @Parameters({
            @Parameter(name = "courseId",description = "课程id"),
            @Parameter(name = "studentId",description = "学生id"),
            @Parameter(name = "score",description = "增加的分数")
    })
    @PostMapping("/addscore")
    public AjaxResult addScore(Long courseId,Long studentId,Long score){
        return toAjax(courseStudentsService.addScore(courseId, studentId, score));
    }

    @Operation(summary = "给学生扣分")
    @Parameters({
            @Parameter(name = "courseId",description = "课程id"),
            @Parameter(name = "studentId",description = "学生id"),
            @Parameter(name = "score",description = "扣除的分数")
    })
    @PostMapping("/reducescore")
    public AjaxResult reduceScore(Long courseId,Long studentId,Long score){
        return toAjax(courseStudentsService.reduceScore(courseId, studentId, score));
    }
}

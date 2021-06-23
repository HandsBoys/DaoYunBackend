package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.system.student.SysStudentDto;
import com.dy.service.SysCourseStudentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxj
 */
@Tag(name = "Sys-Manage-Student-Controller", description = "管理班课中的学生")
@RestController
@RequestMapping("system/course/student")
public class SysManageStudentController extends BaseController {
    @Autowired
    private SysCourseStudentsService courseStudentsService;

    @Operation(summary = "获取某班课中的所有学生信息")
    @GetMapping
    @PreAuthorize("hasAuthority('system:course:edit') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysStudentDto>> listStudents(Long courseId){
        List<SysStudentDto> list = courseStudentsService.getStudents(courseId);
        if(list != null){
            return AjaxResult.success("获取学生信息成功", list);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"获取学生信息失败", null);
    }

    @Operation(summary = "将学生加入班级")
    @PutMapping
    @PreAuthorize("hasAuthority('system:course:edit') or hasAuthority('*:*:*')")
    public AjaxResult addStudent(Long courseId, Long studentId){
        return toAjax(courseStudentsService.addStudent(courseId,studentId));
    }

    @Operation(summary = "将学生退出班课")
    @DeleteMapping
    @PreAuthorize("hasAuthority('system:course:edit') or hasAuthority('*:*:*')")
    public AjaxResult removeStudent(Long courseId, Long studentId){
        return toAjax(courseStudentsService.quitCourse(courseId,studentId));
    }

    @Operation(summary = "修改学生成绩分数")
    @PostMapping
    @PreAuthorize("hasAuthority('system:course:edit') or hasAuthority('*:*:*')")
    public AjaxResult updateScore(Long courseId, Long studentId, Long score){
        return toAjax(courseStudentsService.updateScore( courseId,  studentId,  score));
    }
}

package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.system.course.SysCourseDto;
import com.dy.service.SysCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/course")
public class SysCourseController extends BaseController {
    @Autowired
    private SysCourseService courseService;

    @Operation(summary = "获取所有班课的信息")
    @GetMapping
    @PreAuthorize("hasAuthority('system:course:list') or hasAuthority('*:*:*')")
    public AjaxResult<List<SysCourseDto>> listCourse(){
        List<SysCourseDto> list = courseService.listCourses();
        if(list != null){
            return AjaxResult.success("成功",list);
        }
        return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"失败", null);
    }

    @Operation(summary = "删除班课", description = "")
    @DeleteMapping("/{courseIds}")
    @PreAuthorize("hasAuthority('system:course:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteCourses(@PathVariable Long[] courseIds){
        return toAjax(courseService.deleteCoursesByIds(courseIds));
    }

    @Operation(summary = "新增班课")
    @PutMapping
    @PreAuthorize("hasAuthority('system:course:add') or hasAuthority('*:*:*')")
    public AjaxResult addCourse(@Validated @RequestBody SysCourseDto courseDto){
        return toAjax(courseService.insertCourse(courseDto));
    }

    @Operation(summary = "修改班课信息",description = "将根据id查询班课，修改对应id的班课信息")
    @PostMapping
    @PreAuthorize("hasAuthority('system:course:edit') or hasAuthority('*:*:*')")
    public AjaxResult updateCourse(@Validated @RequestBody SysCourseDto courseDto){
        try {
            return toAjax(courseService.updateCourse(courseDto));
        }catch (Exception e){
            System.out.println(e);
            return AjaxResult.error();
        }
    }
}

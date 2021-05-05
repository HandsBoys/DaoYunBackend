package com.dy.controller;

import com.dy.common.utils.AjaxResult;
import com.dy.domain.SysCourse;
import com.dy.dto.SysCourseDto;
import com.dy.service.SysCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班课管理接口")
@RestController
@RequestMapping("system/course")
public class CourseController extends BaseController{
    @Autowired
    private SysCourseService courseService;

    //TODO:获取班课列表
    @ApiOperation(value = "获取所有班课信息")
    @GetMapping
    public List<SysCourse> listCourseAll(){
        List<SysCourse> list = courseService.listCoursesAll();
        return list;
    }

    //TODO:删除班课
    @ApiOperation(value = "删除班课")
    @DeleteMapping("/{courseIds}")
    public AjaxResult deleteCourses(@PathVariable Long[] courseIds){
        return toAjax(courseService.deleteCourses(courseIds));
    }

    //TODO:新增班课
    @ApiOperation(value = "新增班课")
    @PutMapping
    public AjaxResult addCourse(@Validated @RequestBody SysCourseDto courseDto){
        return toAjax(courseService.insertCourse(courseDto));
    }

    //TODO:修改班课信息
    @ApiOperation(value = "修改班课信息")
    @PostMapping
    public AjaxResult updateCourse(@Validated @RequestBody SysCourseDto courseDto){
        return toAjax(courseService.updateCourse(courseDto));
    }

}
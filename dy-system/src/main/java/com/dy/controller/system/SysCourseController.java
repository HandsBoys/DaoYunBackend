package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysCourse;
import com.dy.dto.system.SysCourseDto;
import com.dy.service.SysCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/course")
public class SysCourseController extends BaseController {
    @Autowired
    private SysCourseService courseService;

    //TODO:获取班课列表
    @GetMapping
    public List<SysCourse> listCourseAll(){
        List<SysCourse> list = courseService.listCoursesAll();
        return list;
    }

    //TODO:删除班课
    @DeleteMapping("/{courseIds}")
    public AjaxResult deleteCourses(@PathVariable Long[] courseIds){
        return toAjax(courseService.deleteCoursesByIds(courseIds));
    }

    //TODO:新增班课
    @PutMapping
    public AjaxResult addCourse(@Validated @RequestBody SysCourseDto courseDto){
        return toAjax(courseService.insertCourse(courseDto));
    }

    //TODO:修改班课信息
    @PostMapping
    public AjaxResult updateCourse(@Validated @RequestBody SysCourseDto courseDto){
        return toAjax(courseService.updateCourse(courseDto));
    }

}

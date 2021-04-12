package com.dy.controller;

import com.dy.core.utils.AjaxResult;
import com.dy.domain.SysCourse;
import com.dy.service.SysCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "班课管理接口")
@RestController
@RequestMapping("system/course")
public class CourseController extends BaseController{
    @Autowired
    private SysCourseService courseService;

    //TODO:按照登录用户获取班课列表
    @GetMapping("/list")
    public AjaxResult listCourse(Long userId){
        List<SysCourse> list = courseService.listCourses(userId);
        return AjaxResult.success(list);
    }

    //TODO:删除班课

    //TODO:新增班课

    //TODO:修改班课信息

    //TODO:管理班课
}

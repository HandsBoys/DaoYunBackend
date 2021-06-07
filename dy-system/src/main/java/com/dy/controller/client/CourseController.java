package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.CourseDto;
import com.dy.service.SysCourseService;
import com.dy.service.SysCourseStudentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxj
 */
@RestController
@RequestMapping("/client/course")
public class CourseController extends BaseController {
    @Autowired
    private SysCourseService courseService;

    @Autowired
    private SysCourseStudentsService courseStudentsService;

    @Operation(summary = "新建班课")
    @PutMapping("/new-course")
    @PreAuthorize("hasAuthority('system:course:add') or hasAuthority('*:*:*')")
    public AjaxResult addNewCourse(@Validated @RequestBody CourseDto course){
        return toAjax(courseService.addNewCourse(course));
    }

    @Operation(summary = "删除班课")
    @DeleteMapping("/delete-course/{courseIds}")
    @PreAuthorize("hasAuthority('system:course:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteCourse(@PathVariable Long[] courseIds){
        return toAjax(courseService.deleteCoursesByIds(courseIds));
    }

    @Operation(summary = "加入班课")
    @PutMapping("/join-course")
    @PreAuthorize("hasAuthority('system:course:join') or hasAuthority('*:*:*')")
    public AjaxResult joinCourse(Long courseId){
        return toAjax(courseStudentsService.joinCourse(courseId));
    }

    @Operation(summary = "退出班课")
    @Parameter(name="学生班课表的记录id",required = true)
    @DeleteMapping("/quit-course")
    @PreAuthorize("hasAuthority('system:course:quit') or hasAuthority('*:*:*')")
    public AjaxResult quitCourse(Long id){
        return toAjax(courseStudentsService.quitCourse(id));
    }

    @Operation(summary = "根据账户获取我创建的班课")
    @GetMapping("/created-course")
    @PreAuthorize("hasAuthority('system:course:list') or hasAuthority('*:*:*')")
    public AjaxResult listCreatedCourse(){
        List<CourseDto> list = courseService.listCreatedCourse();
        AjaxResult<List> ret = new AjaxResult<>();
        ret.setData(list);
        return ret;
    }

    @Operation(summary = "根据账户获取我加入的班课")
    @GetMapping("/joined-course")
    @PreAuthorize("hasAuthority('system:course:list') or hasAuthority('*:*:*')")
    public AjaxResult listJoinedCourse(){
        List<CourseDto> list = courseStudentsService.listJoinedCourse();
        AjaxResult<List> ret = new AjaxResult<>();
        ret.setData(list);
        if(list != null){
            ret.setMsg("班课获取成功！");
            ret.setCode(HttpStatus.OK.value());
        }
        else {
            ret.setMsg("班课获取失败！");
            ret.setCode(HttpStatus.NO_CONTENT.value());
        }
        return ret;
    }

}

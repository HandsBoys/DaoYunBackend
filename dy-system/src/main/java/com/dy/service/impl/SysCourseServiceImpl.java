package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysClass;
import com.dy.domain.SysCourse;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.SysCourseDto;
import com.dy.service.SysClassService;
import com.dy.service.SysCourseService;
import com.dy.mapper.SysCourseMapper;
import com.dy.service.SysCourseStudentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class SysCourseServiceImpl extends ServiceImpl<SysCourseMapper, SysCourse>
implements SysCourseService{
    @Autowired
    private SysClassService classService;

    @Autowired
    private SysCourseStudentsService courseStudentsService;

    @Override
    public List<SysCourse> listCoursesAll() {
        return null;
    }

    @Override
    public int deleteCoursesByIds(Long[] courseIds) {
        return baseMapper.deleteCourseByIds(courseIds);
    }

    @Override
    public int insertCourse(SysCourseDto courseDto) {
        return 0;
    }

    @Override
    public int updateCourse(SysCourseDto courseDto) {
        return 0;
    }

    @Override
    public int addNewCourse(CourseDto course) {
        // 新建班级
        SysClass sysClass = new SysClass();
        BeanUtils.copyProperties(course.getClassDto(),sysClass);
        sysClass.setDelFlag(true);
        sysClass.setStatus(true);
        //设创建者和时间
        sysClass.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysClass.setCreateTime(new Date());
        if(classService.insertClass(sysClass) > 0){
            Long ClassId = classService.getLastId();
            // 设置课程
            SysCourse sysCourse = new SysCourse();
            BeanUtils.copyProperties(course,sysCourse);
            sysCourse.setClassId(ClassId);
            sysCourse.setEnableJoin(true);
            sysCourse.setTeacherId(SecurityUtils.getLoginUser().getUser().getId());
            // 设创建者和时间
            sysCourse.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
            sysCourse.setCreateTime(new Date());
            return baseMapper.insert(sysCourse);
        }
        return 0;
    }

    @Override
    public List<CourseDto> listCreatedCourse() {
        Long teacherId = SecurityUtils.getLoginUser().getUser().getId();
        List<SysCourse> coursesList = baseMapper.listCreatedCourse(teacherId);
        List<CourseDto> ret = new ArrayList<>();
        for(SysCourse course: coursesList){
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course,courseDto);
            ret.add(courseDto);
        }
        return ret;
    }

}





package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysClass;
import com.dy.domain.SysCourse;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.SysCourseDto;
import com.dy.mapper.SysClassMapper;
import com.dy.service.SysClassService;
import com.dy.service.SysCourseService;
import com.dy.mapper.SysCourseMapper;
import com.dy.service.SysUserService;
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
    private SysUserService userService;

    @Autowired
    private SysClassMapper classMapper;

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
        sysClass.setDelFlag(false);
        sysClass.setStatus(false);
        //设创建者和时间
        sysClass.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysClass.setCreateTime(new Date());
        if(classService.insertClass(sysClass) > 0){
            Long ClassId = classService.getLastId();
            // 设置课程
            SysCourse sysCourse = new SysCourse();
            BeanUtils.copyProperties(course,sysCourse);
            sysCourse.setClassId(ClassId);
            sysCourse.setEnableJoin(false);
            sysCourse.setTeacherId(SecurityUtils.getLoginUser().getUser().getId());
            // 设创建者和时间
            sysCourse.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
            sysCourse.setCreateTime(new Date());
            // 设置状态
            sysCourse.setEnableJoin(false);
            sysCourse.setFinish(false);
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
            copyCourseToCourseDto(course,courseDto);
            ret.add(courseDto);
        }
        return ret;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        try{
            QueryWrapper<SysCourse> param = new QueryWrapper<SysCourse>()
                    .eq("id",id);
            SysCourse course = baseMapper.selectOne(param);
            CourseDto courseDto = new CourseDto();
            copyCourseToCourseDto(course,courseDto);
            return courseDto;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public boolean setCourseEnableJoin(Long id, Boolean enableJoin) {
        UpdateWrapper<SysCourse> param = new UpdateWrapper<SysCourse>()
                .set("enable_join",enableJoin)
                .eq("id",id);
        return super.update(param);
    }

    @Override
    public boolean setCourseFinish(Long id, Boolean finish) {
        UpdateWrapper<SysCourse> param = new UpdateWrapper<SysCourse>()
                .set("finish",finish)
                .eq("id",id);
        return super.update(param);
    }

    /**
     * 从SysCourse复制到CourseDto
     * @param sysCourse
     * @param courseDto
     */
    private void copyCourseToCourseDto(SysCourse sysCourse, CourseDto courseDto){
        BeanUtils.copyProperties(sysCourse,courseDto);
        // 设置教师名字
        courseDto.setTeacherName(userService.getNickNameById(courseDto.getTeacherId()));
        courseDto.setClassDto(classMapper.getClassById(sysCourse.getClassId()));
    }

}





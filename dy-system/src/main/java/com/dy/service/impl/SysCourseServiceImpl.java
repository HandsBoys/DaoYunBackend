package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.SysConfigConstants;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysClass;
import com.dy.domain.SysCourse;
import com.dy.domain.SysCourseDept;
import com.dy.domain.SysUser;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.course.CourseClassDto;
import com.dy.dto.system.course.CourseTeacherDto;
import com.dy.dto.system.course.SysCourseDto;
import com.dy.manager.service.SysUserRoleManager;
import com.dy.mapper.SysClassMapper;
import com.dy.mapper.SysCourseDeptMapper;
import com.dy.mapper.SysCourseMapper;
import com.dy.mapper.SysCourseStudentsMapper;
import com.dy.service.SysClassService;
import com.dy.service.SysCourseDeptService;
import com.dy.service.SysCourseService;
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
        implements SysCourseService {
    @Autowired
    private SysClassMapper classMapper;
    @Autowired
    private SysClassService classService;
    @Autowired
    private SysCourseDeptMapper courseDeptMapper;
    @Autowired
    private SysUserRoleManager userRoleManager;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysCourseDeptService courseDeptService;
    @Autowired
    private SysCourseStudentsMapper courseStudentsMapper;

    @Override
    public List<SysCourseDto> listCourses() {
        Long userId = SecurityUtils.getLoginUser().getUser().getId();
        if (userRoleManager.isAdmin(userId)) {
            return listCoursesAll();
        }
        return listCreatedCourse(userId);
    }

    @Override
    public List<SysCourseDto> listCoursesAll() {
        List<SysCourse> coursesList = baseMapper.listCourseAll();
        List<SysCourseDto> ret = new ArrayList<>();
        for (SysCourse course : coursesList) {
            SysCourseDto courseDto = new SysCourseDto();
            BeanUtils.copyProperties(course, courseDto);
            courseDto = getLinkInfoToSysCourseDto(courseDto);
            ret.add(courseDto);
        }
        return ret;
    }

    @Override
    public List<CourseDto> listCreatedCourse() {
        Long teacherId = SecurityUtils.getLoginUser().getUser().getId();
        List<SysCourse> coursesList = baseMapper.listCreatedCourse(teacherId);
        List<CourseDto> ret = new ArrayList<>();
        for (SysCourse course : coursesList) {
            CourseDto courseDto = new CourseDto();
            copySysCourseToCourseDto(course, courseDto);
            ret.add(courseDto);
        }
        return ret;
    }

    @Override
    public List<CourseDto> listJoinedCourse() {
        Long studentId = SecurityUtils.getLoginUser().getUser().getId();
        List<SysCourse> list = courseStudentsMapper.getCoursesByStudentId(studentId);
        List<CourseDto> ret = new ArrayList<>();
        for(SysCourse c:list){
            CourseDto courseDto = new CourseDto();
            copySysCourseToCourseDto(c,courseDto);


            ret.add(courseDto);
        }
        return ret;
    }

    @Override
    public List<SysCourseDto> listCreatedCourse(Long userId) {
        List<SysCourse> coursesList = baseMapper.listCreatedCourse(userId);
        List<SysCourseDto> ret = new ArrayList<>();
        for (SysCourse course : coursesList) {
            SysCourseDto courseDto = new SysCourseDto();
            BeanUtils.copyProperties(course, courseDto);
            courseDto = getLinkInfoToSysCourseDto(courseDto);
            ret.add(courseDto);
        }
        return ret;
    }

    @Override
    public int insertCourse(SysCourseDto courseDto) {
        SysCourse course = new SysCourse();
        BeanUtils.copyProperties(courseDto, course);
        // 设置默认的教师
        Long userId = SecurityUtils.getLoginUser().getUser().getId();
        course.setTeacherId(userId);
        course.setCreateBy(userId);
        course.setCreateTime(new Date());

        // 新增班级信息
        if (courseDto.getClassDto() != null) {
            SysClass sysClass = new SysClass();
            BeanUtils.copyProperties(courseDto.getClassDto(), sysClass);
            classService.insertClass(sysClass);
        }
        // 设置课程与班级关联
        course.setClassId(classService.getLastId());
        // 设置课程与机构关联
        setDeptIds(courseDto);

        return baseMapper.insert(course);
    }

    @Override
    public int addNewCourse(CourseDto course) {
        // 新建班级
        SysClass sysClass = new SysClass();
        BeanUtils.copyProperties(course.getClassDto(), sysClass);
        sysClass.setDelFlag(false);
        sysClass.setStatus(false);
        //设创建者和时间
        sysClass.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysClass.setCreateTime(new Date());
        if (classService.insertClass(sysClass) > 0) {
            // 设置课程
            Long ClassId = classService.getLastId();
            SysCourse sysCourse = new SysCourse();
            BeanUtils.copyProperties(course, sysCourse);
            sysCourse.setClassId(ClassId);
            sysCourse.setEnableJoin(false);
            sysCourse.setTeacherId(SecurityUtils.getLoginUser().getUser().getId());
            // 设创建者和时间
            sysCourse.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
            sysCourse.setCreateTime(new Date());
            // 设置状态
            sysCourse.setEnableJoin(false);
            sysCourse.setFinish(false);
            // 新增班课
            baseMapper.insert(sysCourse);
            // 设置班课和机构关联
            Long courseId = baseMapper.getLastId();
            for(Long id:course.getDeptIds()){
                courseDeptMapper.insert(new SysCourseDept(courseId, id));
            }

        }
        return 0;
    }

    @Override
    public int updateCourse(SysCourseDto courseDto) {
        SysCourse course = new SysCourse();
        BeanUtils.copyProperties(courseDto, course);
        course.setLastUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        course.setLastUpdateTime(new Date());

        // 修改班级信息
        if (courseDto.getClassDto() != null) {
            SysClass sysClass = new SysClass();
            BeanUtils.copyProperties(courseDto.getClassDto(), sysClass);
            classService.updateById(sysClass);
        }

        return baseMapper.updateById(course);
    }

    @Override
    public boolean setCourseEnableJoin(Long id, Boolean enableJoin) {
        UpdateWrapper<SysCourse> param = new UpdateWrapper<SysCourse>()
                .set("enable_join", enableJoin)
                .eq("id", id);
        return super.update(param);
    }

    @Override
    public boolean setCourseFinish(Long id, Boolean finish) {
        UpdateWrapper<SysCourse> param = new UpdateWrapper<SysCourse>()
                .set("finish", finish)
                .eq("id", id);
        return super.update(param);
    }

    @Override
    public int deleteCoursesByIds(Long[] courseIds) {
        int ret = baseMapper.deleteCourseByIds(courseIds);
        // 删除班级
        for (Long courseId : courseIds) {
            classService.deleteById(baseMapper.getClassId(courseId));
        }
        // 删除机构关联
        courseDeptMapper.deleteByCourseId(courseIds);
        return ret;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        try {
            QueryWrapper<SysCourse> param = new QueryWrapper<SysCourse>()
                    .eq("id", id);
            SysCourse course = baseMapper.selectOne(param);
            CourseDto courseDto = new CourseDto();
            copySysCourseToCourseDto(course, courseDto);
            return courseDto;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    private Long getDeptId(Long courseId, String deptLevel) {
        return courseDeptMapper.getDeptId(courseId, deptLevel);
    }

    /**
     * 获取课程教师的信息
     *
     * @param teacherId
     * @return 教师信息
     */
    private CourseTeacherDto getTeacherInfo(Long teacherId) {
        try {
            SysUser user = userService.getUserInfo(teacherId);
            CourseTeacherDto ret = new CourseTeacherDto();
            BeanUtils.copyProperties(user, ret);
            return ret;
        } catch (Exception e) {
            return null;
        }

    }

    private CourseClassDto getClassInfo(Long classId) {
        SysClass sysClass = classService.getClassInfo(classId);
        CourseClassDto ret = new CourseClassDto();
        BeanUtils.copyProperties(sysClass, ret);
        return ret;
    }

    /**
     * 从SysCourse复制到CourseDto
     *
     * @param sysCourse
     * @param courseDto
     */
    private void copySysCourseToCourseDto(SysCourse sysCourse, CourseDto courseDto) {
        BeanUtils.copyProperties(sysCourse, courseDto);
        // 设置教师名字
        courseDto.setTeacherName(userService.getNickNameById(courseDto.getTeacherId()));
        // 设置班级关联
        courseDto.setClassDto(classMapper.getClassById(sysCourse.getClassId()));
        // 关联机构
        courseDto.setDept(courseDeptService.getDeptName(sysCourse.getId()));
        courseDto.setDeptIds(courseDeptMapper.getDeptIds(sysCourse.getId()));
    }

    // 设置班课与机构关联
    private void setDeptIds(SysCourseDto courseDto) {
        Long courseId = courseDto.getId();
        if(courseDto.getUniversityId() != null){
            courseDeptMapper.insert(new SysCourseDept(courseId, courseDto.getUniversityId()));
        }
        if(courseDto.getCollegeId() != null){
            courseDeptMapper.insert(new SysCourseDept(courseId, courseDto.getCollegeId()));
        }
        if(courseDto.getSubjectId() != null){
            courseDeptMapper.insert(new SysCourseDept(courseId, courseDto.getSubjectId()));
        }
    }

    // 获取关联信息
    private SysCourseDto  getLinkInfoToSysCourseDto(SysCourseDto courseDto){
        // 获取班级信息
        courseDto.setClassDto(getClassInfo(courseDto.getClassId()));
        // 获取老师信息
        courseDto.setTeacher(getTeacherInfo(courseDto.getTeacherId()));
        // 获取机构信息
        courseDto.setUniversityId(getDeptId(courseDto.getId(), SysConfigConstants.UNIVERSITY));
        courseDto.setCollegeId(getDeptId(courseDto.getId(), SysConfigConstants.COLLEGE));
        courseDto.setSubjectId(getDeptId(courseDto.getId(), SysConfigConstants.SUBJECT));
        return courseDto;
    }
}





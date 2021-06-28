package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysCourse;
import com.dy.domain.SysCourseStudents;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.student.SysStudentDto;
import com.dy.mapper.SysClassMapper;
import com.dy.mapper.SysCourseDeptMapper;
import com.dy.mapper.SysCourseStudentsMapper;
import com.dy.service.SysCourseDeptService;
import com.dy.service.SysCourseService;
import com.dy.service.SysCourseStudentsService;
import com.dy.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author cxj
 */
@Service
public class SysCourseStudentsServiceImpl extends ServiceImpl<SysCourseStudentsMapper, SysCourseStudents>
implements SysCourseStudentsService {
    @Autowired
    private SysClassMapper classMapper;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysCourseDeptMapper courseDeptMapper;

    @Autowired
    private SysCourseDeptService courseDeptService;

    @Autowired
    private SysCourseService courseService;

    @Override
    public int joinCourse(Long courseId) {
        SysCourseStudents courseStudents = new SysCourseStudents();
        courseStudents.setCourseId(courseId);
        courseStudents.setStudentId(SecurityUtils.getLoginUser().getUser().getId());
        courseStudents.setScore(0L);
        return baseMapper.insert(courseStudents);
    }

    @Override
    public int quitCourse(Long courseId, Long studentId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("course_id",courseId)
                .eq("student_id",studentId);
        return baseMapper.delete(param);
    }


    @Override
    public SysCourseStudents getRecord(Long courseId, Long studentId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("course_id",courseId)
                .eq("student_id",studentId);
        return baseMapper.selectOne(param);
    }

    @Override
    public List<SysStudentDto> getStudents(Long courseId) {
        return baseMapper.getStudents(courseId);
    }

    @Override
    public int removeStudents(Long courseId, Long[] studentIds) {
        return baseMapper.removeStudents(courseId, studentIds);
    }

    @Override
    public int addStudent(Long courseId, Long studentId) {
        try{
            baseMapper.insert(new SysCourseStudents(courseId,studentId));
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int updateScore(Long courseId, Long studentId, Long score) {
        return baseMapper.updateScore(courseId, studentId, score);
    }

    @Override
    public List<Long> getStudentIds(Long courseId) {
        return baseMapper.getStudentIds(courseId);
    }

    @Override
    public boolean checkStudentIsInCourse(Long courseId, Long studentId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("course_id",courseId)
                .eq("student_id",studentId);
        if(baseMapper.selectOne(param) != null){
            return true;
        }
        return false;
    }

    @Override
    public int addScore(Long courseId, Long studentId, Long score) {
        Long currentScore = baseMapper.getScore(courseId,studentId);
        return baseMapper.updateScore(courseId, studentId, currentScore + score);
    }

    @Override
    public int reduceScore(Long courseId, Long studentId, Long score) {
        Long currentScore = baseMapper.getScore(courseId, studentId);
        return baseMapper.updateScore(courseId, studentId, currentScore - score);
    }

    @Override
    public Long getScore(Long courseId, Long studentId) {
        return baseMapper.getScore(courseId, studentId);
    }

}





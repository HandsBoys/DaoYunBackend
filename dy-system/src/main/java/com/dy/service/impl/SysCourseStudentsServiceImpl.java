package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysCourse;
import com.dy.domain.SysCourseStudents;
import com.dy.dto.client.CourseDto;
import com.dy.mapper.SysClassMapper;
import com.dy.mapper.SysCourseStudentsMapper;
import com.dy.service.SysCourseStudentsService;
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

    @Override
    public int joinCourse(Long courseId) {
        SysCourseStudents courseStudents = new SysCourseStudents();
        courseStudents.setCourseId(courseId);
        courseStudents.setStudentId(SecurityUtils.getLoginUser().getUser().getId());
        courseStudents.setScore(0L);
        return baseMapper.insert(courseStudents);
    }

    @Override
    public int quitCourse(Long id) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",id);
        return baseMapper.delete(param);
    }

    @Override
    public List<CourseDto> listJoinedCourse() {
        Long studentId = SecurityUtils.getLoginUser().getUser().getId();
        List<SysCourse> list = baseMapper.getCoursesByStudentId(studentId);
        System.out.println(list);
        List<CourseDto> ret = new ArrayList<>();
        for(SysCourse c:list){
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(c,courseDto);
            courseDto.setClassDto(classMapper.getClassById(c.getClassId()));
            ret.add(courseDto);
        }
        System.out.println(ret);
        return ret;
    }
}





package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysCourseStudents;
import com.dy.mapper.SysCourseStudentsMapper;
import com.dy.service.SysCourseStudentsService;
import org.springframework.stereotype.Service;


/**
 *
 * @author cxj
 */
@Service
public class SysCourseStudentsServiceImpl extends ServiceImpl<SysCourseStudentsMapper, SysCourseStudents>
implements SysCourseStudentsService {

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
}





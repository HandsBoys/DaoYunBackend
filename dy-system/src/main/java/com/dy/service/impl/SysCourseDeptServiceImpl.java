package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.constant.SysConfigConstants;
import com.dy.common.utils.StringUtils;
import com.dy.domain.SysCourseDept;
import com.dy.mapper.SysDeptMapper;
import com.dy.service.SysCourseDeptService;
import com.dy.mapper.SysCourseDeptMapper;
import com.dy.service.SysCourseService;
import com.dy.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysCourseDeptServiceImpl extends ServiceImpl<SysCourseDeptMapper, SysCourseDept>
implements SysCourseDeptService{
    @Autowired
    private SysCourseDeptMapper courseDeptMapper;

    @Override
    public String getDeptName(Long courseId) {
        String ret = "";
        String temp = "";
        temp = courseDeptMapper.getdeptNameByCourseId(courseId, SysConfigConstants.UNIVERSITY);
        if(StringUtils.isNotEmpty(temp)){ ret += temp; }
        temp = courseDeptMapper.getdeptNameByCourseId(courseId, SysConfigConstants.COLLEGE);
        if(StringUtils.isNotEmpty(temp)){ret += temp;}
        return ret;
    }
}





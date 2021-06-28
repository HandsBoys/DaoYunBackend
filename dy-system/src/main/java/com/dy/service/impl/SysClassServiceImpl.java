package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysClass;
import com.dy.service.SysClassService;
import com.dy.mapper.SysClassMapper;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysClassServiceImpl extends ServiceImpl<SysClassMapper, SysClass>
implements SysClassService{

    @Override
    public int insertClass(SysClass sysClass) {
        return baseMapper.insert(sysClass);
    }

    @Override
    public SysClass getClassInfo(Long classId) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",classId);
        return baseMapper.selectOne(param);
    }

    @Override
    public int deleteById(Long id) {
        QueryWrapper param = new QueryWrapper<>()
                .eq("id",id);
        return baseMapper.delete(param);
    }
}





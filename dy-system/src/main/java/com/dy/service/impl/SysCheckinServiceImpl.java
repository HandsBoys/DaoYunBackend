package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.domain.SysCheckin;
import com.dy.service.SysCheckinService;
import com.dy.mapper.SysCheckinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysCheckinServiceImpl extends ServiceImpl<SysCheckinMapper, SysCheckin>
implements SysCheckinService{

    @Override
    public int startCheckin(SysCheckin checkin) {
        return baseMapper.insert(checkin);
    }

    @Override
    public List<SysCheckin> getActiveCheckin(Long courseId) {
        return baseMapper.getActiveCheckin(courseId);
    }
}





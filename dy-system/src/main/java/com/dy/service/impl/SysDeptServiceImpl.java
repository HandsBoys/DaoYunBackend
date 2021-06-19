package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.utils.SecurityUtils;
import com.dy.domain.SysDept;
import com.dy.domain.SysMenu;
import com.dy.dto.client.DeptDto;
import com.dy.dto.system.SysDeptDto;
import com.dy.service.SysDeptService;
import com.dy.mapper.SysDeptMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
implements SysDeptService{

    @Override
    public List<DeptDto> listDeptAll() {
        QueryWrapper<SysDept> param = new QueryWrapper<SysDept>()
                .eq("del_flag",0)
                .eq("status",0)
                .and(i -> i.eq("dept_level","U")
                        .or()
                        .eq("dept_level","C"))
;
        List<SysDept> deptList = baseMapper.selectList(param);
        List<DeptDto> ret = new ArrayList<>();
        for(SysDept d: deptList){
            DeptDto deptDto = new DeptDto();
            BeanUtils.copyProperties(d,deptDto);
            ret.add(deptDto);
        }
        return ret;
    }

    @Override
    public List<DeptDto> buildDeptTree(List<DeptDto> deptDtos) {
        List<DeptDto> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for(DeptDto deptDto:deptDtos){
            tempList.add(deptDto.getId());
        }
        for(DeptDto dept:deptDtos){
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if(!tempList.contains(dept.getParentId())){
                recursionFn(deptDtos,dept);
                returnList.add(dept);
            }
        }
        if(returnList.isEmpty()){
            returnList = deptDtos;
        }
        return  returnList;
    }

    @Override
    public List<SysDeptDto> listSysDeptDtoAll() {
        QueryWrapper<SysDept> param = new QueryWrapper<SysDept>()
                .eq("del_flag",0)
                .eq("status",0)
                .and(i -> i.eq("dept_level","U")
                        .or()
                        .eq("dept_level","C"))
                ;
        List<SysDept> deptList = baseMapper.selectList(param);
        List<SysDeptDto> ret = new ArrayList<>();
        for(SysDept d: deptList){
            SysDeptDto deptDto = new SysDeptDto();
            BeanUtils.copyProperties(d,deptDto);
            ret.add(deptDto);
        }
        return ret;
    }

    @Override
    public List<SysDeptDto> buildSysDeptTree(List<SysDeptDto> deptDtos) {
        List<SysDeptDto> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for(SysDeptDto deptDto:deptDtos){
            tempList.add(deptDto.getId());
        }
        for(SysDeptDto dept:deptDtos){
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if(!tempList.contains(dept.getParentId())){
                recursionFn(deptDtos,dept);
                returnList.add(dept);
            }
        }
        if(returnList.isEmpty()){
            returnList = deptDtos;
        }
        return  returnList;
    }

    @Override
    public int addDept(DeptDto dept) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(dept,sysDept);
        sysDept.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysDept.setCreateTime(new Date());
        sysDept.setStatus(false);
        sysDept.setDelFlag(false);
        return baseMapper.insert(sysDept);
    }

    @Override
    public int addDept(SysDeptDto dept) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(dept,sysDept);
        sysDept.setCreateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysDept.setCreateTime(new Date());
        return baseMapper.insert(sysDept);
    }

    @Override
    public int deleteDeptsById(Long[] deptIds) {
        return baseMapper.deleteDeptsById(deptIds);
    }

    @Override
    public int editDept(SysDeptDto dept) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(dept,sysDept);
        sysDept.setUpdateBy(SecurityUtils.getLoginUser().getUser().getId());
        sysDept.setUpdateTime(new Date());
        return baseMapper.insert(sysDept);
    }

    private void recursionFn(List<DeptDto> list, DeptDto dept){
        List<DeptDto> childList = getChildren(list,dept);
        dept.setChildren(childList);
        for(DeptDto d:childList){
            if(hasChild(list,d)){
                recursionFn(list,d);
            }
        }
    }
    private void recursionFn(List<SysDeptDto> list, SysDeptDto dept){
        List<SysDeptDto> childList = getChildren(list,dept);
        dept.setChildren(childList);
        for(SysDeptDto d:childList){
            if(hasChild(list,d)){
                recursionFn(list,d);
            }
        }
    }

    private boolean hasChild(List<DeptDto> list, DeptDto d) {
        return getChildren(list,d).size() > 0?true:false;
    }

    private boolean hasChild(List<SysDeptDto> list, SysDeptDto d) {
        return getChildren(list,d).size() > 0?true:false;
    }

    private List<DeptDto> getChildren(List<DeptDto> list, DeptDto dept) {
        List<DeptDto> returnList = new ArrayList<>();
        Iterator<DeptDto> it = list.iterator();
        while(it.hasNext()){
            DeptDto next = (DeptDto) it.next();
            if(next.getParentId().longValue() == dept.getId().longValue()){
                returnList.add(next);
            }
        }
        return returnList;
    }
    private List<SysDeptDto> getChildren(List<SysDeptDto> list, SysDeptDto dept) {
        List<SysDeptDto> returnList = new ArrayList<>();
        Iterator<SysDeptDto> it = list.iterator();
        while(it.hasNext()){
            SysDeptDto next = (SysDeptDto) it.next();
            if(next.getParentId().longValue() == dept.getId().longValue()){
                returnList.add(next);
            }
        }
        return returnList;
    }
}





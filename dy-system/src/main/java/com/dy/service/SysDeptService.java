package com.dy.service;

import com.dy.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.DeptDto;
import com.dy.dto.system.SysDeptDto;

import java.util.List;

/**
 *
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 获取所有dept记录
     * @return
     */
    List<DeptDto> listDeptAll();

    List<DeptDto> buildDeptTree(List<DeptDto> deptDtos);

    List<SysDeptDto> listSysDeptDtoAll();

    List<SysDeptDto> buildSysDeptTree(List<SysDeptDto> deptDtos);

    int addDept(DeptDto dept);

    int addDept(SysDeptDto dept);

    int deleteDeptsById(Long[] deptIds);

    int editDept(SysDeptDto dept);
}

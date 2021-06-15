package com.dy.service;

import com.dy.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.DeptDto;

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

    int addDept(DeptDto dept);
}

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

    boolean editDept(SysDeptDto dept);

    /**
     * 获取当前机构的下级机构
     * @param deptId:当前机构的id
     * @return 下级机构列表 List<SysDeptDot>
     */
    List<SysDeptDto> getChildSysDeptDto(Long deptId);

    /**
     * 查询当前机构是否存在下级机构
     * @param deptId:当前机构id
     * @return true:存在下级机构;false:不存在下级机构
     */
    public boolean hasChild(Long deptId);
}

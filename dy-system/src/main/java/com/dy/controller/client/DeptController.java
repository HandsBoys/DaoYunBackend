package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.domain.SysUser;
import com.dy.dto.client.DeptDto;
import com.dy.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxj
 */
@RestController
@RequestMapping("/client/dept")
public class DeptController extends BaseController {
    @Autowired
    private SysDeptService deptService;

    @Operation(summary = "获取部门树结构表")
    @GetMapping
    @PreAuthorize("hasAuthority('system:dept:list') or hasAuthority('*:*:*')")
    public AjaxResult<List> listDeptAll(){
        AjaxResult<List> ret = new AjaxResult<>();
        List<DeptDto> deptDtos = deptService.listDeptAll();
        List<DeptDto> deptTree = deptService.buildDeptTree(deptDtos);
        if(deptTree != null){
            ret.setCode(HttpStatus.OK.value());
            ret.setMsg("获取成功！");
        }
        else{
            ret.setCode(HttpStatus.NO_CONTENT.value());
            ret.setMsg("获取失败！");
        }
        ret.setData(deptTree);
        return ret;
    }

    @Operation(summary = "新增部门")
    @PutMapping
    @PreAuthorize("hasAuthority('system:dept:add') or hasAuthority('*:*:*')")
    public AjaxResult addDept(@Validated @RequestBody DeptDto dept){
        return toAjax(deptService.addDept(dept));
    }
}

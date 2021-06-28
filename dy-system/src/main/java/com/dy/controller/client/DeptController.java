package com.dy.controller.client;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.DeptDto;
import com.dy.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxj
 */
@Tag(name = "client-dept-controller",description = "移动端机构接口")
@RestController
@RequestMapping("/client/dept")
public class DeptController extends BaseController {
    @Autowired
    private SysDeptService deptService;

    @Operation(summary = "获取机构树结构表")
    @GetMapping
    @PreAuthorize("hasAuthority('system:dept:list') or hasAuthority('*:*:*')")
    public AjaxResult<List> listDeptAll(){
        AjaxResult<List> ret = new AjaxResult<>();
        List<DeptDto> deptDtos = deptService.listDeptAll();
        List<DeptDto> deptTree = deptService.buildDeptTree(deptDtos);
        if(deptTree != null && deptTree.size() != 0){
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

    @Operation(summary = "新增机构")
    @PutMapping
    @PreAuthorize("hasAuthority('system:dept:add') or hasAuthority('*:*:*')")
    public AjaxResult addDept(@Validated @RequestBody DeptDto dept){
        dept.setChildren(null);
        return toAjax(deptService.addDept(dept));
    }
}

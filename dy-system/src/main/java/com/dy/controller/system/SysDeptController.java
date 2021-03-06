package com.dy.controller.system;

import com.dy.common.utils.AjaxResult;
import com.dy.controller.BaseController;
import com.dy.dto.client.DeptDto;
import com.dy.dto.system.SysDeptDto;
import com.dy.dto.system.user.UserDeptDto;
import com.dy.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService deptService;

    @Operation(summary = "新增机构")
    @PutMapping
    @PreAuthorize("hasAuthority('system:dept:add') or hasAuthority('*:*:*')")
    public AjaxResult<SysDeptDto> addDept(@Validated @RequestBody SysDeptDto dept){
        return toAjax(deptService.addDept(dept));
    }

    @Operation(summary = "删除机构")
    @DeleteMapping("/{deptIds}")
    @PreAuthorize("hasAuthority('system:dept:remove') or hasAuthority('*:*:*')")
    public AjaxResult deleteDepts(@PathVariable Long[] deptIds){
        for(Long deptId:deptIds){
            if(deptService.hasChild(deptId)){
                return AjaxResult.error(HttpStatus.NOT_ACCEPTABLE.value(), "id:"+deptId+"的机构存在下级机构,无法删除.");
            }
        }
        return toAjax(deptService.deleteDeptsById(deptIds));
    }

    @Operation(summary = "修改机构信息")
    @PostMapping
    @PreAuthorize("hasAuthority('system:dept:edit') or hasAuthority('*:*:*')")
    public AjaxResult editDept(@Validated @RequestBody SysDeptDto dept){
        if(deptService.editDept(dept)){
            return AjaxResult.success("修改成功");
        }
        else {
            return AjaxResult.error("修改失败");
        }
    }

    @Operation(summary = "获取所有机构信息")
    @GetMapping
    @PreAuthorize("hasAuthority('system:dept:list') or hasAuthority('*:*:*')")
    public AjaxResult<SysDeptDto> listDept(){
        List<SysDeptDto> deptDtos = deptService.listSysDeptDtoAll();
        List<SysDeptDto> deptTree = deptService.buildSysDeptTree(deptDtos);
        if(deptTree != null){
            return AjaxResult.success("获取成功",deptTree);
        }
        else {
            return AjaxResult.error(HttpStatus.NO_CONTENT.value(), "获取失败",deptTree);
        }
    }

    @Operation(summary = "获取下级机构")
    @GetMapping("/next-dept")
    @PreAuthorize("hasAuthority('system:dept:query') or hasAuthority('*:*:*')")
    public AjaxResult<UserDeptDto> getNextDepts(Long parentId){
        List<UserDeptDto> ret = deptService.getNextDept(parentId);
        if(ret != null){
            return AjaxResult.success("获取成功",ret);
        }
        else {
            return AjaxResult.error(HttpStatus.NO_CONTENT.value(),"获取失败",null);
        }
    }


}
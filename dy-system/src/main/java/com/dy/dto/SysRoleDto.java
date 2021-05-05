package com.dy.dto;

import lombok.Data;

@Data
public class SysRoleDto {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本班课数据权限 ）
     */
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;
}

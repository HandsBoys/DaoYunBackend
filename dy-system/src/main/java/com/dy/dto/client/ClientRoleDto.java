package com.dy.dto.client;

import lombok.Data;

@Data
public class ClientRoleDto {
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
}

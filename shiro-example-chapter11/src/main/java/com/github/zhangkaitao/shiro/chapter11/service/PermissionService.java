package com.github.zhangkaitao.shiro.chapter11.service;

import com.github.zhangkaitao.shiro.chapter11.entity.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}

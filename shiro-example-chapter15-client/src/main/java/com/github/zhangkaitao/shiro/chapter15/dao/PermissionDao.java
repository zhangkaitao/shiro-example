package com.github.zhangkaitao.shiro.chapter15.dao;

import com.github.zhangkaitao.shiro.chapter15.entity.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}

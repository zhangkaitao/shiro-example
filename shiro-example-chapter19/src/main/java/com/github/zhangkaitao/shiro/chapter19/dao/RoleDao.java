package com.github.zhangkaitao.shiro.chapter19.dao;

import com.github.zhangkaitao.shiro.chapter19.entity.Role;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}

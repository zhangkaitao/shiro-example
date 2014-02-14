package com.github.zhangkaitao.shiro.chapter17.service;

import com.github.zhangkaitao.shiro.chapter17.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleService {


    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();

    Set<String> findRoles(Long... roleIds);
    Set<String> findPermissions(Long[] roleIds);
}

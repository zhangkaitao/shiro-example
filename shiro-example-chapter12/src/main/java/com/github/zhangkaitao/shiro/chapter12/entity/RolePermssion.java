package com.github.zhangkaitao.shiro.chapter12.entity;

import java.io.Serializable;

/**
 * 用户角色关系
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class RolePermssion implements Serializable {

    private Long roleId;
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermssion that = (RolePermssion) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RolePermssion{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}

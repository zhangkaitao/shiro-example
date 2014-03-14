package com.github.zhangkaitao.shiro.chapter23.remote;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public class PermissionContext implements Serializable {
    private Set<String> roles;
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "PermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}

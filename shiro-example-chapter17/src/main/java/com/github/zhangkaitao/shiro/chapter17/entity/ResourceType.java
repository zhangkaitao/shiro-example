package com.github.zhangkaitao.shiro.chapter17.entity;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
public enum ResourceType {
    menu("菜单"), button("按钮");

    private final String info;
    private ResourceType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

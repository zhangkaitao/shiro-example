package com.github.zhangkaitao.shiro.chapter23.dao;

import com.github.zhangkaitao.shiro.chapter23.entity.App;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface AppDao {

    public App createApp(App app);
    public App updateApp(App app);
    public void deleteApp(Long appId);

    public App findOne(Long appId);
    public List<App> findAll();

    Long findAppIdByAppKey(String appKey);
}

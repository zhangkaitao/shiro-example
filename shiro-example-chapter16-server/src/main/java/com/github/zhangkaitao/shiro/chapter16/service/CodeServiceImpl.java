package com.github.zhangkaitao.shiro.chapter16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
@Service
public class CodeServiceImpl implements CodeService {

    private Cache cache;

    @Autowired
    public CodeServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    @Override
    public void addAuthCode(String authCode, String username) {
        cache.put(authCode, username);
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public boolean isValidAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    @Override
    public boolean isValidAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }
}

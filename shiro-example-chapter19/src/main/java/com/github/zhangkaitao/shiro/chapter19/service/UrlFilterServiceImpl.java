package com.github.zhangkaitao.shiro.chapter19.service;

import com.github.zhangkaitao.shiro.chapter19.dao.UrlFilterDao;
import com.github.zhangkaitao.shiro.chapter19.entity.UrlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-25
 * <p>Version: 1.0
 */
@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private UrlFilterDao urlFilterDao;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    @Override
    public UrlFilter createUrlFilter(UrlFilter urlFilter) {
        urlFilterDao.createUrlFilter(urlFilter);
        initFilterChain();
        return urlFilter;
    }



    @Override
    public UrlFilter updateUrlFilter(UrlFilter urlFilter) {
        urlFilterDao.updateUrlFilter(urlFilter);
        initFilterChain();
        return urlFilter;
    }

    @Override
    public void deleteUrlFilter(Long urlFilterId) {
        urlFilterDao.deleteUrlFilter(urlFilterId);
        initFilterChain();
    }

    @Override
    public UrlFilter findOne(Long urlFilterId) {
        return urlFilterDao.findOne(urlFilterId);
    }

    @Override
    public List<UrlFilter> findAll() {
        return urlFilterDao.findAll();
    }

    @PostConstruct
    public void initFilterChain() {
        shiroFilerChainManager.initFilterChains(findAll());
    }

}

package com.github.zhangkaitao.shiro.chapter17.service;

import com.github.zhangkaitao.shiro.chapter17.entity.Organization;
import com.github.zhangkaitao.shiro.chapter17.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceService {


    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

    Set<String> findPermissions(Set<Long> resourceIds);
}

package com.github.zhangkaitao.shiro.chapter17.dao;

import com.github.zhangkaitao.shiro.chapter17.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Resource: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Resource createResource(final Resource resource) {
        final String sql = "insert into sys_resource(name, type, permission, parent_id, parent_ids, available) values(?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, resource.getName());
                psst.setString(count++, resource.getType().name());
                psst.setString(count++, resource.getPermission());
                psst.setLong(count++, resource.getParentId());
                psst.setString(count++, resource.getParentIds());
                psst.setBoolean(count++, resource.getAvailable());
                return psst;
            }
        }, keyHolder);
        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }

    @Override
    public Resource updateResource(Resource resource) {
        final String sql = "update sys_resource set name=?, type=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                resource.getName(), resource.getType().name(), resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(), resource.getId());
        return resource;
    }

    public void deleteResource(Long resourceId) {
        final String sql = "delete from sys_resource where id=?";
        jdbcTemplate.update(sql, resourceId);
    }


    @Override
    public Resource findOne(Long resourceId) {
        final String sql = "select id, name, type, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class), resourceId);
        if(resourceList.size() == 0) {
            return null;
        }
        return resourceList.get(0);
    }

    @Override
    public List<Resource> findAll() {
        final String sql = "select id, name, type, permission, parent_id, parent_ids, available from sys_resource";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
    }

}

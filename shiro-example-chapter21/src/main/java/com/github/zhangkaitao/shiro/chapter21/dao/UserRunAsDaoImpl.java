package com.github.zhangkaitao.shiro.chapter21.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>UserRunAs: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class UserRunAsDaoImpl implements UserRunAsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void grantRunAs(Long fromUserId, Long toUserId) {
        String sql = "insert into sys_user_runas(from_user_id, to_user_id) values (?,?)";
        if(!exists(fromUserId, toUserId)) {
            jdbcTemplate.update(sql, fromUserId, toUserId);
        }
    }

    public boolean exists(Long fromUserId, Long toUserId) {
        String sql = "select count(1) from sys_user_runas where from_user_id=? and to_user_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, fromUserId, toUserId) != 0;
    }

    @Override
    public void revokeRunAs(Long fromUserId, Long toUserId) {
        String sql = "delete from sys_user_runas where from_user_id=? and to_user_id=?";
        jdbcTemplate.update(sql, fromUserId, toUserId);
    }

    @Override
    public List<Long> findFromUserIds(Long toUserId) {
        String sql = "select from_user_id from sys_user_runas where to_user_id=?";
        return jdbcTemplate.queryForList(sql, Long.class, toUserId);
    }

    @Override
    public List<Long> findToUserIds(Long fromUserId) {
        String sql = "select to_user_id from sys_user_runas where from_user_id=?";
        return jdbcTemplate.queryForList(sql, Long.class, fromUserId);
    }
}

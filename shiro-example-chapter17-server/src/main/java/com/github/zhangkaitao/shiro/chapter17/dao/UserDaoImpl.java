package com.github.zhangkaitao.shiro.chapter17.dao;

import com.github.zhangkaitao.shiro.chapter17.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public User createUser(final User user) {
        final String sql = "insert into oauth2_user(username, password, salt) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, user.getUsername());
                psst.setString(count++, user.getPassword());
                psst.setString(count++, user.getSalt());
                return psst;
            }
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public User updateUser(User user) {
        String sql = "update oauth2_user set username=?, password=?, salt=? where id=?";
        jdbcTemplate.update(
                sql,
                user.getUsername(), user.getPassword(), user.getSalt(), user.getId());
        return user;
    }

    public void deleteUser(Long userId) {
        String sql = "delete from oauth2_user where id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt from oauth2_user where id=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public List<User> findAll() {
        String sql = "select id, username, password, salt from oauth2_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }


    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt from oauth2_user where username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }
}

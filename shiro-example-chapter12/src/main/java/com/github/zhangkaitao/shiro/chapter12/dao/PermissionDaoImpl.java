package com.github.zhangkaitao.shiro.chapter12.dao;

import com.github.zhangkaitao.shiro.chapter12.entity.Permission;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionDaoImpl extends JdbcDaoSupport implements PermissionDao {

    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, permission.getPermission());
                psst.setString(2, permission.getDescription());
                psst.setBoolean(3, permission.getAvailable());
                return psst;
            }
        }, keyHolder);
        permission.setId(keyHolder.getKey().longValue());

        return permission;
    }

    public void deletePermission(Long permissionId) {
        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        getJdbcTemplate().update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        getJdbcTemplate().update(sql, permissionId);
    }

}

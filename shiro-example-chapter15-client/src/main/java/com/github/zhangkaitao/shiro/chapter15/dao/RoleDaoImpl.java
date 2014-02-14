package com.github.zhangkaitao.shiro.chapter15.dao;

import com.github.zhangkaitao.shiro.chapter15.entity.Role;
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
public class RoleDaoImpl extends JdbcDaoSupport implements RoleDao {

    public Role createRole(final Role Role) {
        final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, Role.getRole());
                psst.setString(2, Role.getDescription());
                psst.setBoolean(3, Role.getAvailable());
                return psst;
            }
        }, keyHolder);
        Role.setId(keyHolder.getKey().longValue());

        return Role;
    }

    public void deleteRole(Long roleId) {
        //首先把和role关联的相关表数据删掉
        String sql = "delete from sys_users_roles where role_id=?";
        getJdbcTemplate().update(sql, roleId);

        sql = "delete from sys_roles where id=?";
        getJdbcTemplate().update(sql, roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
        for(Long permissionId : permissionIds) {
            if(!exists(roleId, permissionId)) {
                getJdbcTemplate().update(sql, roleId, permissionId);
            }
        }
    }


    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
        for(Long permissionId : permissionIds) {
            if(exists(roleId, permissionId)) {
                getJdbcTemplate().update(sql, roleId, permissionId);
            }
        }
    }

    private boolean exists(Long roleId, Long permissionId) {
        String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
        return getJdbcTemplate().queryForObject(sql, Integer.class, roleId, permissionId) != 0;
    }

}

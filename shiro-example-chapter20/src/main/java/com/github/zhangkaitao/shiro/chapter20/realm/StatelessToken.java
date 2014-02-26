package com.github.zhangkaitao.shiro.chapter20.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessToken implements AuthenticationToken {

    private String username;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String username,  Map<String, ?> params, String clientDigest) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  Map<String, ?> getParams() {
        return params;
    }

    public void setParams( Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
       return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }
}

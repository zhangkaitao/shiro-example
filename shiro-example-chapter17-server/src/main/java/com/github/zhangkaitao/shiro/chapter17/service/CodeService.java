package com.github.zhangkaitao.shiro.chapter17.service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
public interface CodeService {

    static final String AUTH_CODE_KEY = "auth_code";
    static final String ACCESS_TOKEN_KEY = "access_token";

    //添加 auth code
    public void addAuthCode(String authCode, String username);
    //添加 access token
    public void addAccessToken(String accessToken, String username);

    //验证auth code是否有效
    boolean isValidAuthCode(String authCode);
    //验证access token是否有效
    boolean isValidAccessToken(String accessToken);

    //auth code / access token 过期时间
    long getExpireIn();



}

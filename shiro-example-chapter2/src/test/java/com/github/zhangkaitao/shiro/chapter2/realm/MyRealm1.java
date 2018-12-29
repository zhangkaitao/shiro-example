package com.github.zhangkaitao.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
public class MyRealm1 implements Realm {

    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; //浠呮敮鎸乁sernamePasswordToken绫诲瀷鐨凾oken
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //寰楀埌鐢ㄦ埛鍚�
        String password = new String((char[])token.getCredentials()); //寰楀埌瀵嗙爜
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //濡傛灉鐢ㄦ埛鍚嶉敊璇�
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //濡傛灉瀵嗙爜閿欒
        }
        //濡傛灉韬唤璁よ瘉楠岃瘉鎴愬姛锛岃繑鍥炰竴涓狝uthenticationInfo瀹炵幇锛�
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}

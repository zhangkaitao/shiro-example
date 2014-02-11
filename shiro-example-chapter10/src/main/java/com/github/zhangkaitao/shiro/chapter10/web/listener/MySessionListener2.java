package com.github.zhangkaitao.shiro.chapter10.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-8
 * <p>Version: 1.0
 */
public class MySessionListener2 extends SessionListenerAdapter {
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }
}

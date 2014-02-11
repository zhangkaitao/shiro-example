package com.github.zhangkaitao.shiro.chapter10;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-7
 * <p>Version: 1.0
 */
public class SessionTest extends BaseTest {

    @Test
    public void testGetSession() throws Exception{
        login("classpath:shiro.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();//获取会话

        System.out.println(session.getId());//获取会话ID
        System.out.println(session.getHost());//获取当前登录用户主机地址
        System.out.println(session.getTimeout());//获取超时时间
        System.out.println(session.getStartTimestamp()); //获取会话创建时间
        System.out.println(session.getLastAccessTime()); //获取最后访问时间
        Thread.sleep(1000L);
        session.touch();//更新会话最后访问时间
        System.out.println(session.getLastAccessTime());


        //会话属性操作
        session.setAttribute("key", "123");
        Assert.assertEquals("123", session.getAttribute("key"));
        session.removeAttribute("key");
    }

}

package com.github.zhangkaitao.shiro.chapter7.web.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-29
 * <p>Version: 1.0
 */
@WebServlet(name = "roleServlet", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        req.getRequestDispatcher("/WEB-INF/jsp/hasRole.jsp").forward(req, resp);
    }
}

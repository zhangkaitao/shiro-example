<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

当前在线人数：${sessionCount}人<br/>
<table class="table">
    <thead>
        <tr>
            <th style="width: 150px;">会话ID</th>
            <th>用户名</th>
            <th>主机地址</th>
            <th>最后访问时间</th>
            <th>已强制退出</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${sessions}" var="session">
            <tr>
                <td>${session.id}</td>
                <td>${zhangfn:principal(session)}</td>
                <td>${session.host}</td>
                <td><fmt:formatDate value="${session.lastAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${zhangfn:isForceLogout(session) ? '是' : '否'}</td>
                <td>
                    <c:if test="${not zhangfn:isForceLogout(session)}">
                        <a href="${pageContext.request.contextPath}/sessions/${session.id}/forceLogout">强制退出</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
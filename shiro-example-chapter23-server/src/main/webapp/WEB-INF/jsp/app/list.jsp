<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<shiro:hasPermission name="app:create">
    <a href="${pageContext.request.contextPath}/app/create">应用新增</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead>
        <tr>
            <th>应用名称</th>
            <th>应用KEY</th>
            <th>应用安全码</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${appList}" var="app">
            <tr>
                <td>${app.name}</td>
                <td>${app.appKey}</td>
                <td>${app.appSecret}</td>
                <td>
                    <shiro:hasPermission name="app:update">
                        <a href="${pageContext.request.contextPath}/app/${app.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="app:delete">
                        <a href="${pageContext.request.contextPath}/app/${app.id}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
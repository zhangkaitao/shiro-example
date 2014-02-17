<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<a href="${pageContext.request.contextPath}/client/create">客户端新增</a><br/>
<shiro:hasPermission name="client:create">
    <a href="${pageContext.request.contextPath}/client/create">客户端新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>客户端名</th>
            <th>客户端ID</th>
            <th>客户端安全KEY</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${clientList}" var="client">
            <tr>
                <td>${client.clientName}</td>
                <td>${client.clientId}</td>
                <td>${client.clientSecret}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/client/${client.id}/update">修改</a>
                    <a href="${pageContext.request.contextPath}/client/${client.id}/delete">删除</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
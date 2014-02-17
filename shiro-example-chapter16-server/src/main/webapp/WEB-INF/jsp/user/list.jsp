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

<a href="${pageContext.request.contextPath}/user/create">用户新增</a><br/>
<table class="table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/${user.id}/update">修改</a>
                    <a href="${pageContext.request.contextPath}/user/${user.id}/delete">删除</a>
                    <a href="${pageContext.request.contextPath}/user/${user.id}/changePassword">改密</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
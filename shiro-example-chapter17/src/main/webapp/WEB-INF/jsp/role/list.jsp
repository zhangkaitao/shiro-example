<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<a href="${pageContext.request.contextPath}/role/create">角色新增</a><br/>
<table class="table">
    <thead>
        <tr>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>拥有的资源</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.role}</td>
                <td>${role.description}</td>
                <td>${zhangfn:resourceNames(role.resourceIds)}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/role/${role.id}/update">修改</a>
                    |
                    <a href="${pageContext.request.contextPath}/role/${role.id}/delete">删除</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
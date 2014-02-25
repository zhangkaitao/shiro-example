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

<shiro:hasPermission name="urlFilter:create">
    <a href="${pageContext.request.contextPath}/urlFilter/create">URL新增</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead>
        <tr>
            <th>名称</th>
            <th>URL</th>
            <th>角色列表</th>
            <th>权限列表</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${urlFilterList}" var="urlFilter">
            <tr>
                <td>${urlFilter.name}</td>
                <td>${urlFilter.url}</td>
                <td>${urlFilter.roles}</td>
                <td>${urlFilter.permissions}</td>
                <td>
                    <shiro:hasPermission name="urlFilter:update">
                        <a href="${pageContext.request.contextPath}/urlFilter/${urlFilter.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="urlFilter:delete">
                        <a href="${pageContext.request.contextPath}/urlFilter/${urlFilter.id}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
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

<shiro:hasPermission name="authorization:create">
    <a href="${pageContext.request.contextPath}/authorization/create">授权新增</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead>
        <tr>
            <th>应用</th>
            <th>用户</th>
            <th>角色列表</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${authorizationList}" var="authorization">
            <tr>
                <td>${zhangfn:appName(authorization.appId)}</td>
                <td>${zhangfn:username(authorization.userId)}</td>
                <td>${zhangfn:roleNames(authorization.roleIds)}</td>
                <td>
                    <shiro:hasPermission name="authorization:update">
                        <a href="${pageContext.request.contextPath}/authorization/${authorization.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="authorization:delete">
                        <a href="${pageContext.request.contextPath}/authorization/${authorization.id}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
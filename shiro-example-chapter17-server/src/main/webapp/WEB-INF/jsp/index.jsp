<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>OAuth2集成</title>
</head>
<body>

<shiro:guest>
    游客你好，请<a href="${pageContext.request.contextPath}/login">登录</a>！
</shiro:guest>

<shiro:user>
    欢迎[<shiro:principal/>]，点击<a href="${pageContext.request.contextPath}/logout">退出</a>！<br/>
    <a href="${pageContext.request.contextPath}/client" target="_blank">客户端管理</a><br/>
    <a href="${pageContext.request.contextPath}/user" target="_blank">用户管理</a><br/>
</shiro:user>


</body>
</html>
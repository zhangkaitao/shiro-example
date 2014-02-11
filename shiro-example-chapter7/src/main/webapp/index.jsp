<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/login">登录</a><br/>
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
<a href="${pageContext.request.contextPath}/role">角色授权</a><br/>
<a href="${pageContext.request.contextPath}/permission">权限授权</a><br/>

</body>
</html>
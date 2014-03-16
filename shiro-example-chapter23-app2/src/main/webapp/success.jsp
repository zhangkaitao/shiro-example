<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
    hello app1.<br/>

    <shiro:guest>
        <a href="${pageContext.request.contextPath}/login?backUrl=${pageContext.request.contextPath}">点击登录</a>
    </shiro:guest>

    <shiro:authenticated>
        欢迎<shiro:principal/>登录<br/>
        <shiro:hasRole name="role2">
            您拥有role2角色<br/>
        </shiro:hasRole>
        <shiro:lacksRole name="role2">
            您没有role2角色<br/>
        </shiro:lacksRole>
        <shiro:lacksRole name="role1">
            您没有role1角色<br/>
        </shiro:lacksRole>

        <h2>设置会话属性</h2>
        <form action="${pageContext.request.contextPath}/attr" method="post">
            键：<input type="text" name="key">
            值：<input type="text" name="value">
            <input type="submit" value="设置会话属性">
        </form>
        <h2>获取会话属性</h2>
        <form action="${pageContext.request.contextPath}/attr" method="get">
            键：<input type="text" name="key">
            值：<input type="text" value="${value}">
            <input type="submit" value="获取会话属性">
        </form>
    </shiro:authenticated>

</body>
</html>
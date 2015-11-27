<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

    <form method="post">
        <div class="form-group">
            <label for="newPassword">新密码：</label>
            <input type="text" id="newPassword" name="newPassword"/>
        </div>
        <input type="submit" value="${op}">
    </form>

</body>
</html>
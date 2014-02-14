<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>简单Shiro权限管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout-default-latest.css">
</head>
<body>

<iframe class="ui-layout-center"
        src="${pageContext.request.contextPath}/welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎学习Shiro综合案例</div>
<div class="ui-layout-south">
    获取源码：<a href="https://github.com/zhangkaitao/shiro-example" target="_blank">https://github.com/zhangkaitao/shiro-example</a>
</div>
<div class="ui-layout-west">
    菜单
</div>


<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>
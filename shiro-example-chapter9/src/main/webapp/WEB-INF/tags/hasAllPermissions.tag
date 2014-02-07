<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="权限字符串列表" %>
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="权限字符串列表分隔符" %><%

    if(!StringUtils.hasText(delimiter)) {
        delimiter = ",";//默认逗号分隔
    }

    if(!StringUtils.hasText(name)) {
%>
        <jsp:doBody/>
<%
        return;
    }

    String[] roles = name.split(delimiter);

    if(!SecurityUtils.getSubject().isPermittedAll(roles)) {
        return;
    } else {
%>
        <jsp:doBody/>
<%
    }
%>
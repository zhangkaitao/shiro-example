<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ tag import="java.util.Arrays" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="角色列表" %>
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="角色列表分隔符" %><%

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

    if(!SecurityUtils.getSubject().hasAllRoles(Arrays.asList(roles))) {
        return;
    } else {
%>
        <jsp:doBody/>
<%
    }
%>
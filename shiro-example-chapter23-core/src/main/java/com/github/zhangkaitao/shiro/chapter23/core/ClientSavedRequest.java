package com.github.zhangkaitao.shiro.chapter23.core;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-14
 * <p>Version: 1.0
 */
public class ClientSavedRequest extends SavedRequest {
    private String scheme;
    private String domain;
    private int port;
    private String contextPath;
    private String successUrl;

    public ClientSavedRequest(HttpServletRequest request, String successUrl) {
        super(request);
        this.scheme = request.getScheme();
        this.domain = request.getServerName();
        this.port = request.getServerPort();
        this.successUrl = successUrl;
        this.contextPath = request.getContextPath();
    }

    public String getScheme() {
        return scheme;
    }

    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getRequestUrl() {
        String requestURI = getRequestURI();
        if(successUrl != null) {//1
            if(successUrl.toLowerCase().startsWith("http://") || successUrl.toLowerCase().startsWith("https://")) {
                return successUrl;
            } else if(!successUrl.startsWith(contextPath)) {//2
                requestURI = contextPath + successUrl;
            } else {//3
                requestURI = successUrl;
            }
        }

        StringBuilder requestUrl = new StringBuilder(getScheme());//4
        requestUrl.append("://");
        requestUrl.append(getDomain());//5
        //6
        if("http".equalsIgnoreCase(getScheme()) && getPort() != 80) {
            requestUrl.append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(getScheme()) && getPort() != 443) {
            requestUrl.append(String.valueOf(port));
        }
        //7
        requestUrl.append(requestURI);
        //8
        if (successUrl == null && getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestUrl.toString();
    }
}

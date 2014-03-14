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
        System.out.println(successUrl);
        String requestURI = getRequestURI();
        if(successUrl != null) {
            if(successUrl.toLowerCase().startsWith("http://") || successUrl.toLowerCase().startsWith("https://")) {
                return successUrl;
            } else if(!successUrl.startsWith(contextPath)) {
                requestURI = contextPath + successUrl;
            } else {
                requestURI = successUrl;
            }
        }

        StringBuilder requestUrl = new StringBuilder(getScheme());
        requestUrl.append("://");
        requestUrl.append(getDomain());
        if("http".equalsIgnoreCase(getScheme()) && getPort() != 80) {
            requestUrl.append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(getScheme()) && getPort() != 443) {
            requestUrl.append(String.valueOf(port));
        }
        requestUrl.append(requestURI);
        if (successUrl == null && getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestUrl.toString();
    }
}

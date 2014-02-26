package com.github.zhangkaitao.shiro.chapter20.filter;

import com.github.zhangkaitao.shiro.chapter20.Constants;
import com.github.zhangkaitao.shiro.chapter20.realm.StatelessToken;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String clientDigest = request.getParameter(Constants.PARAM_DIGEST);
        String username = request.getParameter(Constants.PARAM_USERNAME);
        Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
        params.remove(Constants.PARAM_DIGEST);

        StatelessToken token = new StatelessToken(username, params, clientDigest);

        try {
            getSubject(request, response).login(token);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response);
            return false;
        }
        return true;
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

}

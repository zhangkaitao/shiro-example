package com.github.zhangkaitao.shiro.chapter17.web.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-16
 * <p>Version: 1.0
 */
@RestController
public class RedirectController {

    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest req) {
        JSONObject object = new JSONObject();
        JSONObject headers = new JSONObject();
        JSONObject qp = new JSONObject();
        String json = "error!";
        try {
            Enumeration<String> enu = req.getHeaderNames();
            while(enu.hasMoreElements()) {
                String key = enu.nextElement();
                headers.put(key, req.getHeader(key));
            }
            object.put("headers", headers);

            Enumeration<String> params = req.getParameterNames();
            while(params.hasMoreElements()) {
                String name = params.nextElement();
                qp.put(name, req.getParameter(name));
            }
            object.put("queryParameters", qp);
            json = object.toString(4);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}

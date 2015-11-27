package com.github.zhangkaitao.shiro.chapter23.web.controller;

import com.github.zhangkaitao.shiro.chapter23.Constants;
import com.github.zhangkaitao.shiro.chapter23.entity.Resource;
import com.github.zhangkaitao.shiro.chapter23.service.AuthorizationService;
import com.github.zhangkaitao.shiro.chapter23.service.ResourceService;
import com.github.zhangkaitao.shiro.chapter23.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping("/")
    public String index(@CurrentUser com.github.zhangkaitao.shiro.chapter23.entity.User loginUser, Model model) {
        Set<String> permissions = authorizationService.findPermissions(Constants.SERVER_APP_KEY, loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}

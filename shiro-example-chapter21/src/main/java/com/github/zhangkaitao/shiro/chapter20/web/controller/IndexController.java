package com.github.zhangkaitao.shiro.chapter20.web.controller;

import com.github.zhangkaitao.shiro.chapter20.entity.Resource;
import com.github.zhangkaitao.shiro.chapter20.entity.User;
import com.github.zhangkaitao.shiro.chapter20.web.bind.annotation.CurrentUser;
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
    private com.github.zhangkaitao.shiro.chapter20.service.ResourceService resourceService;
    @Autowired
    private com.github.zhangkaitao.shiro.chapter20.service.UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}

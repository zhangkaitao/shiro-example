package com.github.zhangkaitao.shiro.chapter23.web.controller;

import com.github.zhangkaitao.shiro.chapter23.entity.Authorization;
import com.github.zhangkaitao.shiro.chapter23.service.AppService;
import com.github.zhangkaitao.shiro.chapter23.service.AuthorizationService;
import com.github.zhangkaitao.shiro.chapter23.service.RoleService;
import com.github.zhangkaitao.shiro.chapter23.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("authorization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("authorizationList", authorizationService.findAll());
        return "authorization/list";
    }

    @RequiresPermissions("authorization:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        Authorization authorization = new Authorization();
        model.addAttribute("authorization", authorization);
        model.addAttribute("op", "新增");
        return "authorization/edit";
    }

    @RequiresPermissions("authorization:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Authorization authorization, RedirectAttributes redirectAttributes) {
        authorizationService.createAuthorization(authorization);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/authorization";
    }

    @RequiresPermissions("authorization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("authorization", authorizationService.findOne(id));
        model.addAttribute("op", "修改");
        return "authorization/edit";
    }

    @RequiresPermissions("authorization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Authorization authorization, RedirectAttributes redirectAttributes) {
        authorizationService.updateAuthorization(authorization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/authorization";
    }

    @RequiresPermissions("authorization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("authorization", authorizationService.findOne(id));
        model.addAttribute("op", "删除");
        return "authorization/edit";
    }

    @RequiresPermissions("authorization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        authorizationService.deleteAuthorization(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/authorization";
    }

    private void setCommonData(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("appList", appService.findAll());
    }


}

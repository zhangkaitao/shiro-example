package com.github.zhangkaitao.shiro.chapter23.web.controller;

import com.github.zhangkaitao.shiro.chapter23.entity.App;
import com.github.zhangkaitao.shiro.chapter23.service.AppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @RequiresPermissions("app:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("appList", appService.findAll());
        return "app/list";
    }

    @RequiresPermissions("app:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        App app = new App();
        app.setAppKey(UUID.randomUUID().toString());
        app.setAppSecret(UUID.randomUUID().toString());
        model.addAttribute("app", app);
        model.addAttribute("op", "新增");
        return "app/edit";
    }

    @RequiresPermissions("app:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(App app, RedirectAttributes redirectAttributes) {
        appService.createApp(app);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/app";
    }

    @RequiresPermissions("app:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("app", appService.findOne(id));
        model.addAttribute("op", "修改");
        return "app/edit";
    }

    @RequiresPermissions("app:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(App app, RedirectAttributes redirectAttributes) {
        appService.updateApp(app);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/app";
    }

    @RequiresPermissions("app:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("app", appService.findOne(id));
        model.addAttribute("op", "删除");
        return "app/edit";
    }

    @RequiresPermissions("app:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        appService.deleteApp(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/app";
    }

}

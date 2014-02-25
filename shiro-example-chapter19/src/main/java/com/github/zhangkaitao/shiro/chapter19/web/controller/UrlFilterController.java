package com.github.zhangkaitao.shiro.chapter19.web.controller;

import com.github.zhangkaitao.shiro.chapter19.entity.UrlFilter;
import com.github.zhangkaitao.shiro.chapter19.service.UrlFilterService;
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
@RequestMapping("/urlFilter")
public class UrlFilterController {

    @Autowired
    private UrlFilterService urlFilterService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("urlFilterList", urlFilterService.findAll());
        return "urlFilter/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("urlFilter", new UrlFilter());
        model.addAttribute("op", "新增");
        return "urlFilter/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UrlFilter urlFilter, RedirectAttributes redirectAttributes) {
        urlFilterService.createUrlFilter(urlFilter);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/urlFilter";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("urlFilter", urlFilterService.findOne(id));
        model.addAttribute("op", "修改");
        return "urlFilter/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(UrlFilter urlFilter, RedirectAttributes redirectAttributes) {
        urlFilterService.updateUrlFilter(urlFilter);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/urlFilter";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("urlFilter", urlFilterService.findOne(id));
        model.addAttribute("op", "删除");
        return "urlFilter/edit";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        urlFilterService.deleteUrlFilter(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/urlFilter";
    }

}

package com.github.zhangkaitao.shiro.chapter19.web.controller;

import com.github.zhangkaitao.shiro.chapter19.entity.Organization;
import com.github.zhangkaitao.shiro.chapter19.service.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "organization/index";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/tree";
    }

    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        Organization parent = organizationService.findOne(parentId);
        model.addAttribute("parent", parent);
        Organization child = new Organization();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "organization/appendChild";
    }

    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(Organization organization) {
        organizationService.createOrganization(organization);
        return "redirect:/organization/success";
    }

    @RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        return "organization/maintain";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Organization organization, RedirectAttributes redirectAttributes) {
        organizationService.updateOrganization(organization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organization/success";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        organizationService.deleteOrganization(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/organization/success";
    }


    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "organization/move";
    }

    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(
            @PathVariable("sourceId") Long sourceId,
            @RequestParam("targetId") Long targetId) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.move(source, target);
        return "redirect:/organization/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "organization/success";
    }


}

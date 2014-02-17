package com.github.zhangkaitao.shiro.chapter17.web.controller;

import com.github.zhangkaitao.shiro.chapter17.entity.Client;
import com.github.zhangkaitao.shiro.chapter17.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>Client: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("clientList", clientService.findAll());
        return "client/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("op", "新增");
        return "client/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Client client, RedirectAttributes redirectAttributes) {
        clientService.createClient(client);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/client";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("op", "修改");
        return "client/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Client client, RedirectAttributes redirectAttributes) {
        clientService.updateClient(client);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/client";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("op", "删除");
        return "client/edit";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        clientService.deleteClient(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/client";
    }

}

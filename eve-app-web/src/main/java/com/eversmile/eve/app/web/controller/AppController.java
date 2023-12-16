package com.eversmile.eve.app.web.controller;

import com.eversmile.eve.app.web.payload.request.RoleRequest;
import com.eversmile.eve.app.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.eversmile.eve.app.web.common.AppComponents.*;

@Controller
public class AppController {

    @Autowired private AccountService accountService;

    @GetMapping("/role/add")
    public String addRole(Model model){
        model.addAttribute("role", new RoleRequest());
        model.addAttribute(COMPONENT, ROLE_COMPONENT);
        model.addAttribute(FRAGMENT, ROLE_FRAGMENT);
        return "index";
    }

    @PostMapping("/role/add")
    public String addRole(@ModelAttribute("role") RoleRequest roleRequest,
                          Model model,
                          BindingResult result){
        model.addAttribute("role", roleRequest);
        model.addAttribute(COMPONENT, ROLE_COMPONENT);
        model.addAttribute(FRAGMENT, ROLE_FRAGMENT);
        if(result.hasErrors()){
            return "index";
        }
        accountService.createRole(roleRequest);
        return "index";
    }

    @GetMapping("/role/list")
    public String roleList(Model model){

        model.addAttribute(COMPONENT, ROLE_LIST_COMPONENT);
        model.addAttribute(FRAGMENT, ROLE_LIST_FRAGMENT);
        return "index";
    }
}

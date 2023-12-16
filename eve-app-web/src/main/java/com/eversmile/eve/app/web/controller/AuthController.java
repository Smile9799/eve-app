package com.eversmile.eve.app.web.controller;

import com.eversmile.eve.app.web.payload.request.AppUserRequest;
import com.eversmile.eve.app.web.service.AccountService;
import jakarta.validation.Valid;
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
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AccountService accountService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute(COMPONENT, LOGIN_COMPONENT);
        model.addAttribute(FRAGMENT, LOGIN_FRAGMENT);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(COMPONENT, REGISTER_COMPONENT);
        model.addAttribute(FRAGMENT, REGISTER_FRAGMENT);
        model.addAttribute("appUser", new AppUserRequest());
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("appUser") @Valid AppUserRequest userRequest,
                           BindingResult result,
                           Model model){
        model.addAttribute("appUser", userRequest);
        model.addAttribute(COMPONENT, REGISTER_COMPONENT);
        model.addAttribute(FRAGMENT, REGISTER_FRAGMENT);

        if(result.hasErrors()){
            return "index";
        }

        if(accountService.userExists(userRequest.getEmail())){
            result.rejectValue("email", null,"There is already an account registered with the same email");
            return "index";
        }
        accountService.saveAppUser(userRequest);
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}

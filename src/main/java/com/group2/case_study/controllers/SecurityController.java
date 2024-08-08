package com.group2.case_study.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SecurityController {

    @GetMapping("/admin")
    public String adPage(Model model, @RequestParam(value = "error", defaultValue = "") String error) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "admin/user-management";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "") String error) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login/login";
    }


    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "redirect:/flight/home";
    }

    @GetMapping("/flight/home")
    public String homePage(Model model) {
        model.addAttribute("title", "Home");
        return "flight/home";
    }

    @GetMapping("/errorr")
    public String handleAccessDenied() {
        return "error/error";
    }
}
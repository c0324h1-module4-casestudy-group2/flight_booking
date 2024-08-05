package com.group2.case_study.controllers;

import com.group2.case_study.dtos.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login/SecurityForm");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }
}

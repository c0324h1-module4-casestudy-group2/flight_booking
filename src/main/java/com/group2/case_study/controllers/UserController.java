package com.group2.case_study.controllers;

import com.group2.case_study.models.User;
import com.group2.case_study.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user-management";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/create-user";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/create-user";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        model.addAttribute("user", user);
        return "admin/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/edit-user";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        model.addAttribute("user", user);
        return "admin/view-user";
    }
}

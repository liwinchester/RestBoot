package com.example.boot.controller;

import com.example.boot.model.User;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String listUsers(Model model){
        model.addAttribute("listUsers", userService.listUser());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userInfo", user);
        model.addAttribute("user", new User());
        model.addAttribute("roles", user.getRolesForTable());

        return "users";
    }


}

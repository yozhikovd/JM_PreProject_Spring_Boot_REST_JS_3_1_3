package com.yozhikovd.jm_preproject_spring_boot_3_1_1.controllers;

import com.yozhikovd.jm_preproject_spring_boot_3_1_1.models.User;
import com.yozhikovd.jm_preproject_spring_boot_3_1_1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String anyMethodNameGoesHere(Model model, Authentication authentication) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("auth", authentication);
        return "show-current-user";
    }

}

package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.User;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String ShowUser(Model model, Authentication authentication) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("auth", authentication);
        return "show-current-user";
    }

}

package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    @GetMapping("/")
    public String getAdminPage() {
        return "admin";
    }
}

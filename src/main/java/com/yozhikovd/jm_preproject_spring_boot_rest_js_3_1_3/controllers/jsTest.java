package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/js")
public class jsTest {

    @GetMapping
    public String js() {
        return "js";
    }

}

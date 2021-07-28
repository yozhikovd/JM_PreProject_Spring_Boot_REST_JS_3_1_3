package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dto.UserDto;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MainRestController {

    private final UserService userService;
    public MainRestController(UserService userService) {
        this.userService = userService;
    }

//////////////////////////////////////////////////////////

    @GetMapping("/users")
    public List<UserDto> getAllUsers () {
        return userService.getAllUsersDTO();

    }







}

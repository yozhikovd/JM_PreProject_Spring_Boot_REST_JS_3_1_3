package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dto.UserDto;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.exeption.NoSuchUserEx;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.exeption.UserIncorrectData;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.User;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsersDTO();

    }

    @GetMapping("/users/{id}")
    public UserDto getUserFromID(@PathVariable int id) {
        UserDto userDto = userService.getUserFromID(id);

        if (userDto == null) {
            throw new NoSuchUserEx("Нет такого пользователя с ID: " + id);
        }
        return userDto;
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handlerException(NoSuchUserEx ex) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }


}

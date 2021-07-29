package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dto.UserDto;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.exeption.NoSuchUserEx;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.exeption.UserIncorrectData;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.Role;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.User;
import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class MainRestController {

    private final UserService userService;

    public MainRestController(UserService userService) {
        this.userService = userService;
    }

//    показать всех пользователей

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsersDTO();
    }

    //    показать пользователя по id

    @GetMapping("/users/{id}")
    public UserDto getUserFromID(@PathVariable long id) {
        UserDto userDto = userService.getUserFromID(id);

        if (userDto == null) {
            throw new NoSuchUserEx("Not found user with ID: " + id);
        }
        return userDto;
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handlerException(NoSuchUserEx ex) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    //    добавить пользователя

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user){

        Set<Role> roles = new HashSet<>();
        if (user.getRole().contains("ADMIN")) {
            roles.add(new Role(1, "ADMIN"));
        }
        if (user.getRole().contains("USER")) {
            roles.add(new Role(2, "USER"));
        }
        if (user.getRole() == null) {
            roles.add(new Role(2, "USER"));
        }
        user.setRoles(roles);
        userService.addNewUser(user);
        return user;
    }

    //    удалить пользователя по id

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){

        Set<Role> roles = new HashSet<>();
        if (user.getRole().contains("ADMIN")) {
            roles.add(new Role(1, "ADMIN"));
        }
        if (user.getRole().contains("USER")) {
            roles.add(new Role(2, "USER"));
        }
        if (user.getRole() == null) {
            roles.add(new Role(2, "USER"));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}

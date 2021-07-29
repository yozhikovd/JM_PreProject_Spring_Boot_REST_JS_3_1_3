package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.controllers;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dto.UserDto;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        final List<UserDto> userDtoList = userService.getAllUsersDTO();

        return userDtoList != null && !userDtoList.isEmpty()
                ? new ResponseEntity<>(userDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //    показать пользователя по id

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserFromID(@PathVariable long id) {
        final UserDto userDto = userService.getUserFromID(id);

        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //    добавить пользователя

    @PostMapping("/users")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {

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

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    удалить пользователя по id

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        boolean statusUpdate = false;

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
        try {
            user.setRoles(roles);
            userService.updateUser(user);
            statusUpdate = true;
        } catch (RuntimeException ignored) {

        }

        return statusUpdate
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        boolean statusDelete = false;
        try {
            userService.deleteUser(id);
            statusDelete = true;
        } catch (RuntimeException ignored) {
        }

        return statusDelete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

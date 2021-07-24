package com.yozhikovd.jm_preproject_spring_boot_3_1_1.controllers;

import com.yozhikovd.jm_preproject_spring_boot_3_1_1.models.Role;
import com.yozhikovd.jm_preproject_spring_boot_3_1_1.models.User;
import com.yozhikovd.jm_preproject_spring_boot_3_1_1.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("usersList", userService.userList());
        return "show-all-users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userById", userService.getUserById(id));
        return "show-current-user";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "add-new-user";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @RequestParam(required = false, name = "username") String username,
                             @RequestParam(required = false, name = "ADMIN") String ADMIN,
                             @RequestParam(required = false, name = "USER") String USER) {

        //////////////////////// проверка на уникальность username и ошибки ////////////////////////////////

        long i = userService.userList().stream().filter(user1 -> user1.getUsername().equals(username)).count();
        if (i > 0){
            bindingResult.rejectValue("username", "error.username", "Username must be Unique!");
        }
        if (bindingResult.hasErrors()){
            return "add-new-user";}

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        Set<Role> roles = new HashSet<>();
        if (ADMIN != null) {
            roles.add(new Role(1, ADMIN));
        }
        if (USER != null) {
            roles.add(new Role(2, USER));
        }
        if (ADMIN == null && USER == null) {
            roles.add(new Role(2, USER));
        }

        user.setRoles(roles);
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit-user";

    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id,
                         @RequestParam(required = false, name = "ADMIN") String ADMIN,
                         @RequestParam(required = false, name = "USER") String USER){

        if (bindingResult.hasErrors())
            return "edit-user";

        Set<Role> roles = new HashSet<>();
        if (ADMIN != null) {
            roles.add(new Role(1, ADMIN));
        }
        if (USER != null) {
            roles.add(new Role(2, USER));
        }
        if (ADMIN == null && USER == null ) {
            roles.add(new Role(2, USER));
        }
            user.setRoles(roles);

        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/error")
    public String error() {
        return "error";
    }

}

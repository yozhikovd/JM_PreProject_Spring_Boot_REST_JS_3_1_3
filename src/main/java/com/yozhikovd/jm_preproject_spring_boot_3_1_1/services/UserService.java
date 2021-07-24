package com.yozhikovd.jm_preproject_spring_boot_3_1_1.services;

import com.yozhikovd.jm_preproject_spring_boot_3_1_1.models.User;
import java.util.List;

public interface UserService {
    List<User> userList();
    User getUserById(int id);
    void addNewUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User findByUsername(String username);
  }

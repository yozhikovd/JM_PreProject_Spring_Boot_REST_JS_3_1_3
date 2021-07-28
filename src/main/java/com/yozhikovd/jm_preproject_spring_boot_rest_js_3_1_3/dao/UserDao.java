package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dao;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.User;
import java.util.List;


public interface UserDao {
    List<User> userList();
    User getUserById(int id);
    void addNewUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User findByUsername(String username);
}

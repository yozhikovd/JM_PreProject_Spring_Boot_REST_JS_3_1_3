package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.repository;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteById(Long id);
}

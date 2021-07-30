package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.services;

import com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.models.Role;

import java.util.Set;

public interface RoleService {
    void createRoles(Set<Role> roles);
    Set<Role> getAllRoles();
}

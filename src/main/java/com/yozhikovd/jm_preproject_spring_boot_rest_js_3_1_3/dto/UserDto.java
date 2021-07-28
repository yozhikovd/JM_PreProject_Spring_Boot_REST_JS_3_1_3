package com.yozhikovd.jm_preproject_spring_boot_rest_js_3_1_3.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private String roles;
}

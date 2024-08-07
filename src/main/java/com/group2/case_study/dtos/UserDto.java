package com.group2.case_study.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String avatar;
    private Boolean activated;
    private String rememberToken;
    private Set<String> roles;
}

package com.group2.case_study.services;

import com.group2.case_study.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    List<UserDto> getUsersByFullName(String fullName);
    UserDto getUserById(Integer userId);
    Iterable<UserDto> findAll();
    Optional<UserDto> findById(Integer id);
    boolean save(UserDto userDto);
    void remove(Integer id);
}

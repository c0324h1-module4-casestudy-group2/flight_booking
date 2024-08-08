package com.group2.case_study.services;

import com.group2.case_study.models.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Integer id);
    void save(User user);
    void deleteById(Integer id);
}

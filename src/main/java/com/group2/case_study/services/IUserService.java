package com.group2.case_study.services;

import com.group2.case_study.models.User;

public interface IUserService {
    User findUserByUsername(String username);
}

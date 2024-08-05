package com.group2.case_study.services;

public interface SecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}

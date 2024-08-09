package com.group2.case_study.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        // Kiểm tra vai trò của người dùng và chuyển hướng tới trang tương ứng
        String redirectUrl = "/home"; // Trang mặc định nếu không có vai trò cụ thể

        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            redirectUrl = "/admin/flights";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"))) {
            redirectUrl = "/home";
        }

        response.sendRedirect(redirectUrl);
    }
}
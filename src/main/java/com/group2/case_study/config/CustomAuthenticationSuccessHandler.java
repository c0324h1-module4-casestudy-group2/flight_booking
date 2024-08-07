// File: com/group2/case_study/config/CustomAuthenticationSuccessHandler.java
package com.group2.case_study.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.isEmpty()) {
            roles.add("ROLE_CUSTOMER");
            response.sendRedirect("/flight/home");
        }
        if (roles.contains("ROLE_ADMIN") || roles.contains("ROLE_EMPLOYEE")) {
            response.sendRedirect("/admin");
        } else if (roles.contains("ROLE_CUSTOMER")) {
            response.sendRedirect("/flight/home");
        } else {
            response.sendRedirect("/login?error=true");
        }
    }
}

package com.group2.case_study.payload.response;

import jakarta.servlet.http.Cookie;
import jakarta.validation.constraints.NotBlank;


public class LoginResponse {

    @NotBlank
    private String message;

    private Cookie cookie;

    public LoginResponse() {
        super();
    }

    public LoginResponse(@NotBlank String message) {
        super();
        this.message = message;
    }

    public LoginResponse(String message, Cookie cookie) {
        this.message = message;
        this.cookie = cookie;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

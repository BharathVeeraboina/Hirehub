package com.hirehub.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users/profile")
    public String getProfile() {
        return "This is a protected user profile";
    }
}
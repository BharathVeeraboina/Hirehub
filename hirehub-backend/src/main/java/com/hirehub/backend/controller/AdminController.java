package com.hirehub.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/api/admin/test")
    public String adminAccess() {
        return "Admin Access Granted";
    }
}
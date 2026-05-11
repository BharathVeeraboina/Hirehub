package com.hirehub.backend.controller;

import com.hirehub.backend.dto.ProfileDto;
import com.hirehub.backend.entity.User;
import com.hirehub.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getCurrentUser(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody ProfileDto profileDto) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.updateUserProfile(email, profileDto));
    }
}
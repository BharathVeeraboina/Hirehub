package com.hirehub.backend.controller;

import com.hirehub.backend.entity.User;
import com.hirehub.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserTestController {

    private final UserRepository userRepository;

    public UserTestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/test")
    public User createUser() {

        User user = User.builder()
                .name("Test User")
                .email("test@example.com")
                .password("123456")
                .role("USER")
                .build();

        return userRepository.save(user);
    }
}
package com.hirehub.backend.service;

import com.hirehub.backend.config.JwtUtil;
import com.hirehub.backend.entity.User;
import com.hirehub.backend.dto.RegisterRequest;
import com.hirehub.backend.dto.LoginRequest;
import com.hirehub.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ✅ REGISTER
    public User registerUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(hashedPassword)
                .role(request.getRole() != null && !request.getRole().isEmpty() ? request.getRole() : "USER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    // ✅ LOGIN (UPDATED)
    public Map<String, Object> loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of(
                "token", token,
                "role", user.getRole()
        );
    }

    // ✅ GET ALL USERS (ADMIN)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    // ✅ GET CURRENT USER
    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ UPDATE USER PROFILE
    public User updateUserProfile(String email, com.hirehub.backend.dto.ProfileDto dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getTitle() != null) user.setTitle(dto.getTitle());
        if (dto.getBio() != null) user.setBio(dto.getBio());
        if (dto.getSkills() != null) user.setSkills(dto.getSkills());
        if (dto.getLocation() != null) user.setLocation(dto.getLocation());
        if (dto.getPortfolioUrl() != null) user.setPortfolioUrl(dto.getPortfolioUrl());

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
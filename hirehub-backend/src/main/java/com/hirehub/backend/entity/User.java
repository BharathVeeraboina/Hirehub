package com.hirehub.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String phone;

    private String title;

    @Column(length = 1000)
    private String bio;

    private String skills;

    private String location;

    private String portfolioUrl;

}
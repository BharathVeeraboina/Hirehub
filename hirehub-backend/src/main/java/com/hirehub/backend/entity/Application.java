package com.hirehub.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 🔗 Relationship with Job
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String resumePath;
    private String status;

    private LocalDateTime appliedAt;
}
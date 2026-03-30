package com.hirehub.backend.repository;

import com.hirehub.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hirehub.backend.entity.User;
import com.hirehub.backend.entity.Job;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByUserAndJob(User user, Job job);
    List<Application> findByUser(User user);
    List<Application> findByJob(Job job);
    long countByJob(Job job);
}
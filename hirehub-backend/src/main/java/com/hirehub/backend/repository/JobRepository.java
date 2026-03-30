package com.hirehub.backend.repository;

import com.hirehub.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hirehub.backend.entity.User;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    List<Job> findByRecruiter(User recruiter);
}
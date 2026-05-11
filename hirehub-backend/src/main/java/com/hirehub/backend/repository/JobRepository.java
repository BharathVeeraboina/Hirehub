package com.hirehub.backend.repository;

import com.hirehub.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hirehub.backend.entity.User;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    List<Job> findByRecruiter(User recruiter);

    @Query("""
SELECT j FROM Job j
WHERE 
(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))
AND (:minSalary IS NULL OR j.salary >= :minSalary)
""")
    Page<Job> filterJobs(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("minSalary") Integer minSalary,
            Pageable pageable
    );
}
package com.hirehub.backend.service;

import com.hirehub.backend.dto.JobResponse;
import com.hirehub.backend.entity.Job;
import com.hirehub.backend.entity.User;
import com.hirehub.backend.repository.JobRepository;
import com.hirehub.backend.repository.UserRepository;
import com.hirehub.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    public JobService(JobRepository jobRepository,UserRepository userRepository,ApplicationRepository applicationRepository) {
        this.userRepository=userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository=applicationRepository;
    }

    public Job createJob(Job job, String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        job.setRecruiter(recruiter);
        job.setCreatedAt(LocalDateTime.now());

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    public Job updateJob(Long id, Job updatedJob) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setCompany(updatedJob.getCompany());
        job.setLocation(updatedJob.getLocation());
        job.setSalary(updatedJob.getSalary());

        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jobRepository.delete(job);
    }

    public Page<Job> getJobs(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return jobRepository.findAll(pageable);
    }

    public Page<Job> searchJobs(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    public List<JobResponse> getMyJobs(String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Job> jobs = jobRepository.findByRecruiter(recruiter);

        return jobs.stream().map(job -> {

            long count = applicationRepository.countByJob(job);

            return JobResponse.builder()
                    .id(job.getId())
                    .title(job.getTitle())
                    .company(job.getCompany())
                    .applicationsCount((int) count)
                    .build();

        }).toList();
    }

    public Page<Job> filterJobs(String keyword,
                                String location,
                                Integer minSalary,
                                int page,
                                int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository.filterJobs(keyword, location, minSalary, pageable);
    }
}
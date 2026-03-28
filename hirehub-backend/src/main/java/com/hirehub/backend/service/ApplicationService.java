package com.hirehub.backend.service;

import com.hirehub.backend.entity.*;
import com.hirehub.backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              JobRepository jobRepository,
                              UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Application applyJob(Long jobId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ PREVENT DUPLICATE
        if (applicationRepository.existsByUserAndJob(user, job)) {
            throw new RuntimeException("You already applied for this job");
        }

        Application application = Application.builder()
                .user(user)
                .job(job)
                .status("APPLIED")
                .appliedAt(LocalDateTime.now())
                .build();

        return applicationRepository.save(application);
    }

    public List<Application> getMyApplications(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByUser(user);
    }

    public Application updateStatus(Long applicationId, String status, String email) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // ✅ SECURITY CHECK
        if (!application.getJob().getRecruiter().getEmail().equals(email)) {
            throw new RuntimeException("Not allowed");
        }

        application.setStatus(status);

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsForJob(Long jobId, String email) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ SECURITY CHECK
        if (!job.getRecruiter().getEmail().equals(email)) {
            throw new RuntimeException("You are not allowed to view this job");
        }

        return applicationRepository.findByJob(job);
    }



    public Application applyJobWithResume(Long jobId, String email, MultipartFile file) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.existsByUserAndJob(user, job)) {
            throw new RuntimeException("Already applied");
        }

        try {
            // 📁 Save file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);

            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            Application application = Application.builder()
                    .user(user)
                    .job(job)
                    .status("APPLIED")
                    .resumePath(path.toString()) // ✅ save path
                    .appliedAt(LocalDateTime.now())
                    .build();

            return applicationRepository.save(application);

        } catch (Exception e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
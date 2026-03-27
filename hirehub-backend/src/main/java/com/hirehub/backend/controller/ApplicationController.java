package com.hirehub.backend.controller;

import com.hirehub.backend.entity.Application;
import com.hirehub.backend.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // ✅ Apply for job
    @PostMapping("/{jobId}")
    @PreAuthorize("hasRole('USER')")
    public Application apply(@PathVariable Long jobId, Authentication authentication) {

        String email = authentication.getName();

        return applicationService.applyJob(jobId, email);
    }

    // ✅ Get my applications
    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<Application> myApplications(Authentication authentication) {

        String email = authentication.getName();

        return applicationService.getMyApplications(email);
    }


    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('RECRUITER')")
    public Application updateStatus(@PathVariable Long id,
                                    @RequestParam String status) {
        return applicationService.updateStatus(id, status);
    }
}
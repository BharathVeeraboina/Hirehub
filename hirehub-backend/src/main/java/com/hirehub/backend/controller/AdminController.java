package com.hirehub.backend.controller;

import com.hirehub.backend.entity.User;
import com.hirehub.backend.entity.Job;
import com.hirehub.backend.service.UserService;
import com.hirehub.backend.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final JobService jobService;

    public AdminController(UserService userService, JobService jobService) {
        this.userService = userService;
        this.jobService = jobService;
    }

    @GetMapping("/test")
    public String adminAccess() {
        return "Admin Access Granted";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }
}
package com.hirehub.backend.controller;

import com.hirehub.backend.entity.Job;
import com.hirehub.backend.service.JobService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import com.hirehub.backend.repository.JobRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @PreAuthorize("hasRole('RECRUITER')")
    public Job createJob(@RequestBody Job job, Authentication authentication) {

        String email = authentication.getName();

        return jobService.createJob(job, email);
    }

//    @GetMapping
//    public List<Job> getJobs() {
//        return jobService.getAllJobs();
//    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }


    @GetMapping
    public Page<Job> getJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return jobService.getJobs(page, size);
    }

    @GetMapping("/search")
    public Page<Job> searchJobs(@RequestParam String keyword,
                                @RequestParam int page,
                                @RequestParam int size) {
        return jobService.searchJobs(keyword, page, size);
    }

}
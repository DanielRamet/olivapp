package com.xapaya.olivapp.jobs.controller;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.model.Job;
import com.xapaya.olivapp.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> addJob(JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.addJob(jobRequest));
    }
}

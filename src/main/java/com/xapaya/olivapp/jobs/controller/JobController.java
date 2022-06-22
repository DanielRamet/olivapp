package com.xapaya.olivapp.jobs.controller;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.model.Job;
import com.xapaya.olivapp.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> addJob(JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.addJob(jobRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> updateJob(@PathParam("id") String id, JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.updateJob(jobRequest, id));
    }

    @GetMapping
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(jobService.getJobsByCurrentUser());
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<?> employeeJobs(@RequestParam Date date) {
        return ResponseEntity.ok(jobService.getJobsEmployee(date));
    }
}

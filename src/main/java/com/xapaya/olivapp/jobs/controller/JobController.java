package com.xapaya.olivapp.jobs.controller;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.model.JobType;
import com.xapaya.olivapp.jobs.model.RequestType;
import com.xapaya.olivapp.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @PostMapping("")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> addJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.addJob(jobRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> updateJob(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.updateJob(jobRequest, id));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllByUser() {
        return ResponseEntity.ok(jobService.getJobsByCurrentUser());
    }

    @GetMapping
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> search(@RequestParam RequestType type,
                                    @RequestParam JobType jobType,
                                    @RequestParam int season,
                                    @RequestParam(defaultValue = "false") boolean withDisabled) {
        return ResponseEntity.ok(jobService.search(type, jobType, season, withDisabled));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(jobService.deleteById(id));
    }
}

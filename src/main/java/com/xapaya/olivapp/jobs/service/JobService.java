package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.model.Job;
import com.xapaya.olivapp.jobs.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final NotificationService notificationService;

    public Job addJob(JobRequest jobRequest) {
        Job job = this.getJob(jobRequest);
        job = jobRepository.save(job);

        // add notification
        notificationService.addNotification(job.getId(), "ADDED");

        return job;
    }

    public Job updateJob(JobRequest jobRequest, String id) {
        Job job = this.getJob(jobRequest);
        job.setId(id);
        job.setDisabled(jobRequest.isDisabled());
        job = jobRepository.save(job);
        // notification
        notificationService.addNotification(id, "UPDATED");
        return job;
    }

    public List<Job> getJobsByCurrentUser() {
        return jobRepository.findJobsByUserId(UserUtils.getCurrentUserId());
    }

    public List<Job> getJobsEmployee(Date date) {
        if (date == null) {
            date = Date.from(Instant.now());
        }
        return jobRepository.getCurrentAvailableJobs(date);
    }

    private Job getJob(JobRequest jobRequest) {
        Job job = Job.builder()
                .description(jobRequest.getDescription())
                .type(jobRequest.getType())
                .start(jobRequest.getStart())
                .end(jobRequest.getEnd())
                .build();
        job.setUserId(UserUtils.getCurrentUserId());
        return job;
    }
}

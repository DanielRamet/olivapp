package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.model.Job;
import com.xapaya.olivapp.jobs.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void updateJob(JobRequest jobRequest, String id) {
        Job job = this.getJob(jobRequest);
        job.setId(id);
        jobRepository.save(job);

        // notification
        notificationService.addNotification(id, "UPDATED");
    }

    public List<Job> getJobsByCurrentUser() {
        return jobRepository.findJobsByUserId(UserUtils.getCurrentUserId());
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

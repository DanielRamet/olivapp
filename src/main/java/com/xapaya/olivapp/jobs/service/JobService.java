package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.jobs.controller.dto.JobRequest;
import com.xapaya.olivapp.jobs.controller.exception.MessageException;
import com.xapaya.olivapp.jobs.model.Job;
import com.xapaya.olivapp.jobs.model.JobType;
import com.xapaya.olivapp.jobs.model.RequestType;
import com.xapaya.olivapp.jobs.repository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class JobService {

    private final JobRepository jobRepository;
    private final NotificationService notificationService;

    public Job addJob(JobRequest jobRequest) {
        log.info("request: {}", jobRequest);
        Job job = this.getJob(jobRequest);
        log.info("Job to add: {}", job);
        job = jobRepository.save(job);

        // add notification
        notificationService.addNotification(job.getId(), "ADDED");

        return job;
    }

    public Job updateJob(JobRequest jobRequest, String id) {
        return jobRepository.findById(id)
                .filter(j -> UserUtils.getCurrentUserId().equals(j.getUserId()))
                .map(j -> {
                    j.setDisabled(jobRequest.isDisabled());
                    j.setJobType(jobRequest.getJobType());
                    j.setDescription(jobRequest.getDescription());
                    j.setRequestType(jobRequest.getType());
                    j.setEnd(jobRequest.getEnd());
                    j.setSeason(jobRequest.getSeason());
                    j.setStart(jobRequest.getStart());
                    return j;
                })
                .map(j -> {
                    Job saved = jobRepository.save(j);
                    log.info("updated {}", saved);
                    notificationService.addNotification(saved.getId(), "UPDATED");
                    return saved;
                })
                .orElseThrow(() -> new MessageException("Job Not found with id: " + id));
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

    public List<Job> search(RequestType type, JobType jobType, int season, boolean withDisabled) {
        return jobRepository.findJobsByRequestTypeAndJobTypeAndSeason(type, jobType, season);
    }

    public boolean deleteById(String id) {
        boolean res = false;
        try {
            jobRepository.deleteById(id);
            log.info("Deleted job with id: {}", id);
            res = true;
        } catch (Exception e) {
            log.error("Error deleting.", e);
        }

        return res;
    }

    private Job getJob(JobRequest jobRequest) {
        return Job.builder()
                .description(jobRequest.getDescription())
                .requestType(jobRequest.getType())
                .start(jobRequest.getStart())
                .end(jobRequest.getEnd())
                .disabled(jobRequest.isDisabled())
                .userId(UserUtils.getCurrentUserId())
                .jobType(jobRequest.getJobType())
                .season(jobRequest.getSeason())
                .build();
    }
}

package com.xapaya.olivapp.jobs.repository;

import com.xapaya.olivapp.jobs.model.Job;

import java.util.Date;
import java.util.List;

public interface JobRepositoryCustom {
    List<Job> getCurrentAvailableJobs(Date date);
}

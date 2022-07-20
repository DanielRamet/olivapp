package com.xapaya.olivapp.jobs.controller.dto;

import com.xapaya.olivapp.jobs.model.JobType;
import com.xapaya.olivapp.jobs.model.RequestType;
import lombok.Value;

import java.util.Date;

@Value
public class JobRequest {
    String description;
    RequestType type;
    JobType jobType;
    Integer season;
    Date start;
    Date end;
    boolean disabled;
}

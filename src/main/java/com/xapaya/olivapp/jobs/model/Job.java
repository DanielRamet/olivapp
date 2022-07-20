package com.xapaya.olivapp.jobs.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("jobs")
@Builder
public class Job {
    @Id
    private String id;
    private String userId; //owner

    private RequestType requestType;

    private JobType jobType;
    private Integer season;

    private Date start;
    private Date end;
    private String description;

    private boolean disabled;
}

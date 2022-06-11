package com.xapaya.olivapp.jobs.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document("applicants")
@Builder
public class Applicant {
    @Id
    private String id;
    private List<String> availableTypes;
    private String description;
    private Date start;
    private Date end;
    private String userId;
}

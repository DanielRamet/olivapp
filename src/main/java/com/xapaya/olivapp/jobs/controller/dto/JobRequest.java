package com.xapaya.olivapp.jobs.controller.dto;

import lombok.Value;

import java.util.Date;

@Value
public class JobRequest {
    String description;
    String type;
    Date start;
    Date end;
    boolean disabled;
}

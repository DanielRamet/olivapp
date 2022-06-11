package com.xapaya.olivapp.jobs.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("notifications")
@Builder
public class Notification {
    @Id
    String id;
    String userId;
    String entityId;
    Instant created;
    String operation;
}

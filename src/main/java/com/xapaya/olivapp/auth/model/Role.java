package com.xapaya.olivapp.auth.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
@Builder
public class Role {
    @Id
    private String id;
    private String name;
}

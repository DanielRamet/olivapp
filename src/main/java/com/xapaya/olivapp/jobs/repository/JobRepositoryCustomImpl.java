package com.xapaya.olivapp.jobs.repository;

import com.xapaya.olivapp.jobs.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class JobRepositoryCustomImpl implements JobRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Job> getCurrentAvailableJobs(Date date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("endDate").gte(date).orOperator(Criteria.where("endDate").isNull()));
        return mongoTemplate.find(query, Job.class);
    }
}

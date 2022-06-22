package com.xapaya.olivapp.jobs.repository;

import com.xapaya.olivapp.jobs.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job, String>, JobRepositoryCustom {

    List<Job> findJobsByUserId(String userId);
}

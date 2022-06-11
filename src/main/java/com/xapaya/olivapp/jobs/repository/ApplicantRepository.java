package com.xapaya.olivapp.jobs.repository;

import com.xapaya.olivapp.jobs.model.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends MongoRepository<Applicant, String> {
}

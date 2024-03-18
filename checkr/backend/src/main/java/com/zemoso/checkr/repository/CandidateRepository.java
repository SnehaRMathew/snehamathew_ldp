package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    List<Candidate> findAll();
    Candidate getById(Long id);
}

package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    //Page<CandidateReport> findByNameAndStatus(Pageable pageable, String name, String status);
    Page<Candidate> findByName(Pageable pageable, String name);
}

package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateReportRepository extends JpaRepository<CandidateReport, Long> {
    Optional<CandidateReport> findById(Long id);

}

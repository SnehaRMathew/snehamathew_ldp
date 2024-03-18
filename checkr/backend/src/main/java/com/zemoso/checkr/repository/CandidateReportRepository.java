package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CandidateReportRepository extends JpaRepository<CandidateReport, Long> {
    Optional<CandidateReport> findByCandidateId(Long id);

//    @Modifying
//    @Transactional
//    @Query("update Candidate_Report c set c.adjudication = 'engaged' where c.candidate_id = :candidate_id")
//    String engaged(@Param("candidate_id") long id);

}

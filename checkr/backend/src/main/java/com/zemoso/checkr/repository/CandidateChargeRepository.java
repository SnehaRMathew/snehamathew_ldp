package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.CandidateCharge;
import com.zemoso.checkr.entity.CourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateChargeRepository extends JpaRepository<CandidateCharge, Long> {
    List<CandidateCharge> findByCandidateId(long id);
}

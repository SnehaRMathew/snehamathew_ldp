package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.CourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateSearchRepository extends JpaRepository<CourtSearch,Long> {
    public Optional<List<CourtSearch>> findByCandidateId(long id) ;
}
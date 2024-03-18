package com.zemoso.checkr.repository;

import com.zemoso.checkr.entity.CourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourtSearchRepository extends JpaRepository<CourtSearch, Long> {
   Optional<List<CourtSearch>> findByCandidateId(Long id);
}

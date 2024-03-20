package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {

    public CandidateReportDTO findById(long id) throws NoSuchCandidateExistsException;
    public Page<Candidate> findByFilter(Pageable pageable, String name );
    public List<Candidate> findAll();

}

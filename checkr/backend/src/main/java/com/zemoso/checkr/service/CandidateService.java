package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateReport;
import com.zemoso.checkr.entity.Search;
import com.zemoso.checkr.repository.CandidateReportRepository;
import com.zemoso.checkr.repository.CandidateRepository;
import com.zemoso.checkr.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional @Service
public class CandidateService {
    CandidateRepository candidateRepository;
    CandidateReportRepository candidateReportRepository;
    SearchRepository searchRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, CandidateReportRepository candidateReportRepository, SearchRepository searchRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateReportRepository = candidateReportRepository;
        this.searchRepository = searchRepository;
    }

    public Page<Candidate> findByFilter(Pageable pageable, String name ) {
        return candidateRepository.findByName(pageable,name);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Optional<CandidateReportDTO> findById(Long id) {
        Candidate candidate =candidateRepository.getById(id);
        Optional <CandidateReport> candidateReport= candidateReportRepository.findById(id);
        Optional<Search> search=searchRepository.findById(id);
       return convertToDTO(candidate,candidateReport,search);
    }

    private Optional<CandidateReportDTO> convertToDTO(Candidate candidate, Optional<CandidateReport> candidateReport, Optional<Search> search) {
        //yet to implement
        return null;
    }
}

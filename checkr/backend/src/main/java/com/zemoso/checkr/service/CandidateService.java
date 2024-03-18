package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.CandidateDTO;
import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.dto.CourtSearchDTO;
import com.zemoso.checkr.dto.ReportDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateReport;
import com.zemoso.checkr.entity.CourtSearch;
import com.zemoso.checkr.entity.Search;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.repository.CandidateReportRepository;
import com.zemoso.checkr.repository.CandidateRepository;
import com.zemoso.checkr.repository.CourtSearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional @Service
public class CandidateService {
    CandidateRepository candidateRepository;
    CandidateReportRepository candidateReportRepository;

    CandidateReportService candidateReportService;

    CourtSearchRepository courtSearchRepository;
    ModelMapper modelMapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, CandidateReportRepository candidateReportRepository,
                            CandidateReportService candidateReportService, CourtSearchRepository courtSearchRepository,
                            ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateReportRepository = candidateReportRepository;
        this.candidateReportService = candidateReportService;
        this.courtSearchRepository = courtSearchRepository;
        this.modelMapper = modelMapper;
    }

    public Page<Candidate> findByFilter(Pageable pageable, String name ) {
        return candidateRepository.findByName(pageable,name);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public CandidateReportDTO findById(long id) throws NoSuchCandidateExistsException{
        Candidate candidate =candidateRepository.getById(id);
        CandidateReport candidateReport= candidateReportRepository.findByCandidateId(id).orElseThrow(()->new NoSuchCandidateExistsException(id));
        System.out.println("Candidate Report"+candidateReport.toString());
        List<CourtSearch> courtSearch= courtSearchRepository.findByCandidateId(id).orElse(Collections.emptyList());
        System.out.println("Court Search"+courtSearch.toString());
        List<Search> listSearch = courtSearch.stream()
                .map(c ->(Search) c.getSearch())
                .collect(Collectors.toList());

        return convertToDTO(candidate,candidateReport,courtSearch);
    }

    private CandidateReportDTO convertToDTO(Candidate candidate, CandidateReport candidateReport, List<CourtSearch> courtSearchList) {
        CandidateDTO candidateDTO=modelMapper.map(candidate, CandidateDTO.class);
        ReportDTO reportDTO=modelMapper.map(candidateReport, ReportDTO.class);
        System.out.println(courtSearchList);
        List<CourtSearchDTO> courtSearchDTO= courtSearchList.stream()
                .map(c-> getMap(c))
                .collect(Collectors.toList());
        System.out.println(courtSearchDTO);
        CandidateReportDTO candidateReportDTO= new CandidateReportDTO();
        candidateReportDTO.setCandidate(candidateDTO);
        candidateReportDTO.setReport(reportDTO);
        candidateReportDTO.setCourtSearch(courtSearchDTO);
        System.out.println(candidateReportDTO);
        return candidateReportDTO;
    }

    private CourtSearchDTO getMap(CourtSearch c) {
        CourtSearchDTO courtSearchDTO=modelMapper.map(c, CourtSearchDTO.class);
        courtSearchDTO.setSearchName(c.getSearch().getSearchName());
        return courtSearchDTO;
    }
}

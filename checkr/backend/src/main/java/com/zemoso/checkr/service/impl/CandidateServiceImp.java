package com.zemoso.checkr.service.impl;

import com.zemoso.checkr.dto.CandidateDTO;
import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.dto.CourtSearchDTO;
import com.zemoso.checkr.dto.CandidateDetailDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateDetails;
import com.zemoso.checkr.entity.CourtSearch;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.repository.CandidateDetailsRepository;
import com.zemoso.checkr.repository.CandidateRepository;
import com.zemoso.checkr.repository.CourtSearchRepository;
import com.zemoso.checkr.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional @Service
public class CandidateServiceImp implements CandidateService {
    CandidateRepository candidateRepository;
    CandidateDetailsRepository candidateDetailsRepository;

    CourtSearchRepository courtSearchRepository;
    ModelMapper modelMapper;
    private Logger logger = Logger.getLogger(CandidateServiceImp.class.getName());

    @Autowired
    public CandidateServiceImp(CandidateRepository candidateRepository,
                               CandidateDetailsRepository candidateDetailsRepository,
                               CourtSearchRepository courtSearchRepository,
                               ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateDetailsRepository = candidateDetailsRepository;
        this.courtSearchRepository = courtSearchRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Page<Candidate> findByFilter(Pageable pageable, String name ) {
        return candidateRepository.findByName(pageable,name);
    }
    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }
    @Override
    public CandidateReportDTO findById(long id) throws NoSuchCandidateExistsException{
        Candidate candidate =candidateRepository.findById(id).orElseThrow(()->new NoSuchCandidateExistsException(id));
        CandidateDetails candidateDetails= candidateDetailsRepository.findByCandidateId(id).orElseThrow(()->new NoSuchCandidateExistsException(id));
        logger.log(Level.INFO,"Candidate Report {0}",candidateDetails.toString());
        List<CourtSearch> courtSearch= courtSearchRepository.findByCandidateId(id).orElse(Collections.emptyList());
        logger.log(Level.INFO,"Court Search {0}",courtSearch.toString());
        var dto= convertToDTO(candidate,candidateDetails,courtSearch);
        System.out.println(dto);
        return dto;
    }

    private CandidateReportDTO convertToDTO(Candidate candidate, CandidateDetails candidateDetails, List<CourtSearch> courtSearchList) {
        CandidateDTO candidateDTO=modelMapper.map(candidate, CandidateDTO.class);
        CandidateDetailDTO candidateDetailDTO =modelMapper.map(candidateDetails, CandidateDetailDTO.class);
        List<CourtSearchDTO> courtSearchDTO= courtSearchList.stream()
                .map(this::getMap)
                .toList();
        logger.log(Level.INFO,courtSearchDTO.toString());
        return CandidateReportDTO
                .builder()
                .candidate (candidateDTO)
                .report(candidateDetailDTO)
                .courtSearch(courtSearchDTO)
                .build();
    }

    private CourtSearchDTO getMap(CourtSearch c) {
        CourtSearchDTO courtSearchDTO=modelMapper.map(c, CourtSearchDTO.class);
        courtSearchDTO.setSearchName(c.getSearch().getSearchName());
        return courtSearchDTO;
    }
}

package com.zemoso.checkr.service.impl;

import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.entity.*;
import com.zemoso.checkr.enums.AdjudicationType;
import com.zemoso.checkr.enums.AdverseActionStatus;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.exception.NoSuchChargeExistsException;
import com.zemoso.checkr.repository.*;
import com.zemoso.checkr.service.CandidateReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateReportServiceImp implements CandidateReportService {

    private SearchChargeRepository searchChargeRepository;
    private CandidateDetailsRepository candidateDetailsRepository;
    private CandidateSearchRepository candidateSearchRepository;
    private ModelMapper modelMapper;
    private AdverseActionRepository adverseActionRepository;

    private CandidateChargeRepository candidateChargeRepository;

    @Autowired
    public CandidateReportServiceImp(CandidateDetailsRepository candidateDetailsRepository, ModelMapper modelMapper,
                                     AdverseActionRepository adverseActionRepository,
                                     CandidateChargeRepository candidateChargeRepository,
                                     CandidateSearchRepository candidateSearchRepository,
                                     SearchChargeRepository searchChargeRepository) {
        this.candidateDetailsRepository = candidateDetailsRepository;
        this.modelMapper =modelMapper;
        this.adverseActionRepository=adverseActionRepository;
        this.candidateChargeRepository=candidateChargeRepository;
        this.candidateSearchRepository =candidateSearchRepository;
        this.searchChargeRepository=searchChargeRepository;
    }
@Override
    public String engaged(long id) throws NoSuchCandidateExistsException {
    CandidateDetails candidateDetails= candidateDetailsRepository.findByCandidateId(id).orElseThrow(() -> new NoSuchCandidateExistsException(id));
        candidateDetails.setAdjudication(AdjudicationType.ENGAGED.name());
        candidateDetailsRepository.save(candidateDetails);
        return candidateDetails.getCandidate().getName()+" is marked as engaged";
    }
@Override
    public String sendAdverseAction(long id, int days, List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException {
    CandidateDetails candidateDetails= candidateDetailsRepository.findByCandidateId(id).orElseThrow(() -> new NoSuchCandidateExistsException(id));
        candidateDetails.setAdjudication(AdjudicationType.ADVERSE_ACTION.name());
        candidateDetailsRepository.save(candidateDetails);
        AdverseAction adverseAction= settingAdverseAction(candidateDetails.getCandidate(),days);
        adverseActionRepository.save(adverseAction);
        long l = convertTocharge(candidateDetails.getCandidate(), searchChargeDTOList);
        return "Adverse-action send to "+candidateDetails.getCandidate().getName()+". Charges added "+l;
    }
    private long convertTocharge(Candidate candidate, List <SearchChargeDTO> searchChargeDTOList) {
       return searchChargeDTOList.stream()
                .map(c->c.getId())
                .map(this::getSearchCharge)
                .map(c->CandidateCharge.builder().candidate(candidate).searchCharge(c).build())
                .map(c->candidateChargeRepository.save(c))
                .count();

    }

    private SearchCharge getSearchCharge(Long c) {
        return searchChargeRepository.findById(c).orElse(new SearchCharge());
    }

    private AdverseAction settingAdverseAction(Candidate candidate, int days) {
        return AdverseAction.builder().candidate(candidate)
                .preNoticeDate(convertToLocalDateViaInstant(LocalDate.now()))
                .postNoticeDate(convertToLocalDateViaInstant(LocalDate.now().plusDays(days)))
                .status(AdverseActionStatus.valueOf(AdverseActionStatus.PENDING.name()))
                .build();

    }
    // Convert LocalDate to java.util.Date using Instant
    public static Date convertToLocalDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
@Override
    public List<SearchChargeDTO> getCharges(Long id) throws NoSuchCandidateExistsException {
        List<CourtSearch> candidateCharge =candidateSearchRepository.findByCandidateId(id)
                .orElseThrow(()->new NoSuchChargeExistsException(id));
        return candidateCharge.stream()
                .map(c->c.getSearch().getId())
                .map(c->getCourtSearchIntegerFunction(c))
                .flatMap(List::stream)
                .map(c->modelMapper.map(c, SearchChargeDTO.class))
                .collect(Collectors.toList());

    }
    private List<SearchCharge> getCourtSearchIntegerFunction(Long c) {
       return searchChargeRepository.findBySearchId(c);
    }
}

package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.entity.*;
import com.zemoso.checkr.enums.AdjudicationType;
import com.zemoso.checkr.enums.AdverseActionStatus;
import com.zemoso.checkr.enums.CandidateStatus;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.exception.NoSuchChargeExistsException;
import com.zemoso.checkr.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateReportService {

    private SearchChargeRepository searchChargeRepository;
    private CandidateReportRepository candidateReportRepository;
    private CandidateSearchRepository candidateSearchRepository;
    private ModelMapper modelMapper;
    private AdverseActionRepository adverseActionRepository;

    private CandidateChargeRepository candidateChargeRepository;

    @Autowired
    public CandidateReportService(CandidateReportRepository candidateReportRepository, ModelMapper modelMapper,
                                  AdverseActionRepository adverseActionRepository,
                                  CandidateChargeRepository candidateChargeRepository,
                                  CandidateSearchRepository candidateSearchRepository,
                                  SearchChargeRepository searchChargeRepository) {
        this.candidateReportRepository = candidateReportRepository;
        this.modelMapper =modelMapper;
        this.adverseActionRepository=adverseActionRepository;
        this.candidateChargeRepository=candidateChargeRepository;
        this.candidateSearchRepository =candidateSearchRepository;
        this.searchChargeRepository=searchChargeRepository;
    }

    public String engaged(long id) throws NoSuchCandidateExistsException {
        CandidateReport candidateReport= candidateReportRepository.findByCandidateId(id).orElseThrow(() -> new NoSuchCandidateExistsException(id));
        candidateReport.setAdjudication(AdjudicationType.ENGAGED.name());
        candidateReportRepository.save(candidateReport);
        return candidateReport.getCandidate().getName()+" is marked as engaged";
    }

    public String sendAdverseAction(long id, int days, List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException {
        CandidateReport candidateReport= candidateReportRepository.findByCandidateId(id).orElseThrow(() -> new NoSuchCandidateExistsException(id));
        candidateReport.setAdjudication(AdjudicationType.ADVERSE_ACTION.name());
        candidateReportRepository.save(candidateReport);
        AdverseAction adverseAction= settingAdverseAction(candidateReport.getCandidate(),days);
        adverseActionRepository.save(adverseAction);
        long l = convertTocharge(candidateReport.getCandidate(), searchChargeDTOList);
        return "Adverse-action send to "+candidateReport.getCandidate().getName()+". Charges added "+l;
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

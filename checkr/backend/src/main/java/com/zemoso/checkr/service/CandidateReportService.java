package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;

import java.util.List;

public  interface CandidateReportService {
    public String engaged(long id) throws NoSuchCandidateExistsException ;

    public String sendAdverseAction(long id, int days, List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException ;

    public List<SearchChargeDTO> getCharges(Long id) throws NoSuchCandidateExistsException ;
}

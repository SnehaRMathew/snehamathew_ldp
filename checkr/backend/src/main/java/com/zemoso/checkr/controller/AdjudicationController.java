package com.zemoso.checkr.controller;

import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.service.CandidateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates/")
public class AdjudicationController {
    CandidateReportService candidateReportService;
    @Autowired
    public AdjudicationController(CandidateReportService candidateReportService) {
        this.candidateReportService = candidateReportService;
    }

    @PatchMapping("{id}/engaged")
    public String engageCandidate(@PathVariable long id) throws NoSuchCandidateExistsException {
       return candidateReportService.engaged(id);
    }

    @PutMapping ("{id}/AdverseAction")
    public String adverseActionCandidate(@PathVariable long id, @RequestParam(name="days") int days, @RequestBody List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException {
        System.out.println(days+"    sdasd  0"+searchChargeDTOList);
        return candidateReportService.sendAdverseAction(id,days, searchChargeDTOList);
    }

    @GetMapping("{id}/AdverseCharges")
    public List<SearchChargeDTO> getCharges(@PathVariable long id) throws NoSuchCandidateExistsException {
        return candidateReportService.getCharges(id);
    }


}

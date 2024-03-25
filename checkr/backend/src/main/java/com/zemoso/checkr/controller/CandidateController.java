package com.zemoso.checkr.controller;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.service.AdverseActionService;
import com.zemoso.checkr.service.CandidateReportService;
import com.zemoso.checkr.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/Candidate")
public class CandidateController {
    private final CandidateService candidateService;
    private CandidateReportService candidateReportServiceImp;
    private final AdverseActionService adverseActionServiceImp;
    private   Logger LOGGER = Logger.getLogger(CandidateController.class.getName());

    @Autowired
    public CandidateController(CandidateService candidateService,
                               CandidateReportService candidateReportServiceImp,
                               AdverseActionService adverseActionServiceImp) {
        this.candidateService = candidateService;
        this.candidateReportServiceImp = candidateReportServiceImp;
        this.adverseActionServiceImp = adverseActionServiceImp;
    }



    @GetMapping
    public ResponseEntity<Page<Candidate>> getAllCandidates(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String status) {
        LOGGER.info("Get all Candidate");
        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortOrder,sortBy));

        Page<Candidate> candidateResult = candidateService.findByFilter(pageable,name);
        return ResponseEntity.ok(candidateResult);
    }

   @GetMapping("/export")

   public ResponseEntity<List<Candidate>> getCandidates() {
       LOGGER.info("Export Candidate");
       List<Candidate> candidateResult = candidateService.findAll();
       return ResponseEntity.ok(candidateResult);
   }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateReportDTO> getCandidateById(@PathVariable Long id) throws NoSuchCandidateExistsException {
        LOGGER.info("Searching for Candidate " + id);
        CandidateReportDTO candidateReportDTO = candidateService.findById(id);
        return ResponseEntity.ok(candidateReportDTO);
    }

    @PatchMapping("{id}/engaged")
    public String engageCandidate(@PathVariable long id) throws NoSuchCandidateExistsException {
        return candidateReportServiceImp.engaged(id);
    }

    @PutMapping ("{id}/AdverseAction")
    public String adverseActionCandidate(@PathVariable long id, @RequestParam(name="days") int days, @RequestBody List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException {
        return candidateReportServiceImp.sendAdverseAction(id,days, searchChargeDTOList);
    }

    @GetMapping("{id}/AdverseCharges")
    public List<SearchChargeDTO> getCharges(@PathVariable long id) throws NoSuchCandidateExistsException {
        return candidateReportServiceImp.getCharges(id);
    }

    @GetMapping("{id}/all-adverse-actions")
    @Operation(summary = "Get all adverse action that were send to the candidates")
    public List<AdverseActionDTO> getAllAdverseActions() {
        return adverseActionServiceImp.findAll();
    }

}

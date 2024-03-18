package com.zemoso.checkr.controller;

import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.service.CandidateService;
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
    private final static Logger LOGGER = Logger.getLogger(CandidateController.class.getName());

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
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

//    @GetMapping
//    public ResponseEntity<Page<CandidateReport>> getAllCandidates(
//             @RequestParam(defaultValue = "1") int pageNo,
//             @RequestParam(defaultValue = "10") int pageSize,
//             @RequestParam(defaultValue = "name") String sortBy,
//             @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
//             @RequestParam(defaultValue = "") String name,
//             @RequestParam(defaultValue = "") String status) {
//        LOGGER.info("Get all Candidate");
//        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortOrder,sortBy));
//
//        Page<CandidateReport> candidateResult = candidateService.findByFilter(pageable,name,status);
//        return ResponseEntity.ok(candidateResult);
//    }



   @GetMapping("/all")
   public ResponseEntity<List<Candidate>> getCandidates() {
       LOGGER.info("Get all Candidate");
       List<Candidate> candidateResult = candidateService.findAll();
       return ResponseEntity.ok(candidateResult);
   }


    @GetMapping("/{id}")
    public ResponseEntity<CandidateReportDTO> getCandidateById(@PathVariable Long id) throws NoSuchCandidateExistsException {
        LOGGER.info("Searching for Candidate " + id);
        CandidateReportDTO candidateReportDTO = candidateService.findById(id);
        return ResponseEntity.ok(candidateReportDTO);
    }
}

package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/Candidate")
public class CandidateController {
    private final CandidateRepository candidateRepository;
    private final static Logger LOGGER = Logger.getLogger(CandidateController.class.getName());

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return ResponseEntity.ok(candidates);
    }

    @PostMapping ("/add")
    public ResponseEntity<String> saveCandidate(@RequestBody Candidate candidate) {
        LOGGER.info("Saving Candidate " +candidate);
        candidateRepository.save(candidate);
        ResponseEntity<String> successful = ResponseEntity.ok("Successful");
        return successful;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) throws NoSuchCandidateExistsException {
        LOGGER.info("Searching for Candidate " + id);
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new NoSuchCandidateExistsException(id));
        return ResponseEntity.ok(candidate);
    }
}

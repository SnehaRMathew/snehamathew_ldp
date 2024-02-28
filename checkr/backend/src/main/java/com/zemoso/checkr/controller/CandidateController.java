package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Candidate;

import com.zemoso.checkr.repository.CandidateRepository;
import com.zemoso.checkr.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping
    public ResponseEntity<String> saveCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateRepository.save(candidate);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateRepository.getById(id);
        return ResponseEntity.ok(candidate);

    }
}

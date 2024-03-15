package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Case;
import com.zemoso.checkr.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Case")
public class CaseController {
    private final CaseRepository caseRepository;

    @Autowired
    public CaseController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @GetMapping
    public List<Case> getAllHRs() {
        return caseRepository.findAll();
    }
}

package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.AdverseAction;
import com.zemoso.checkr.repository.AdverseActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/AdverseAction")
public class AdverseActionController {

    private final AdverseActionRepository adverseActionRepository;

    @Autowired
    public AdverseActionController(AdverseActionRepository adverseActionRepository) {
        this.adverseActionRepository = adverseActionRepository;
    }

    @GetMapping
    public List<AdverseAction> getAllHRs() {
        return adverseActionRepository.findAll();
    }
}

package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.AdverseAction;
import com.zemoso.checkr.repository.AdverseActionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AdverseActions")
@Tag(name= "Adverse-Actions", description = "Operations that are related to adverse Action Notification")
public class AdverseActionController {

    private final AdverseActionRepository adverseActionRepository;

    @Autowired
    public AdverseActionController(AdverseActionRepository adverseActionRepository) {
        this.adverseActionRepository = adverseActionRepository;
    }

    @GetMapping
    @Operation(summary = "Get all adverse action that were send to the candidates")
    public List<AdverseAction> getAllHRs() {
        return adverseActionRepository.findAll();
    }
}

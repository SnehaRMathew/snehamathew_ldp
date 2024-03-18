package com.zemoso.checkr.controller;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.repository.AdverseActionRepository;
import com.zemoso.checkr.service.AdverseActionService;
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

    private final AdverseActionService adverseActionService;

    @Autowired
    public AdverseActionController(AdverseActionService adverseActionService) {
        this.adverseActionService = adverseActionService;
    }

    @GetMapping
    @Operation(summary = "Get all adverse action that were send to the candidates")
    public List<AdverseActionDTO> getAllAdverseActions() {
        return adverseActionService.findAll();
    }
}

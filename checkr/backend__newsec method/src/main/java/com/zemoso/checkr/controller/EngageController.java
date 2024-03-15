package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Engage;
import com.zemoso.checkr.repository.EngageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Engage")
public class EngageController {
    private final EngageRepository engageRepository;

    @Autowired
    public EngageController(EngageRepository engageRepository) {
        this.engageRepository = engageRepository;
    }

    @GetMapping
    public List<Engage> getAllHRs() {
        return engageRepository.findAll();
    }
}

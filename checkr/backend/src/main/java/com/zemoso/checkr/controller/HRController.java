package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.HR;
import com.zemoso.checkr.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/HR")
public class HRController {
    private final HRRepository hrRepository;

    @Autowired
    public HRController(HRRepository hrRepository) {
        this.hrRepository = hrRepository;
    }

    @GetMapping
    public List<HR> getAllHRs() {
        return hrRepository.findAll();
    }
}

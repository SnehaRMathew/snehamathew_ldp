package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.HR;
import com.zemoso.checkr.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> saveHR(@RequestBody HR hr) {
        try{
            hrRepository.save(hr);
            return ResponseEntity.ok("Successful");
        }
        catch(Exception ex)
        {  return ResponseEntity.badRequest().body("Error in processing");}
    }


}

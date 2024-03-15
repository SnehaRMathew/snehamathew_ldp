package com.zemoso.checkr.controller;

import com.zemoso.checkr.entity.AdverseActionNotification;
import com.zemoso.checkr.repository.AdverseActionNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AdverseActionNotification")
public class AdverseActionNotificationController {

    private final AdverseActionNotificationRepository adverseActionNotificationRepository;

    @Autowired
    public AdverseActionNotificationController(AdverseActionNotificationRepository adverseActionNotificationRepository) {
        this.adverseActionNotificationRepository = adverseActionNotificationRepository;
    }

    @GetMapping
    public List<AdverseActionNotification> getAllHRs() {
        return adverseActionNotificationRepository.findAll();
    }
}

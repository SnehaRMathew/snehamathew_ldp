package com.zemoso.checkr.service.impl;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.entity.AdverseAction;
import com.zemoso.checkr.repository.AdverseActionRepository;
import com.zemoso.checkr.service.AdverseActionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdverseActionServiceImp implements AdverseActionService {
    ModelMapper modelMapper;

    @Autowired
    public AdverseActionServiceImp(ModelMapper modelMapper, AdverseActionRepository adverseActionRepository) {
        this.modelMapper = modelMapper;
        this.adverseActionRepository = adverseActionRepository;
    }

    AdverseActionRepository adverseActionRepository;
    public List<AdverseActionDTO> findAll() {
        List<AdverseAction> adverseAction= adverseActionRepository.findAll();
        System.out.println(adverseAction);
        return adverseAction.stream()
                .map(this::getMap)
                .toList();

    }
    private AdverseActionDTO getMap(AdverseAction c) {
        AdverseActionDTO adverseActionDTO=modelMapper.map(c, AdverseActionDTO.class);
        adverseActionDTO.setName(c.getCandidate().getName());
        return adverseActionDTO;
    }

}

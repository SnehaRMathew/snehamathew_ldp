package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.dto.CourtSearchDTO;
import com.zemoso.checkr.entity.AdverseAction;
import com.zemoso.checkr.entity.CourtSearch;
import com.zemoso.checkr.repository.AdverseActionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdverseActionService {
    ModelMapper modelMapper;

    @Autowired
    public AdverseActionService(ModelMapper modelMapper, AdverseActionRepository adverseActionRepository) {
        this.modelMapper = modelMapper;
        this.adverseActionRepository = adverseActionRepository;
    }

    AdverseActionRepository adverseActionRepository;
    public List<AdverseActionDTO> findAll() {
        List<AdverseAction> adverseAction= adverseActionRepository.findAll();
        System.out.println(adverseAction);
        List<AdverseActionDTO> adverseActionDTOList= adverseAction.stream()
                .map(c-> getMap(c))
                .collect(Collectors.toList());
         return adverseActionDTOList;
    }
    private AdverseActionDTO getMap(AdverseAction c) {
        AdverseActionDTO adverseActionDTO=modelMapper.map(c, AdverseActionDTO.class);
        adverseActionDTO.setName(c.getCandidate().getName());
        return adverseActionDTO;
    }
}

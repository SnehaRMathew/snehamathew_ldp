package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.entity.AdverseAction;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.enums.AdverseActionStatus;
import com.zemoso.checkr.repository.AdverseActionRepository;
import com.zemoso.checkr.service.impl.AdverseActionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdverseActionServiceTest {
    @Mock
    private AdverseActionRepository adverseActionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AdverseActionServiceImp adverseActionServiceImp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Candidate expectedCandidate = Candidate.builder().id(1).name("James Mayer").build();
        AdverseAction adverseAction1 = AdverseAction.builder().id(1).candidate(expectedCandidate)
                .status(AdverseActionStatus.PENDING).preNoticeDate(new Date()).postNoticeDate(new Date()).build();
        AdverseAction adverseAction2 = AdverseAction.builder().id(2).candidate(expectedCandidate)
                .status(AdverseActionStatus.PENDING).preNoticeDate(new Date()).postNoticeDate(new Date()).build();
        List<AdverseAction> expectedAdverseActions = Arrays.asList(adverseAction1, adverseAction2); // create a list of AdverseAction objects
        when(adverseActionRepository.findAll()).thenReturn(expectedAdverseActions);
        when(modelMapper.map(adverseAction1,AdverseActionDTO.class)).thenReturn(new AdverseActionDTO());
        when(modelMapper.map(adverseAction2,AdverseActionDTO.class)).thenReturn(new AdverseActionDTO());
        // Act
        List<AdverseActionDTO> actualAdverseActionDTOList = adverseActionServiceImp.findAll();

        // Assert
        assertEquals(expectedAdverseActions.size(), actualAdverseActionDTOList.size());
    }
}

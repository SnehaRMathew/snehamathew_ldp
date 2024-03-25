package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateDetails;
import com.zemoso.checkr.entity.CourtSearch;
import com.zemoso.checkr.entity.Search;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.exception.NoSuchChargeExistsException;
import com.zemoso.checkr.repository.*;
import com.zemoso.checkr.service.impl.CandidateReportServiceImp;
import com.zemoso.checkr.service.impl.CandidateServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CandidateReportServiceTest {
    @Mock
    private SearchChargeRepository searchChargeRepository;
    @Mock
    private CandidateDetailsRepository candidateDetailsRepository;
    @Mock
    private CandidateSearchRepository candidateSearchRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AdverseActionRepository adverseActionRepository;
    @Mock
    private CandidateChargeRepository candidateChargeRepository;

    @InjectMocks
    private CandidateReportServiceImp candidateReportServiceImp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testEngaged() throws NoSuchCandidateExistsException {
        // Arrange
        long id = 1;
        CandidateDetails expectedCandidateDetails = new CandidateDetails(); // create a CandidateDetails object
        when(candidateDetailsRepository.findByCandidateId(id)).thenReturn(Optional.of(expectedCandidateDetails));

        // Act
        String actualResult = candidateReportServiceImp.engaged(id);

        // Assert
        assertEquals(expectedCandidateDetails.getCandidate().getName() + " is marked as engaged", actualResult);
    }

    @Test
    public void testSendAdverseAction() throws NoSuchCandidateExistsException {
        // Arrange
        long id = 1;
        int days = 7;
        List<SearchChargeDTO> searchChargeDTOList = Collections.emptyList(); // create a list of SearchChargeDTO objects
        CandidateDetails expectedCandidateDetails = new CandidateDetails(); // create a CandidateDetails object
        when(candidateDetailsRepository.findByCandidateId(id)).thenReturn(Optional.of(expectedCandidateDetails));

        // Act
        String actualResult = candidateReportServiceImp.sendAdverseAction(id, days, searchChargeDTOList);

        // Assert
        assertEquals("Adverse-action send to " + expectedCandidateDetails.getCandidate().getName() + ". Charges added " + searchChargeDTOList.size(), actualResult);
    }
    @Test
    public void testGetCharges() throws NoSuchChargeExistsException {
        // Arrange
        long id = 1;
        Candidate expectedCandidate = Candidate.builder().id(id).name("James Mayer").build();
        SearchChargeDTO searchChargeDTO1 = new SearchChargeDTO(); // create a SearchChargeDTO object
        SearchChargeDTO searchChargeDTO2 = new SearchChargeDTO(); // create another SearchChargeDTO object
        List<SearchChargeDTO> expectedSearchChargeDTOList = Arrays.asList(searchChargeDTO1, searchChargeDTO2); // create a list of SearchChargeDTO objects
        CourtSearch courtSearch1 = CourtSearch.builder().id(1).candidate(expectedCandidate).status("clear")
                .search(Search.builder().id(2).searchName("saerch 2").build()).build(); // create a CourtSearch object
        CourtSearch courtSearch2 = CourtSearch.builder().id(2).candidate(expectedCandidate).status("clear")
                .search(Search.builder().id(2).searchName("saerch 2").build()).build();// create another CourtSearch object
        List<CourtSearch> expectedCourtSearch = Arrays.asList(courtSearch1, courtSearch2); // create a list of CourtSearch objects
        when(candidateSearchRepository.findByCandidateId(id)).thenReturn(Optional.of(expectedCourtSearch));
        when(modelMapper.map(courtSearch1, SearchChargeDTO.class)).thenReturn(searchChargeDTO1);
        when(modelMapper.map(courtSearch2, SearchChargeDTO.class)).thenReturn(searchChargeDTO2);

        // Act
        List<SearchChargeDTO> actualSearchChargeDTOList = candidateReportServiceImp.getCharges(id);

        // Assert
        assertEquals(expectedSearchChargeDTOList, actualSearchChargeDTOList);
    }
}

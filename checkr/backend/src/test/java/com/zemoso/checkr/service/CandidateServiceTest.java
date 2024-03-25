package com.zemoso.checkr.service;

import com.zemoso.checkr.dto.CandidateDTO;
import com.zemoso.checkr.dto.CandidateDetailDTO;
import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.dto.CourtSearchDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateDetails;
import com.zemoso.checkr.entity.CourtSearch;
import com.zemoso.checkr.entity.Search;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.repository.CandidateDetailsRepository;
import com.zemoso.checkr.repository.CandidateRepository;
import com.zemoso.checkr.repository.CourtSearchRepository;
import com.zemoso.checkr.service.impl.CandidateServiceImp;
import data.SampleCandidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private CandidateDetailsRepository candidateDetailsRepository;

    @Mock
    private CourtSearchRepository courtSearchRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CandidateServiceImp candidateServiceImp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testFindByFilter() {
        // Arrange
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "name";
        Sort.Direction sortOrder = Sort.Direction.ASC;
        String name = "";
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrder, sortBy));
        List<Candidate> candidateList = SampleCandidate.getCandidates();
        Page<Candidate> expectedCandidates = new PageImpl<>(candidateList, pageable, candidateList.size());
        when(candidateRepository.findByName(pageable, name)).thenReturn(expectedCandidates);

        // Act
        Page<Candidate> actualCandidates = candidateServiceImp.findByFilter(pageable, name);

        // Assert
        assertEquals(expectedCandidates, actualCandidates);
        assertEquals(candidateList.size()/pageSize+1,actualCandidates.getTotalPages());
        assertEquals(candidateList.size(),actualCandidates.getTotalElements());
    }

    @Test
     void testFindAll() {
        // Arrange
        List<Candidate> expectedCandidates = new ArrayList<>();
        when(candidateRepository.findAll()).thenReturn(expectedCandidates);
        List<Candidate> actualCandidates = candidateServiceImp.findAll();
        assertEquals(expectedCandidates, actualCandidates);
    }

    @Test
     void testFindById() throws NoSuchCandidateExistsException {
        // Arrange
        long id = 1;
        Candidate expectedCandidate = Candidate.builder().id(id).name("James Mayer").build(); // create a Candidate object
        CandidateDetails expectedCandidateDetails = CandidateDetails.builder().id(1).candidate(expectedCandidate).build(); // create a CandidateDetails object
        CourtSearch courtSearch1 = CourtSearch.builder().id(1).candidate(expectedCandidate).status("clear")
                .search(Search.builder().id(1).searchName("search 1").build()).build();
        CourtSearch courtSearch2 = CourtSearch.builder().id(2).candidate(expectedCandidate).status("clear")
                .search(Search.builder().id(2).searchName("saerch 2").build()).build();
        List<CourtSearch> expectedCourtSearch = Arrays.asList(courtSearch1, courtSearch2);
        List<CourtSearchDTO> courtSearchDTOS= expectedCourtSearch.stream()
                .map(c->modelMapper.map(c,CourtSearchDTO.class)).toList();

        CandidateReportDTO expecteCandidateReportDTO= CandidateReportDTO.builder()
                .candidate(modelMapper.map(expectedCandidate, CandidateDTO.class))
                .report(modelMapper.map(expectedCandidateDetails, CandidateDetailDTO.class))
                .courtSearch(courtSearchDTOS)
                .build();

        when(candidateRepository.findById(id)).thenReturn(Optional.of(expectedCandidate));
        when(candidateDetailsRepository.findByCandidateId(id)).thenReturn(Optional.of(expectedCandidateDetails));

        // Act
        CandidateReportDTO actualCandidateReportDTO = candidateServiceImp.findById(id);
        System.out.println(actualCandidateReportDTO);

        // Assert
        assertEquals(expecteCandidateReportDTO.getCandidate(),actualCandidateReportDTO.getCandidate());
        assertEquals(expecteCandidateReportDTO.getReport(),actualCandidateReportDTO.getReport());
    }

}

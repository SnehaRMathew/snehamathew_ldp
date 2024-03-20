package com.zemoso.checkr.controller;

import com.zemoso.checkr.dto.AdverseActionDTO;
import com.zemoso.checkr.dto.CandidateDTO;
import com.zemoso.checkr.dto.CandidateReportDTO;
import com.zemoso.checkr.dto.SearchChargeDTO;
import com.zemoso.checkr.entity.Candidate;
import com.zemoso.checkr.entity.CandidateDetails;
import com.zemoso.checkr.exception.NoSuchCandidateExistsException;
import com.zemoso.checkr.service.AdverseActionService;
import com.zemoso.checkr.service.CandidateReportService;
import com.zemoso.checkr.service.CandidateService;
import data.SampleCandidate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

//@SpringBootTest
 class CandidateControllerTest {

    ModelMapper modelMapper;
    private CandidateService candidateService;
    private CandidateController candidateController ;
    private AdverseActionService adverseActionService;
   private CandidateReportService candidateReportService;


    @BeforeEach
    public void init(){
        candidateService = new FakeCandidateServiceImp();
        candidateReportService =new FakeCandidateReportServiceImp();
        adverseActionService= new FakeAdverseActionService();
        candidateController = new CandidateController(candidateService,candidateReportService,adverseActionService);
        modelMapper =new ModelMapper();
    }
    @AfterEach
    public void last(){
        candidateService = null;
        candidateReportService =null;
        adverseActionService= null;
        candidateController =null;
        modelMapper =null;
    }
    @Test
     void testGetCandidatesPage() {
        // Arrange
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "name";
        Sort.Direction sortOrder = Sort.Direction.ASC;
        String name = "";
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrder, sortBy));
        List<Candidate> candidates = SampleCandidate.getCandidates();
        Page<Candidate> expectedCandidates =  new PageImpl<>(candidates, pageable, candidates.size());
        ResponseEntity<Page<Candidate>> response = candidateController.getAllCandidates(pageNo, pageSize, sortBy, sortOrder, name, "");
        assertEquals(expectedCandidates, response.getBody());
    }
    @Test
     void testGetCandidates() {
        List<Candidate> expectedCandidates =SampleCandidate.getCandidates();
        ResponseEntity<List<Candidate>> response = candidateController.getCandidates();
        assertEquals(expectedCandidates, response.getBody());
    }

    @Test
     void testGetIdExist() throws NoSuchCandidateExistsException {
        long id=10;
        Candidate candidate= SampleCandidate.getCandidates().get(10);
        CandidateDTO candidateDTO=modelMapper.map(candidate, CandidateDTO.class);
        CandidateReportDTO expectedCandidates = CandidateReportDTO.builder().candidate(candidateDTO).build();
        ResponseEntity<CandidateReportDTO> response = candidateController.getCandidateById(id);
        assertEquals(expectedCandidates, response.getBody());
    }

    @Test
     void testGetIdNotExist() throws NoSuchCandidateExistsException {
        long id=35;
       assertThrows( NoSuchCandidateExistsException.class, () ->candidateController.getCandidateById(id));
        //check for exception
    }

    @Test
     void testEngageCandidateExist() throws NoSuchCandidateExistsException {
        long id=10;
        String response = candidateController.engageCandidate(id);
        String expected = SampleCandidate.getCandidateDetails().get((int) id).getCandidate().getName() + " is marked as engaged";
        assertEquals(expected, response);
    }
    @Test
     void testEngageCandidateNotExist() throws NoSuchCandidateExistsException {
        long id=30;
        assertThrows(NoSuchCandidateExistsException.class, ()->candidateController.engageCandidate(id));
    }

    @Test
     void testAdverseAction() throws NoSuchCandidateExistsException {
        long id=10;
        int days=10;
        List<SearchChargeDTO> searchChargeDTOList=new ArrayList<>();
        String response = candidateController.adverseActionCandidate(id,days,searchChargeDTOList);
        String expected = "Adverse-action send";
        assertEquals(expected, response);
    }
    @Test
     void testAdverseActionNotExist() throws NoSuchCandidateExistsException {
        long id=30;
        int days=10;
        List<SearchChargeDTO> searchChargeDTOList=new ArrayList<>();
        assertThrows(NoSuchCandidateExistsException.class,
                ()->candidateController.adverseActionCandidate(id,days,searchChargeDTOList));
    }
    @Test
     void testChargesCandidateExist() throws NoSuchCandidateExistsException {
        long id=10;
        List<SearchChargeDTO> expected = new ArrayList<>();
        List<SearchChargeDTO> response = candidateController.getCharges(id);
        assertEquals(expected, response);
    }
    @Test
     void testChargesCandidateNoExist() throws NoSuchCandidateExistsException {
        long id=30;
        assertThrows(NoSuchCandidateExistsException.class, ()->candidateController.getCharges(id));
    }

    @Test
     void getAllAdverseActions() {
       List<AdverseActionDTO> expected = mockAdverseActionDTOList();
       assertEquals(expected.get(0).getName(), candidateController.getAllAdverseActions().get(0).getName());
    }


    private  class FakeCandidateServiceImp implements CandidateService {
        @Override
        public CandidateReportDTO findById(long id) throws NoSuchCandidateExistsException {
             Candidate candidate=SampleCandidate.getCandidates().stream().filter(c->(c.getId()==id)).findFirst()
                    .orElseThrow(()->new NoSuchCandidateExistsException(id));
              CandidateDTO candidateDTO=modelMapper.map(candidate, CandidateDTO.class);
              return CandidateReportDTO.builder().candidate(candidateDTO).build();
        }

        @Override
        public Page<Candidate> findByFilter(Pageable pageable, String name) {
            List<Candidate> candidates = SampleCandidate.getCandidates();
            Page<Candidate> candidatePage =  new PageImpl<>(candidates, pageable, candidates.size());
            return candidatePage;
        }

        @Override
        public List<Candidate> findAll() {
            return SampleCandidate.getCandidates();
        }
    }



    private class FakeCandidateReportServiceImp implements CandidateReportService {
        @Override
        public String engaged(long id) throws NoSuchCandidateExistsException {
            CandidateDetails candidateDetails= SampleCandidate.getCandidateDetails().stream()
                    .filter(c->c.getCandidate().getId()==id).findFirst()
                    .orElseThrow(()->new NoSuchCandidateExistsException(id));
             return candidateDetails.getCandidate().getName()+" is marked as engaged";

        }

        @Override
        public String sendAdverseAction(long id, int days, List<SearchChargeDTO> searchChargeDTOList) throws NoSuchCandidateExistsException {
            if(id<20)
              return  "Adverse-action send";
            else throw new NoSuchCandidateExistsException(id);
        }

        @Override
        public List<SearchChargeDTO> getCharges(Long id) throws NoSuchCandidateExistsException {
            if (id<20)
                return new ArrayList<>();
            else
                throw new NoSuchCandidateExistsException(id);
        }
    }

    private class FakeAdverseActionService implements AdverseActionService {
        @Override
        public List<AdverseActionDTO> findAll() {
            return mockAdverseActionDTOList();
        }
    }

    private List<AdverseActionDTO> mockAdverseActionDTOList() {
        AdverseActionDTO adverseActionDTO=mock(AdverseActionDTO.class);
        adverseActionDTO.setName("Candidate1");
        return List.of(adverseActionDTO);
    }
}

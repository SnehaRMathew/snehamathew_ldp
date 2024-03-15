//package com.zemoso.checkr.controller;
//
//import com.zemoso.checkr.repository.CaseRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@WebMvcTest(CandidateController.class)
//public class CandidateControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private final CaseRepository candidateRepository;
//
//    public CandidateControllerTest(CaseRepository candidateRepository) {
//        this.candidateRepository = candidateRepository;
//    }
//
//    @Test
//    public void testGetAllCandidates() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/candidates")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testGetCandidateById() throws Exception {
//        long candidateId = 1L; // Replace with an existing candidate ID
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/candidates/{id}", candidateId)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testSaveCandidate() throws Exception {
//        String candidateJson = "{\"name\": \"John Doe\"}"; // Replace with valid JSON
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/candidates")
//                        .content(candidateJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//}
//

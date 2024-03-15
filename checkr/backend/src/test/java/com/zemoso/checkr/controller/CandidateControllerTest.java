package com.zemoso.checkr.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.entity.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Initialize test data (if needed)
    // ...

    @BeforeEach
    public void setUp() {
        // Set up any necessary test data or mocks
        // ...
    }

    @Test
    public void testGetAllCandidates() throws Exception {
        // Arrange
        int page = 0;
        int size = 10;

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/Candidates")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        Page<Candidate> candidates = objectMapper.readValue(content, Page.class);

        assertThat(candidates).isNotNull();
        assertThat(candidates.getTotalElements()).isGreaterThan(0);
        // Add more assertions based on your business logic

        // Example: Verify that the first candidate's name is not empty
        List<Candidate> candidateList = candidates.getContent();
        if (!candidateList.isEmpty()) {
          //  assertThat(candidateList.get(0).).isNotBlank();
        }
    }
}

package com.zemoso.checkr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter

public class CandidateReport {
    private Candidate candidate;
    private Report report;
    private List<CourtSearch> courtSearches;
}
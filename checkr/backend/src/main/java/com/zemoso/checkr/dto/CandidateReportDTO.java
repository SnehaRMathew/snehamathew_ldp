package com.zemoso.checkr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateReportDTO {
    private CandidateDTO candidate;
    private CandidateDetailDTO report;
    private List<CourtSearchDTO> courtSearch;
}
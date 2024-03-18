package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor
public class CandidateReportDTO {
    private CandidateDTO candidate;
    private ReportDTO report;
    private List<CourtSearchDTO> courtSearch;
}
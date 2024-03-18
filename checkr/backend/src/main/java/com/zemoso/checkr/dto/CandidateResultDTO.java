package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor
public class CandidateResultDTO {
    private List<CandidateDTO> candidateDTO;
    private PagingDTO paging;
   // private Filter filter;
    private SortDTO sortDTO;
}

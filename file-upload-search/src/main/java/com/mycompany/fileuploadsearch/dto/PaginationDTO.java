package com.mycompany.fileuploadsearch.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class PaginationDTO {
    private Integer pageSize;
    private Integer currentPage;
    private Integer previousPage;
    private Integer nextPage;
}
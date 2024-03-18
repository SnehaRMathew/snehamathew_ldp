package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingDTO {
    private Integer totalRecords;
    private Integer totalPages;
    private Integer currentPage;
    private Integer nextPage;
    private Integer prevPage;
}


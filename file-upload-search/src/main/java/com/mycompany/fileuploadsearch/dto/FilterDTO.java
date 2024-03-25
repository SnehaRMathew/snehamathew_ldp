package com.mycompany.fileuploadsearch.dto;

import lombok.Builder;
import lombok.Data;

@Data@Builder
public class FilterDTO {
    private String filterName;
    private String filterValue;
}
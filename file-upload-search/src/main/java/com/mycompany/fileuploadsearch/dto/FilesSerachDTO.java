package com.mycompany.fileuploadsearch.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data@Builder
public class FilesSerachDTO {
    private List<FileDTO> result;
    private PaginationDTO pagination;
    private List<FilterDTO> filters;
}
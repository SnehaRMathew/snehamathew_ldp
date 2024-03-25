package com.mycompany.fileuploadsearch.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data @Builder
public class FileContentPageDTO {
    List<FileContentDTO> fileContentDTOS;
    PaginationDTO paginationDTO;
    FilterDTO filterDTO;
}

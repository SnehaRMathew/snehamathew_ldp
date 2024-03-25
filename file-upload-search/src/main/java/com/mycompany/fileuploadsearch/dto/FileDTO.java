package com.mycompany.fileuploadsearch.dto;

import com.mycompany.fileuploadsearch.enums.FileType;
import com.mycompany.fileuploadsearch.enums.PublishBy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileDTO {
    private String filename;
    private FileType filetype;
    private PublishBy publishBy;
    private LocalDateTime uploadDate;
}
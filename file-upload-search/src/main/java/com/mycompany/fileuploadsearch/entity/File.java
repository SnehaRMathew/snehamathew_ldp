package com.mycompany.fileuploadsearch.entity;

import com.mycompany.fileuploadsearch.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    private Long id;
    private String filename;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    private byte[] content;
    private LocalDateTime uploadDateTime;
    private Boolean isDeleted;
    @ManyToOne
    private User user;
}
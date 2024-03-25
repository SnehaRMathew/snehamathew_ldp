package com.mycompany.fileuploadsearch.entity;

import com.mycompany.fileuploadsearch.enums.ActionType;
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
public class FileAudit {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private ActionType action;
    private String oldData;
    private String newData;
    private LocalDateTime auditDate;
    @ManyToOne
    private File file;
}
package com.mycompany.fileuploadsearch.dto;

import com.mycompany.fileuploadsearch.enums.ActionType;
import jakarta.json.Json;

import java.time.LocalDateTime;

public class AuditDTO {
    FileDTO fileDTO;
    ActionType actionType;
    Json oldJson;
    Json newJson;
     LocalDateTime date;

}

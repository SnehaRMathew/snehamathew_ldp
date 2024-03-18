package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ErrorMessageDTO {
    private Integer statusCode;
    private String description;
}
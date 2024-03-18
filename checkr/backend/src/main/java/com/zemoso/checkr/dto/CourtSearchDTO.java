package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor
public class CourtSearchDTO {
    private String searchName;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data@NoArgsConstructor
public class CandidateSummaryDTO {
    private String id;
    private String name;
    private String adjudicationType;
    private String status;
    private String location;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
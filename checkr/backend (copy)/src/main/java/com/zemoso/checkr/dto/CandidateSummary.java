package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CandidateSummary {
    private String id;
    private String name;
    private String adjudicationType;
    private String status;
    private String location;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
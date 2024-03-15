package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourtSearch {
    private String search;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
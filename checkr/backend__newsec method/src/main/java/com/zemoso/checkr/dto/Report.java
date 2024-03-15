package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Report {
    private String name;
    private String status;
    private String adjudication;
    private String pack;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date completeDate;
    private String turnAroundTime;
}
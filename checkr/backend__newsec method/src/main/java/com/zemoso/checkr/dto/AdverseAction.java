package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;

public class AdverseAction {;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date preNoticeDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postNoticeDate;


    public enum Status {
        PENDING, SCHEDULED, DISPUTE, CANCELLED, UNDELIVERABLE
    }
}
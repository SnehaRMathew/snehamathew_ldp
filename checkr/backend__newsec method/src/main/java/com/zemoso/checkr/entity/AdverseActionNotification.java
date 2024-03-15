package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter @Setter @Entity @NoArgsConstructor
public class AdverseActionNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate receiver;
    private String body;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date preNoticeDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postNoticeDate;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedDate;
}

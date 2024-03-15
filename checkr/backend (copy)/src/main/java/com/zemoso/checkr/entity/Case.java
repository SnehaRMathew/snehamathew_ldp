package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Getter @Setter @Entity @NoArgsConstructor
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidateID;
    private String description;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy; // References HR ID
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy; // References HR ID
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedDate;
}

package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter @Setter @Entity @NoArgsConstructor
public class Engage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Candidate candidateId;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private String status;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedDate;
}

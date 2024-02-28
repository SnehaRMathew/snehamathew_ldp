package com.zemoso.checkr.entity;

import java.util.Date;
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
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR createdBy; // References HR ID
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR updatedBy; // References HR ID
    private Date updatedDate;
}

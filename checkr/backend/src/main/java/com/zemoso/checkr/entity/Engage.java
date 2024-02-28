package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Engage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Candidate candidateId;
    private String description;
    private Date date;
    private String status;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private HR createdBy;

    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR updatedBy;
    private Date updatedDate;
}

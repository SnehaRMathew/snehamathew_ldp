package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Candidate candidateID; // References Candidate ID
    private String description;
    private String status;
    private Date date;
    private HR createdBy; // References HR ID
    private Date createdDate;
    private HR updatedBy; // References HR ID
    private Date updatedDate;
}

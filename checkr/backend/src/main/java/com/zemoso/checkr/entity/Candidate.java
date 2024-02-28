package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date dateOfBirth;
    private Date enrolDate;
    private String email;
    private String status;
    private String mobile;
    private String address;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private HR createdBy;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR updatedBy;
    private Date updatedDate;
}

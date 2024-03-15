package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date enrolDate;
    private String email;
    private String status;
    private String mobile;
    private String address;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
    private Date updatedDate;
}

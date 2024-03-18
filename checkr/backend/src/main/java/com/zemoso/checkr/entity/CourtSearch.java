package com.zemoso.checkr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "Court_Search")
public class CourtSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Search search; // Many-to-one relationship with Search

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch= FetchType.LAZY)
    private Candidate candidate; // Many-to-one relationship with Candidate

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

}

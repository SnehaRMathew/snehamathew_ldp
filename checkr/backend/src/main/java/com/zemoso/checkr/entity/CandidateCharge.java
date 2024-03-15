package com.zemoso.checkr.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Candidate_Charge")
public class CandidateCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "searchID")
    private Search search; // Many-to-one relationship with SearchCharge

    @ManyToOne
    @JoinColumn(name = "candidateId")
    private Candidate candidate; // Many-to-one relationship with Candidate
}

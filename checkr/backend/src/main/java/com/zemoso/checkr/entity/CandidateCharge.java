package com.zemoso.checkr.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Candidate_Charge")
public class CandidateCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private SearchCharge searchCharge; // Many-to-one relationship with SearchCharge

    @ManyToOne
    private Candidate candidate; // Many-to-one relationship with Candidate
}

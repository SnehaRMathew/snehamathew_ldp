package com.zemoso.checkr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Candidate_Report")
public class CandidateDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch=FetchType.EAGER)
    private Candidate candidate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "adjudication", length = 20, nullable = true)
    private String adjudication;

    @Column(name = "package", length = 50, nullable = true)
    private String packageType;

    @Column(name = "createDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    @Column(name ="completeDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date completeDate;

}

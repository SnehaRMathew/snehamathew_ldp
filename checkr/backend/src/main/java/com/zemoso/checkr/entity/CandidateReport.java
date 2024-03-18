package com.zemoso.checkr.entity;
import java.util.Date;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zemoso.checkr.enums.AdjudicationType;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Candidate_Report")
public class CandidateReport {

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

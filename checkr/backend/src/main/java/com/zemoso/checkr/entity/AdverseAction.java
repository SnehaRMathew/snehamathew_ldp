package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zemoso.checkr.enums.AdverseActionStatus;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Data @Entity @NoArgsConstructor
@Builder @AllArgsConstructor
@Table(name = "Adverse_Action")
public class AdverseAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    @Column(name = "status", length = 50)
    @Enumerated(EnumType.STRING)
    private AdverseActionStatus status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date preNoticeDate;


    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postNoticeDate;
}

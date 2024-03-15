package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter @Entity @NoArgsConstructor
@Table(name = "Adverse_Action")
public class AdverseAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "status", length = 50)
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date preNoticeDate;


    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postNoticeDate;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPreNoticeDate(Date preNoticeDate) {
        this.preNoticeDate = preNoticeDate;
    }

    public void setPostNoticeDate(Date postNoticeDate) {
        this.postNoticeDate = postNoticeDate;
    }
}

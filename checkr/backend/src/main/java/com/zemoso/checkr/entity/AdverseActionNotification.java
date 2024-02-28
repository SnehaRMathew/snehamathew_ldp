package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class AdverseActionNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate receiver;
    private String body;
    private Date preNoticeDate;
    private Date postNoticeDate;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR createdBy;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private HR updatedBy;
    private Date updatedDate;
}

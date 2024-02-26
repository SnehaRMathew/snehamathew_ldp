package com.zemoso.checkr.Entity;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
public class AdverseActionNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private HR sender;
    private Candidate receiver;
    private String body;
    private Date preNoticeDate;
    private Date postNoticeDate;
    private String status;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

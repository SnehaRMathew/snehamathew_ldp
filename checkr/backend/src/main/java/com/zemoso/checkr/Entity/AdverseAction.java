package com.zemoso.checkr.Entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
public class AdverseAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Candidate candidateId;
    private String description;
    private Date date;
    private String status;
    private AdverseActionNotification notificationId;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

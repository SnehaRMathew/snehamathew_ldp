package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
public class HR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private String email;
    private String status;
    private String mobile;
    private String address;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

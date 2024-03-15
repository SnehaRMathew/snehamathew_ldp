package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Candidate {
    private Integer id;
    private String name;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String phone;
    private String zipcode;
    private String ssn;
    private String driversLicense;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdate;
}
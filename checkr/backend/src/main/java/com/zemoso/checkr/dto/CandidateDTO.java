package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor
public class CandidateDTO {
    private Integer id;
    private String name;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String phone;
    private String zipcode;
    private String ssn;
    private String driverLicense;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdate;
}
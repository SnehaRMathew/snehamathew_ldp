package com.zemoso.checkr.entity;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Getter @Setter @Entity @NoArgsConstructor
public class HR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private String email;
    private String status;
    private String mobile;
    private String address;

}

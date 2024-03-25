package com.zemoso.checkr.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String zipcode;

    @Column(length = 50, nullable=true)
    private String ssn;

    @Column(length=50,nullable=true)
    private String driverLicense;

    @Column(nullable=false)
    private int createdBy;

    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createDate;

    @Column(nullable=true)
    private Integer updateBy;

    @Column(nullable=true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        updateDate = createDate; // Initialize updateDate with createDate
    }
    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }

}

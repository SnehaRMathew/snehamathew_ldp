package com.zemoso.checkr.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "Candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @PrePersist // Executed before entity is persisted (i.e., before insert)
    public void prePersist() {
        createDate = LocalDateTime.now();
        updateDate = createDate; // Initialize updateDate with createDate
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @PreUpdate // Executed before entity is updated
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }

}

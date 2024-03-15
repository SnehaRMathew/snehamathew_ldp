package com.zemoso.checkr.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Entity
@Table(name = "user")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
    private String password;
}


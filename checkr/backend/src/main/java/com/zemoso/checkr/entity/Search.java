package com.zemoso.checkr.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;


@Getter @Entity @NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name ="Search")
public class Search {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "SearchName", length = 50)
        private String SearchName;

}

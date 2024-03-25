package com.zemoso.checkr.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter @Entity @NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Table(name ="Search")
public class Search {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "SearchName", length = 50)
        private String searchName;

}

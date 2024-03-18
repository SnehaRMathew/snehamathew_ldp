package com.zemoso.checkr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Search_Charge")
public class SearchCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Search search;
    private String chargeCode;
    private String chargeDescription;
}

package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchChargeDTO {
    private long id;
    private String chargeCode;
    private String chargeDescription;
}
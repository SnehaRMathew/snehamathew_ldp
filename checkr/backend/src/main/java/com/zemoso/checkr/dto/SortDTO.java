package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SortDTO {
    private String by;
    private String order;
}

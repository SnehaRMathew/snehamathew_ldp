package com.zemoso.checkr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor
public class PreAdverseActionNoticeDTO {
    private String from;
    private String to;
    private String subject;
    private String salutation;
    private String prefix;
    private List<SearchChargeDTO> charges;
}
package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zemoso.checkr.enums.AdverseActionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor
public class AdverseActionDTO {
    private String name;
    private AdverseActionStatus status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date preNoticeDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postNoticeDate;


}
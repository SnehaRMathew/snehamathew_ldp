package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data @NoArgsConstructor
public class CandidateDetailDTO {
    private String status;
    private String adjudication;
    private String packageType;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date completeDate;
    private long turnAroundTime;

}
package com.zemoso.checkr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.PostUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data @NoArgsConstructor
public class ReportDTO {
    private String status;
    private String adjudication;
    private String packageType;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date completeDate;
    private long turnAroundTime;

}
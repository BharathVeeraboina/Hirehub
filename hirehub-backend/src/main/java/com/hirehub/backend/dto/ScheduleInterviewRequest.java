package com.hirehub.backend.dto;

import lombok.Data;

@Data
public class ScheduleInterviewRequest {
    private String interviewDate;
    private String interviewTime;
    private String interviewLocation;
}

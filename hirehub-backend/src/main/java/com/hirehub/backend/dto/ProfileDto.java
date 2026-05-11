package com.hirehub.backend.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private String name;
    private String phone;
    private String title;
    private String bio;
    private String skills;
    private String location;
    private String portfolioUrl;
}

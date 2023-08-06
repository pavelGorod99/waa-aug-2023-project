package com.example.Alumni_Management_Portal.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IndustryDto {
    private int id;
    private String industry;
}
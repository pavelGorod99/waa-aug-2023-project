package com.example.Alumni_Management_Portal.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class InsightDto {
        private int id;

        private String title;
        private Boolean surveyStatus;
        private LocalDateTime openDate;
        private LocalDateTime closeDate;
}

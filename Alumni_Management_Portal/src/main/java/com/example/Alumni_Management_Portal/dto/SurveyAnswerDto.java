package com.example.Alumni_Management_Portal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class SurveyAnswerDto {

    private int id;
    private  String answer;
    private LocalDateTime dateCompleted;
}

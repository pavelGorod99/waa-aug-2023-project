package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.SurveyAnswerDto;
import com.example.Alumni_Management_Portal.dto.SurveyQuestionDto;

import java.util.List;
import java.util.Optional;

public interface SurveyAnswerService {

    public List<SurveyAnswerDto> getAll();

    public SurveyAnswerDto getById(int surveyAnswerId);

}


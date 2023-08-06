package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.InsightDto;
import com.example.Alumni_Management_Portal.dto.SurveyQuestionDto;

import java.util.List;

public interface SurveyQuestionService {

    public void create(SurveyQuestionDto surveyQuestionDto);

    public void update(SurveyQuestionDto surveyQuestionDto);

    //public void delete(Integer surveyQuestionId);

    public SurveyQuestionDto getById(Integer surveyQuestionId);

    List<SurveyQuestionDto> getAll();

    List<SurveyQuestionDto> getSurveyQuestionByInsight (InsightDto insightDto);
}

